package com.cyvack.create_crystal_clear.blocks.glass_encased_shaft;

import com.cyvack.create_crystal_clear.blocks.ModBlocks;
import com.cyvack.create_crystal_clear.blocks.compat.AlloyedCompatBlocks;
import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.cyvack.create_crystal_clear.tile_entities.ModtileEntities;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.AbstractEncasedShaftBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.schematics.requirement.ISpecialBlockItemRequirement;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import com.simibubi.create.foundation.block.IBE;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class GlassEncasedShaftBlock extends AbstractEncasedShaftBlock
		implements IBE<KineticBlockEntity>, ISpecialBlockItemRequirement {

	private BlockEntry<com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing> GlassCasing;

	public static GlassEncasedShaftBlock andesiteglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.ANDESITE_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock andesiteclearglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.ANDESITE_CLEAR_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock brassglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.BRASS_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock brassclearglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.BRASS_CLEAR_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock trainglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.TRAIN_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock trainclearglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.TRAIN_CLEAR_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock steelglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, AlloyedCompatBlocks.STEEL_GLASS_CASING);
	}

	public GlassEncasedShaftBlock(Properties properties, BlockEntry<GlassCasing> GlassCasing) {
		super(properties);
		this.GlassCasing = GlassCasing;
	}

	public BlockEntry<GlassCasing> getCasing() {
		return GlassCasing;
	}

	@Override
	public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {
	}

	@Override
	public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
		if (context.getLevel().isClientSide)
			return InteractionResult.SUCCESS;
		context.getLevel()
				.levelEvent(2001, context.getClickedPos(), Block.getId(state));
		KineticBlockEntity.switchToBlockState(context.getLevel(), context.getClickedPos(),
				AllBlocks.SHAFT.getDefaultState()
						.setValue(AXIS, state.getValue(AXIS)));
		return InteractionResult.SUCCESS;
	}

	// @Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos,
			Player player) {
		if (target instanceof BlockHitResult)
			return ((BlockHitResult) target).getDirection()
					.getAxis() == getRotationAxis(state) ? AllBlocks.SHAFT.asStack() : getCasing().asStack();
		return this.asItem().getDefaultInstance();
	}

	@Override
	public ItemRequirement getRequiredItems(BlockState state, BlockEntity te) {
		return ItemRequirement.of(AllBlocks.SHAFT.getDefaultState(), te);
	}

	@Override
	public Class<KineticBlockEntity> getBlockEntityClass() {
		return KineticBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
		return (BlockEntityType<? extends KineticBlockEntity>) ModtileEntities.GLASS_ENCASED_SHAFT.get();
	}

	// @Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos,
			FluidState fluidState) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Environment(EnvType.CLIENT)
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction side) {
		return ((pState.getBlock() instanceof GlassEncasedShaftBlock)
				&& (pAdjacentBlockState.getBlock() instanceof GlassEncasedShaftBlock));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return true;
	}
}
