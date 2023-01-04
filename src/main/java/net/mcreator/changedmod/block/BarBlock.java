
package net.mcreator.changedmod.block;

import net.mcreator.changedmod.procedures.BarConnectionProcedure;
import net.mcreator.changedmod.procedures.BarDirectionChangeProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Collections;

public class BarBlock extends Block implements SimpleWaterloggedBlock

{
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty VERTICAL = BooleanProperty.create("vertical");
	public static final BooleanProperty HORIZONTALNTOS = BooleanProperty.create("horizontalntos");

	//VoxelShapes
	//  Straight
	public static final VoxelShape STRAIGHT_N_UD = Block.box(7,0,11,9,16,13);
	public static final VoxelShape STRAIGHT_N_EW = Block.box(0,7,11,16,9,13);
	public static final VoxelShape STRAIGHT_S_UD = Block.box(7,0,3,9,16,5);
	public static final VoxelShape STRAIGHT_S_EW = Block.box(0,7,3,16,9,5);
	public static final VoxelShape STRAIGHT_E_UD = Block.box(3,0,7,5,16,9);
	public static final VoxelShape STRAIGHT_E_NS = Block.box(3,7,0,5,9,16);
	public static final VoxelShape STRAIGHT_W_UD = Block.box(11,0,7,13,16,9);
	public static final VoxelShape STRAIGHT_W_NS = Block.box(11,7,0,13,9,16);
	public static final VoxelShape STRAIGHT_U_NS = Block.box(7,3,0,9,5,16);
	public static final VoxelShape STRAIGHT_U_EW = Block.box(0,3,7,16,5,9);
	public static final VoxelShape STRAIGHT_D_NS = Block.box(7,11,0,9,13,16);
	public static final VoxelShape STRAIGHT_D_EW = Block.box(0,11,7,16,13,9);
	//  Corner
	public static final VoxelShape CORNER_N_U = Shapes.or(
			Block.box(7,4,11,9,16,13),
			Block.box(7,3,13,9,5,16),
			Block.box(7,3,12,9,4,13),
			Block.box(7,5,13,9,6,14)
	);
	public static final VoxelShape CORNER_N_D = Shapes.or(
			Block.box(7,0,11,9,12,13),
			Block.box(7,11,13,9,13,16),
			Block.box(7,12,12,9,13,13),
			Block.box(7,10,13,9,11,14)
	);
	public static final VoxelShape CORNER_N_E = Shapes.or(
			Block.box(4,7,11,16,9,13),
			Block.box(3,7,13,5,9,16),
			Block.box(3,7,12,4,9,13),
			Block.box(5,7,13,6,9,14)
	);
	public static final VoxelShape CORNER_N_W = Shapes.or(
			Block.box(0,7,11,12,9,13),
			Block.box(11,7,13,13,9,16),
			Block.box(12,7,12,13,9,13),
			Block.box(10,7,13,11,9,14)
	);

	public static final VoxelShape CORNER_S_U = Shapes.or(
			Block.box(7,4,3,9,16,5),
			Block.box(7,3,0,9,5,3),
			Block.box(7,3,3,9,4,4),
			Block.box(7,5,2,9,6,3)
	);
	public static final VoxelShape CORNER_S_D = Shapes.or(
			Block.box(7,0,3,9,12,5),
			Block.box(7,11,0,9,13,3),
			Block.box(7,12,3,9,13,4),
			Block.box(7,10,2,9,11,3)
	);
	public static final VoxelShape CORNER_S_E = Shapes.or(
			Block.box(4,7,3,16,9,5),
			Block.box(3,7,0,5,9,3),
			Block.box(3,7,3,4,9,4),
			Block.box(5,7,2,6,9,3)
	);
	public static final VoxelShape CORNER_S_W = Shapes.or(
			Block.box(0,7,3,12,9,5),
			Block.box(11,7,0,13,9,3),
			Block.box(12,7,3,13,9,4),
			Block.box(10,7,2,11,9,3)
	);

