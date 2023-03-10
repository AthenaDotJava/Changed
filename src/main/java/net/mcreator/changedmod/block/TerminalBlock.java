
package net.mcreator.changedmod.block;

import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootContext;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.changedmod.world.inventory.TerminalUIMenu;
import net.mcreator.changedmod.utils.CardTypes;

import java.util.List;
import java.util.Collections;

import io.netty.buffer.Unpooled;

public class TerminalBlock extends Block implements SimpleWaterloggedBlock

{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty PASS_SET = BooleanProperty.create("pass_set");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final EnumProperty<CardTypes> CARD = EnumProperty.create("card_type", CardTypes.class);

	public TerminalBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f).requiresCorrectToolForDrops().noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false)
				.setValue(PASS_SET, false).setValue(POWERED, false)
				.setValue(CARD, CardTypes.WHITE)
		);
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

		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(2, 3, 0, 14, 14, 6), box(3, 6, 6, 9, 11, 7), box(2, 3, 6, 3, 14, 8), box(9, 3, 6, 14, 14, 8),
					box(10, 7, 7, 13, 8, 9), box(10, 10, 7, 13, 11, 9), box(10, 8, 7, 11, 10, 9), box(12, 8, 7, 13, 10, 9), box(3, 3, 6, 9, 6, 8),
					box(3, 11, 6, 9, 14, 8));
			case NORTH -> Shapes.or(box(2, 3, 10, 14, 14, 16), box(7, 6, 9, 13, 11, 10), box(13, 3, 8, 14, 14, 10), box(2, 3, 8, 7, 14, 10),
					box(3, 7, 7, 6, 8, 9), box(3, 10, 7, 6, 11, 9), box(5, 8, 7, 6, 10, 9), box(3, 8, 7, 4, 10, 9), box(7, 3, 8, 13, 6, 10),
					box(7, 11, 8, 13, 14, 10));
			case EAST ->
				Shapes.or(box(0, 3, 2, 6, 14, 14), box(6, 6, 7, 7, 11, 13), box(6, 3, 13, 8, 14, 14), box(6, 3, 2, 8, 14, 7), box(7, 7, 3, 9, 8, 6),
						box(7, 10, 3, 9, 11, 6), box(7, 8, 5, 9, 10, 6), box(7, 8, 3, 9, 10, 4), box(6, 3, 7, 8, 6, 13), box(6, 11, 7, 8, 14, 13));
			case WEST -> Shapes.or(box(10, 3, 2, 16, 14, 14), box(9, 6, 3, 10, 11, 9), box(8, 3, 2, 10, 14, 3), box(8, 3, 9, 10, 14, 14),
					box(7, 7, 10, 9, 8, 13), box(7, 10, 10, 9, 11, 13), box(7, 8, 10, 9, 10, 11), box(7, 8, 12, 9, 10, 13), box(8, 3, 3, 10, 6, 9),
					box(8, 11, 3, 10, 14, 9));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED
				,PASS_SET , POWERED
				, CARD
		);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, flag);
		return this.defaultBlockState().setValue(FACING, context.getClickedFace()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
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
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
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
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		if (entity instanceof ServerPlayer player) {
			NetworkHooks.openScreen(player, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Terminal");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new TerminalUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

}