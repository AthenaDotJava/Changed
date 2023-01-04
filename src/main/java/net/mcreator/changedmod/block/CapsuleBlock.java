
package net.mcreator.changedmod.block;

import net.mcreator.changedmod.procedures.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.changedmod.world.inventory.CapsuleSleepUIMenu;
import net.mcreator.changedmod.init.ChangedModBlocks;

import java.util.List;
import java.util.Collections;

import io.netty.buffer.Unpooled;

import static net.mcreator.changedmod.block.CapsuleEnumStates.*;
import static net.minecraft.core.Direction.NORTH;

public class CapsuleBlock extends Block implements SimpleWaterloggedBlock

{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<CapsuleEnumStates> HALF = EnumProperty.create("half", CapsuleEnumStates.class);
	public static final BooleanProperty OCCUPIED = BooleanProperty.create("occupied");

	//Hit boxes
	//  Bases and Lids
	public static final VoxelShape LID = Shapes.or(
			Block.box(1,15,3,15,16,13),
			Block.box(0,15,5,16,16,11),
			Block.box(5,15,0,11,16,16),
			Block.box(2,15,2,14,16,14),
			Block.box(3,15,1,13,16,15)
	);
	public static final VoxelShape BASE = Shapes.or(
			Block.box(1,0,3,15,1,13),
			Block.box(0,0,5,16,1,11),
			Block.box(5,0,0,11,1,16),
			Block.box(2,0,2,14,1,14),
			Block.box(3,0,1,13,1,15)
	);
	public static final int[][] SIDE = {
			{13, 0, 13, 14, 16, 14},
			{5, 0, 15, 11, 16, 16},
			{0, 0, 5, 1, 16, 11},
			{15, 0, 5, 16, 16, 11},
			{3, 0, 14, 5, 16, 15},
			{2, 0, 13, 3, 16, 14},
			{1, 0, 11, 2, 16, 13},
			{11, 0, 14, 13, 16, 15},
			{14, 0, 11, 15, 16, 13}
	};
	// Capsule parts
	public static final int[][] OPEN = {
			{5,0,14,11,16,15},
			{2,0,11,3,16,12},
			{1,0,10,2,16,11},
			{4,0,13,5,16,14},
			{3,0,12,4,16,13},
			{14,0,10,15,16,11},
			{13,0,11,14,16,12},
			{12,0,12,13,16,13},
			{11,0,13,12,16,14}
	};
	public static final int[][] CLOSED = {
			{5,0,1,11,16,2},
			{3,0,3,4,16,4},
			{4,0,2,5,16,3},
			{1,0,5,2,16,6},
			{2,0,4,3,16,5},
			{11,0,2,12,16,3},
			{12,0,3,13,16,4},
			{13,0,4,14,16,5},
			{14,0,5,15,16,6}
	};
	public CapsuleBlock() {
		super(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).strength(25f, 10f).requiresCorrectToolForDrops().noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, NORTH).setValue(WATERLOGGED, false).setValue(HALF, DOWN).setValue(OCCUPIED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(HALF) == UP)
			return Shapes.or(LID, getCapsule(state));
		else
			return Shapes.or(BASE, getCapsule(state));
	}

	public VoxelShape getCapsule(BlockState state) {
		if (state.getValue(OCCUPIED)) {
			switch (state.getValue(FACING)) {
				default:
				case NORTH:
					return Shapes.or(
							BBoxTools.toVoxelShape(SIDE[0]),
							BBoxTools.toVoxelShape(SIDE[1]),
							BBoxTools.toVoxelShape(SIDE[2]),
							BBoxTools.toVoxelShape(SIDE[3]),
							BBoxTools.toVoxelShape(SIDE[4]),
							BBoxTools.toVoxelShape(SIDE[5]),
							BBoxTools.toVoxelShape(SIDE[6]),
							BBoxTools.toVoxelShape(SIDE[7]),
							BBoxTools.toVoxelShape(SIDE[8]),

							BBoxTools.toVoxelShape(CLOSED[0]),
							BBoxTools.toVoxelShape(CLOSED[1]),
							BBoxTools.toVoxelShape(CLOSED[2]),
							BBoxTools.toVoxelShape(CLOSED[3]),
							BBoxTools.toVoxelShape(CLOSED[4]),
							BBoxTools.toVoxelShape(CLOSED[5]),
							BBoxTools.toVoxelShape(CLOSED[6]),
							BBoxTools.toVoxelShape(CLOSED[7]),
							BBoxTools.toVoxelShape(CLOSED[8])
					);
				case SOUTH:
					return Shapes.or(
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[0])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[1])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[2])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[3])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[4])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[5])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[6])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[7])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[8])),

							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[0])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[1])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[2])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[3])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[4])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[5])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[6])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[7])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(CLOSED[8]))
					);
				case WEST:
					return Shapes.or(
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[0])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[1])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[2])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[3])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[4])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[5])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[6])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[7])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[8])),

							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[0])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[1])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[2])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[3])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[4])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[5])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[6])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[7])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(CLOSED[8]))
					);
				case EAST:
					return Shapes.or(
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[0]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[1]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[2]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[3]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[4]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[5]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[6]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[7]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[8]))),

							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[0]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[1]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[2]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[3]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[4]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[5]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[6]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[7]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(CLOSED[8])))
					);
			}
		} else {
			switch (state.getValue(FACING)) {
				default:
				case NORTH:
					return Shapes.or(
							BBoxTools.toVoxelShape(SIDE[0]),
							BBoxTools.toVoxelShape(SIDE[1]),
							BBoxTools.toVoxelShape(SIDE[2]),
							BBoxTools.toVoxelShape(SIDE[3]),
							BBoxTools.toVoxelShape(SIDE[4]),
							BBoxTools.toVoxelShape(SIDE[5]),
							BBoxTools.toVoxelShape(SIDE[6]),
							BBoxTools.toVoxelShape(SIDE[7]),
							BBoxTools.toVoxelShape(SIDE[8]),

							BBoxTools.toVoxelShape(OPEN[0]),
							BBoxTools.toVoxelShape(OPEN[1]),
							BBoxTools.toVoxelShape(OPEN[2]),
							BBoxTools.toVoxelShape(OPEN[3]),
							BBoxTools.toVoxelShape(OPEN[4]),
							BBoxTools.toVoxelShape(OPEN[5]),
							BBoxTools.toVoxelShape(OPEN[6]),
							BBoxTools.toVoxelShape(OPEN[7]),
							BBoxTools.toVoxelShape(OPEN[8])
					);
				case SOUTH:
					return Shapes.or(
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[0])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[1])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[2])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[3])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[4])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[5])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[6])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[7])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(SIDE[8])),

							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[0])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[1])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[2])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[3])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[4])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[5])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[6])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[7])),
							BBoxTools.toVoxelShape(BBoxTools.flipZ(OPEN[8]))
					);
				case WEST:
					return Shapes.or(
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[0])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[1])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[2])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[3])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[4])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[5])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[6])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[7])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(SIDE[8])),

							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[0])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[1])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[2])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[3])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[4])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[5])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[6])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[7])),
							BBoxTools.toVoxelShape(BBoxTools.swapHorizontalAxis(OPEN[8]))
					);
				case EAST:
					return Shapes.or(
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[0]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[1]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[2]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[3]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[4]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[5]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[6]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[7]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(SIDE[8]))),

							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[0]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[1]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[2]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[3]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[4]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[5]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[6]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[7]))),
							BBoxTools.toVoxelShape(BBoxTools.flipX(BBoxTools.swapHorizontalAxis(OPEN[8])))
					);
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, HALF, OCCUPIED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
		if (worldIn instanceof LevelAccessor world) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			return CapsulePlacementConditionProcedure.execute(world, x, y, z);
		}
		return super.canSurvive(blockstate, worldIn, pos);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos,
			BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return !state.canSurvive(world, currentPos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
		return new ItemStack(ChangedModBlocks.CAPSULE.get());
	}

	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return BlockPathTypes.BLOCKED;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.IGNORE;
	}

	@Override
	public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
		if (player.getInventory().getSelected().getItem() instanceof TieredItem tieredItem)
			return tieredItem.getTier().getLevel() >= 2;
		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(ChangedModBlocks.CAPSULE.get()));
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		CapsulePlaceProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
		boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
		CapsuleDeleteProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		return retval;
	}

	public void attack(BlockState blockstate, Level world, BlockPos pos, Player entity) {
		super.attack(blockstate, world, pos, entity);
		CapsuleUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		if (entity instanceof ServerPlayer player && (!(blockstate.getBlock().getStateDefinition().getProperty("occupied") instanceof BooleanProperty _getbp1
				&& blockstate.getValue(_getbp1)))) {
			NetworkHooks.openScreen(player, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Capsule");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new CapsuleSleepUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();

		if (("up").equals(blockstate.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep1
				? blockstate.getValue(_getep1).toString()
				: "")) {
			CapsuleWakeProcedure.execute(world, x, y - 1, z, blockstate);
		} else if (!("up").equals(blockstate.getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep1
				? blockstate.getValue(_getep1).toString()
				: "")){
			CapsuleWakeProcedure.execute(world, x, y ,z, blockstate);
		}
		return InteractionResult.SUCCESS;
	}
}