	public static final VoxelShape CORNER_E_U = Shapes.or(
			Block.box(3,4,7,5,16,9),
			Block.box(0,3,7,3,5,9),
			Block.box(3,3,7,4,4,9),
			Block.box(2,5,7,3,6,9)
	);
	public static final VoxelShape CORNER_E_D = Shapes.or(
			Block.box(3,0,7,5,12,9),
			Block.box(0,11,7,3,13,9),
			Block.box(3,12,7,4,13,9),
			Block.box(2,10,7,3,11,9)
	);
	public static final VoxelShape CORNER_E_S = Shapes.or(
			Block.box(3,7,4,5,9,16),
			Block.box(0,7,3,3,9,5),
			Block.box(3,7,3,4,9,4),
			Block.box(2,7,5,3,9,6)
	);
	public static final VoxelShape CORNER_E_N = Shapes.or(
			Block.box(3,7,0,5,9,12),
			Block.box(0,7,11,3,9,13),
			Block.box(3,7,12,4,9,13),
			Block.box(2,7,10,3,9,11)
	);

	public static final VoxelShape CORNER_W_U = Shapes.or(
			Block.box(11,4,7,13,16,9),
			Block.box(13,3,7,16,5,9),
			Block.box(12,3,7,13,4,9),
			Block.box(13,5,7,14,6,9)
	);
	public static final VoxelShape CORNER_W_D = Shapes.or(
			Block.box(11,0,7,13,12,9),
			Block.box(13,11,7,16,13,9),
			Block.box(12,12,7,13,13,9),
			Block.box(13,10,7,14,11,9)
	);
	public static final VoxelShape CORNER_W_S = Shapes.or(
			Block.box(11,7,4,13,9,16),
			Block.box(13,7,3,16,9,5),
			Block.box(12,7,3,13,9,4),
			Block.box(13,7,5,14,9,6)
	);
	public static final VoxelShape CORNER_W_N = Shapes.or(
			Block.box(11,7,0,13,9,12),
			Block.box(13,7,11,16,9,13),
			Block.box(12,7,12,13,9,13),
			Block.box(13,7,10,14,9,11)
	);

	public static final VoxelShape CORNER_U_N = Shapes.or(
			Block.box(7,3,0,9,5,12),
			Block.box(7,0,11,9,3,13),
			Block.box(7,3,12,9,4,13),
			Block.box(7,2,10,9,3,11)
	);
	public static final VoxelShape CORNER_U_S = Shapes.or(
			Block.box(7,3,4,9,5,16),
			Block.box(7,0,3,9,3,5),
			Block.box(7,3,3,9,4,4),
			Block.box(7,2,5,9,3,6)
	);

