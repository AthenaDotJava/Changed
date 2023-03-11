
package net.mcreator.changedmod.block;

import net.mcreator.changedmod.init.ChangedModItems;
import net.mcreator.changedmod.init.ChangedModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import utils.enums.CardTypes;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class TerminalBlock extends Block implements SimpleWaterloggedBlock

{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty PASS_SET = BooleanProperty.create("pass_set");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final EnumProperty<CardTypes> CARD = EnumProperty.create("card_type", CardTypes.class);
	public static final int POWER_DURATION = 30;

	public TerminalBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(10f).requiresCorrectToolForDrops().noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> true));
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
	public InteractionResult use(
			BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit
	) {
		if ((entity.getMainHandItem()).getItem() == ChangedModItems.KEY_CARD.get()) {
			ItemStack card = entity.getMainHandItem();
			if (blockstate.getValue(PASS_SET)) {
				if (card.getOrCreateTag().get("Colour") instanceof StringTag) {
					if (card.getOrCreateTag().get("Colour").toString().substring(1, card.getOrCreateTag().get("Colour")
							.toString().length() - 1).toLowerCase().equals(blockstate.getValue(CARD).getSerializedName())) {
						this.power(blockstate, world, pos);
						playSound(entity, world, pos, true);

						return InteractionResult.SUCCESS;
					} else {
						playSound(entity, world, pos, false);

						return InteractionResult.SUCCESS;
					}
				}
			} else {
				try {
					world.setBlock(pos,
							blockstate.setValue(PASS_SET, true)
									.setValue(CARD, CardTypes.valueOf(card.getOrCreateTag()
											.get("Colour").toString().substring(1, card.getOrCreateTag()
													.get("Colour").toString().length() - 1).toUpperCase())),
							3);
					playSound(entity, world, pos, true);

					return InteractionResult.SUCCESS;
				} catch (NullPointerException | IllegalArgumentException e){
					playSound(entity, world, pos, false);
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.FAIL;
	}

	private void power(BlockState blockstate, Level world, BlockPos pos) {
		world.setBlock(pos, blockstate.setValue(POWERED, Boolean.valueOf(true)), 3);
		this.updateNeighbours(blockstate, world, pos);
		world.scheduleTick(pos, this, POWER_DURATION);
	}
	private void updateNeighbours(BlockState state, Level world, BlockPos pos) {
	world.updateNeighborsAt(pos, this);
	world.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
}
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
		if (state.getValue(POWERED)) {
				level.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(false)), 3);
				this.updateNeighbours(state, level, pos);
				level.gameEvent((Entity)null, GameEvent.BLOCK_DEACTIVATE, pos);
		}
	}

	public boolean isSignalSource(BlockState state) {
		return true;
	}
	public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction){
		return state.getValue(POWERED) ? 15 : 0;
	}
	protected void playSound(@Nullable Player entity, LevelAccessor world, BlockPos pos, boolean accepted) {
		world.playSound(accepted ? entity : null, pos, this.getSound(accepted), SoundSource.BLOCKS, 0.3F, accepted ? 0.6F : 0.5F);
	}
	protected SoundEvent getSound(boolean b) {
		return b ? ChangedModSounds.TERMINAL_SUCCESS.get() : ChangedModSounds.TERMINAL_FAIL.get();
	}
}