package io.github.asablock.oregenplus;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GravelBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class GravelOreBlock extends GravelBlock {
    private final UniformIntProvider experienceDropped;

    public GravelOreBlock(AbstractBlock.Settings settings) {
        this(settings, UniformIntProvider.create(0, 0));
    }

    public GravelOreBlock(AbstractBlock.Settings settings, UniformIntProvider experienceDropped) {
        super(settings);
        this.experienceDropped = experienceDropped;
    }

    @SuppressWarnings("deprecation")
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        int i;
        super.onStacksDropped(state, world, pos, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0 && (i = this.experienceDropped.get(world.random)) > 0) {
            this.dropExperience(world, pos, i);
        }
    }
}