	public static final VoxelShape CORNER_U_E = Shapes.or(
			Block.box(4,3,7,16,5,9),
			Block.box(3,0,7,5,3,9),
			Block.box(3,3,7,4,4,9),
			Block.box(5,2,7,6,3,9)
	);
	public static final VoxelShape CORNER_U_W = Shapes.or(
			Block.box(0,3,7,12,5,9),
			Block.box(11,0,7,13,3,9),
			Block.box(12,3,7,13,4,9),
			Block.box(10,2,7,11,3,9)
	);
	public static final VoxelShape CORNER_D_N = Shapes.or(
			Block.box(7,11,0,9,13,12),
			Block.box(7,13,11,9,16,13),
			Block.box(7,12,12,9,13,13),
			Block.box(7,13,10,9,14,11)
	);
	public static final VoxelShape CORNER_D_S = Shapes.or(
			Block.box(7,11,4,9,13,16),
			Block.box(7,13,3,9,16,5),
			Block.box(7,12,3,9,13,4),
			Block.box(7,13,5,9,14,6)
	);//(7,11,4,9,13,16),(7,13,3,9,16,5),(7,12,3,9,13,4),(7,13,5,9,14,6)
	public static final VoxelShape CORNER_D_E = Shapes.or(
			Block.box(4,11,7,16,13,9),
			Block.box(3,13,7,5,16,9),
			Block.box(3,12,7,4,13,9),
			Block.box(5,13,7,6,14,9)
	);//(4,11,7,16,13,9),(3,13,7,5,16,9),(3,12,7,4,13,9),(5,13,7,6,14,9)
	public static final VoxelShape CORNER_D_W = Shapes.or(
			Block.box(0,11,7,12,13,9),
			Block.box(11,13,7,13,16,9),
			Block.box(12,12,7,13,13,9),
			Block.box(10,13,7,11,14,9)
	);//(0,11,7,12,13,9),(11,13,7,13,16,9),(12,12,7,13,13,9),(10,13,7,11,14,9)
	//  Alone
	public static final VoxelShape ALONE_N_UD = Shapes.or(
			Block.box(7,4,10,9,12,12),
			Block.box(7,11,12,9,13,16),
			Block.box(7,3,12,9,5,16),
			Block.box(7,12,11,9,13,12),
			Block.box(7,3,11,9,4,12),
			Block.box(7,10,12,9,11,13),
			Block.box(7,5,12,9,6,13)
	);//(7,4,10,9,12,12),(7,11,12,9,13,16),(7,3,12,9,5,16),(7,12,11,9,13,12),(7,3,11,9,4,12),(7,10,12,9,11,13),(7,5,12,9,6,13)
	public static final VoxelShape ALONE_N_EW = Shapes.or(
			Block.box(4,7,10,12,9,12),
			Block.box(3,7,12,5,9,16),
			Block.box(11,7,12,13,9,16),
			Block.box(3,7,11,4,9,12),
			Block.box(12,7,11,13,9,12),
			Block.box(5,7,12,6,9,13),
			Block.box(10,7,12,11,9,13)
	);//(4,7,10,12,9,12),(3,7,12,5,9,16),(11,7,12,13,9,16),(3,7,11,4,9,12),(12,7,11,13,9,12),(5,7,12,6,9,13),(10,7,12,11,9,13)
	public static final VoxelShape ALONE_S_UD = Shapes.or(
			Block.box(7,4,4,9,12,6),
			Block.box(7,11,0,9,13,4),
			Block.box(7,3,0,9,5,4),
			Block.box(7,12,4,9,13,5),
			Block.box(7,3,4,9,4,5),
			Block.box(7,10,3,9,11,4),
			Block.box(7,5,3,9,6,4)
	);//(7,4,4,9,12,6),(7,11,0,9,13,4),(7,3,0,9,5,4),(7,12,4,9,13,5),(7,3,4,9,4,5),(7,10,3,9,11,4),(7,5,3,9,6,4)
	public static final VoxelShape ALONE_S_EW = Shapes.or(
			Block.box(4,7,4,12,9,6),
			Block.box(3,7,0,5,9,4),
			Block.box(11,7,0,13,9,4),
			Block.box(3,7,4,4,9,5),
			Block.box(12,7,4,13,9,5),
			Block.box(5,7,3,6,9,4),
			Block.box(10,7,3,11,9,4)
	);//(4,7,4,12,9,6),(3,7,0,5,9,4),(11,7,0,13,9,4),(3,7,4,4,9,5),(12,7,4,13,9,5),(5,7,3,6,9,4),(10,7,3,11,9,4)
	public static final VoxelShape ALONE_E_UD = Shapes.or(
			Block.box(4,4,7,6,12,9),
			Block.box(0,11,7,4,13,9),
			Block.box(0,3,7,4,5,9),
			Block.box(4,12,7,5,13,9),
			Block.box(4,3,7,5,4,9),
			Block.box(3,10,7,4,11,9),
			Block.box(3,5,7,4,6,9)
	);//(4,4,7,6,12,9),(0,11,7,4,13,9),(0,3,7,4,5,9),(4,12,7,5,13,9),(4,3,7,5,4,9),(3,10,7,4,11,9),(3,5,7,4,6,9)
	public static final VoxelShape ALONE_E_NS = Shapes.or(
			Block.box(4,7,4,6,9,12),
			Block.box(0,7,3,4,9,5),
			Block.box(0,7,11,4,9,13),
			Block.box(4,7,3,5,9,4),
			Block.box(4,7,12,5,9,13),
			Block.box(3,7,5,4,9,6),
			Block.box(3,7,10,4,9,11)
	);//(4,7,4,6,9,12),(0,7,3,4,9,5),(0,7,11,4,9,13),(4,7,3,5,9,4),(4,7,12,5,9,13),(3,7,5,4,9,6),(3,7,10,4,9,11)
	public static final VoxelShape ALONE_W_UD = Shapes.or(
			Block.box(10,4,7,12,12,9),
			Block.box(12,11,7,16,13,9),
			Block.box(12,3,7,16,5,9),
			Block.box(11,12,7,12,13,9),
			Block.box(11,3,7,12,4,9),
			Block.box(12,10,7,13,11,9),
			Block.box(12,5,7,13,6,9)
	);//(10,4,7,12,12,9),(12,11,7,16,13,9),(12,3,7,16,5,9),(12,12,7,11,13,9),(11,3,7,12,4,9),(12,10,7,13,11,9),(12,5,7,13,6,9)
	public static final VoxelShape ALONE_W_NS = Shapes.or(
			Block.box(10,7,4,12,9,12),
			Block.box(12,7,3,16,9,5),
			Block.box(12,7,11,16,9,13),
			Block.box(11,7,3,12,9,4),
			Block.box(11,7,12,12,9,13),
			Block.box(12,7,5,13,9,6),
			Block.box(12,7,10,13,9,11)
	);//(10,7,4,12,9,12),(12,7,3,16,9,5),(12,7,11,16,9,13),(11,7,3,12,9,4),(11,7,12,12,9,13),(12,7,5,13,9,6),(12,7,10,13,9,11)
	public static final VoxelShape ALONE_U_NS =  Shapes.or(
			Block.box(7,4,4,9,6,12),
			Block.box(7,0,3,9,4,5),
			Block.box(7,0,11,9,4,13),
			Block.box(7,4,3,9,5,4),
			Block.box(7,4,12,9,5,13),
			Block.box(7,3,5,9,4,6),
			Block.box(7,3,10,9,4,11)
	);//(7,4,4,9,6,12),(7,0,3,9,4,5),(7,0,11,9,4,13),(7,4,3,9,5,4),(7,4,12,9,5,13),(7,3,5,9,4,6),(7,3,10,9,4,11)
	public static final VoxelShape ALONE_U_EW =  Shapes.or(
			Block.box(4,4,7,12,6,9),
			Block.box(3,0,7,5,4,9),
			Block.box(11,0,7,13,4,9),
			Block.box(3,4,7,4,5,9),
			Block.box(12,4,7,13,5,9),
			Block.box(5,3,7,6,4,9),
			Block.box(10,3,7,11,4,9)
	);//(4,4,7,12,6,9),(3,0,7,5,4,9),(11,0,7,13,4,9),(3,4,7,4,5,9),(12,4,7,13,5,9),(5,3,7,6,4,9),(10,3,7,11,4,9)
	public static final VoxelShape ALONE_D_NS = Shapes.or(
			Block.box(7,10,4,9,12,12),
			Block.box(7,12,3,9,16,5),
			Block.box(7,12,11,9,16,13),
			Block.box(7,11,3,9,12,4),
			Block.box(7,11,12,9,12,13),
			Block.box(7,12,5,9,13,6),
			Block.box(7,12,10,9,13,11)
	);//(7,10,4,9,12,12),(7,12,3,9,16,5),(7,12,11,9,16,13),(7,11,3,9,12,4),(7,11,12,9,12,13),(7,12,5,9,13,6),(7,12,10,9,13,11)
	public static final VoxelShape ALONE_D_EW = Shapes.or(
			Block.box(4,10,7,12,12,9),
			Block.box(3,12,7,5,16,9),
			Block.box(11,12,7,13,16,9),
			Block.box(3,11,7,4,12,9),
			Block.box(12,11,7,13,12,9),
			Block.box(5,12,7,6,13,9),
			Block.box(10,12,7,11,13,9)
	);//(4,10,7,12,12,9),(3,12,7,5,16,9),(11,12,7,13,16,9),(3,11,7,4,12,9),(12,11,7,13,12,9),(5,12,7,6,13,9),(10,12,7,11,13,9)
	public BarBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f).noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false)
				.setValue(UP, false).setValue(DOWN, false).setValue(NORTH, false)
				.setValue(SOUTH, false).setValue(EAST, false).setValue(WEST, false)
				.setValue(VERTICAL, false).setValue(HORIZONTALNTOS, true));
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
		return true;
	}

	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.getBlock() == this ? true : super.skipRendering(state, adjacentBlockState, side);
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, NORTH, SOUTH, EAST, WEST, UP, DOWN, VERTICAL, HORIZONTALNTOS);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
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
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		switch (state.getValue(FACING)){
			case DOWN:
				if (state.getValue(HORIZONTALNTOS)) {
					if (state.getValue(NORTH)){
						if(state.getValue(SOUTH)) return STRAIGHT_D_NS;//return straight N to S, else return corner N without S
						else return CORNER_D_N;
					} else if (state.getValue(SOUTH)){
						return CORNER_D_S;//return corner S without N
					} else {
						return ALONE_D_NS;//return alone N to S
					}
				} else {
					if (state.getValue(EAST)){
						if (state.getValue(WEST)) return STRAIGHT_D_EW;//return straight E to W, else return corner E without W
						else return CORNER_D_E;
					} else if (state.getValue(WEST)){
						return CORNER_D_W;//return corner W without E
					} else {
						return ALONE_D_EW;//return alone E to W
					}
				}
			case UP:
				if (state.getValue(HORIZONTALNTOS)) {
					if (state.getValue(NORTH)){
						if(state.getValue(SOUTH)) return STRAIGHT_U_NS;//return straight N to S, else return corner N without S
						else return CORNER_U_N;
					} else if (state.getValue(SOUTH)){
						return CORNER_U_S;//return corner S without N
					} else {
						return ALONE_U_NS;//return alone N to S
					}
				} else {
					if (state.getValue(EAST)){
						if (state.getValue(WEST)) return STRAIGHT_U_EW;//return straight E to W, else return corner E without W
						else return CORNER_U_E;
					} else if (state.getValue(WEST)){
						return CORNER_U_W;//return corner W without E
					} else {
						return ALONE_U_EW;//return alone E to W
					}
				}
			default:
			case NORTH:
				if (!state.getValue(VERTICAL)) {
					if (state.getValue(EAST)){
						if (state.getValue(WEST)) return STRAIGHT_N_EW;//return straight E to W, else return corner E without W
						else return CORNER_N_E;
					} else if (state.getValue(WEST)){
						return CORNER_N_W;//return corner W without E
					} else {
						return ALONE_N_EW;//return alone E to W
					}
				} else {
					if (state.getValue(UP)){
						if (state.getValue(DOWN)) return STRAIGHT_N_UD;//return straight U to D, else return corner U without D
						else return CORNER_N_U;
					} else if (state.getValue(DOWN)){
						return CORNER_N_D;//return corner D without U
					} else {
						return ALONE_N_UD;//return alone U to D
					}
				}
			case SOUTH:
				if (!state.getValue(VERTICAL)) {
					if (state.getValue(EAST)){
						if (state.getValue(WEST)) return STRAIGHT_S_EW;//return straight E to W, else return corner E without W
						else return CORNER_S_E;
					} else if (state.getValue(WEST)){
						return CORNER_S_W;//return corner W without E
					} else {
						return ALONE_S_EW;//return alone E to W
					}
				} else {
					if (state.getValue(UP)){
						if (state.getValue(DOWN)) return STRAIGHT_S_UD;//return straight U to D, else return corner U without D
						else return CORNER_S_U;
					} else if (state.getValue(DOWN)){
						return CORNER_S_D;//return corner D without U
					} else {
						return ALONE_S_UD;//return alone U to D
					}
				}
			case WEST:
				if (!state.getValue(VERTICAL)) {
					if (state.getValue(NORTH)){
						if(state.getValue(SOUTH)) return STRAIGHT_W_NS;//return straight N to S, else return corner N without S
						else return CORNER_W_N;
					} else if (state.getValue(SOUTH)){
						return CORNER_W_S;//return corner S without N
					} else {
						return ALONE_W_NS;//return alone N to S
					}
				} else {
					if (state.getValue(UP)){
						if (state.getValue(DOWN)) return STRAIGHT_W_UD;//return straight U to D, else return corner U without D
						else return CORNER_W_U;
					} else if (state.getValue(DOWN)){
						return CORNER_W_D;//return corner D without U
					} else {
						return ALONE_W_UD;//return alone U to D
					}
				}
			case EAST:
				if (!state.getValue(VERTICAL)) {
					if (state.getValue(NORTH)){
						if(state.getValue(SOUTH)) return STRAIGHT_E_NS;//return straight N to S, else return corner N without S
						else return CORNER_E_N;
					} else if (state.getValue(SOUTH)){
						return CORNER_E_S;//return corner S without N
					} else {
						return ALONE_E_NS;//return alone N to S
					}
				} else {
					if (state.getValue(UP)){
						if (state.getValue(DOWN)) return STRAIGHT_E_UD;//return straight U to D, else return corner U without D
						else return CORNER_E_U;
					} else if (state.getValue(DOWN)){
						return CORNER_E_D;//return corner D without U
					} else {
						return ALONE_E_UD;//return alone U to D
					}
				}
	}
}
	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();

		BarDirectionChangeProcedure.execute(world, x, y, z, blockstate, entity);
		BarConnectionProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		BarConnectionProcedure.execute(
			world, pos.getX(), pos.getY(), pos.getZ()
		);
	}
	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving){
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		BarConnectionProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}
