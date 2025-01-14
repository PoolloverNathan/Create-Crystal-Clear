package com.cyvack.create_crystal_clear.blocks.glass_casings;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class GlassCasing extends GlassBlock {

	public GlassCasing(Properties p_53640_) {
		super(p_53640_);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction side) {
		return ((pState.getBlock() instanceof GlassCasing) && (pAdjacentBlockState.getBlock() instanceof GlassCasing));
	}

	// @Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidState) {
		return true;
	}
}
