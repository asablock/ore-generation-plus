package io.github.asablock.oregenplus;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import static io.github.asablock.oregenplus.OreGenPlus.*;

import java.util.List;

public class OreGenPlusFeatures {
    private static final RuleTest END_STONE_ORE_REPLACEABLES = new BlockMatchRuleTest(Blocks.END_STONE);

    public static void registerFeatures() {
        registerNetherrack(netherrack_coal_ore, 10, UniformIntProvider.create(13, 28), YOffset.getBottom(), YOffset.fixed(192));
        registerNetherrack(netherrack_copper_ore, 7, UniformIntProvider.create(8, 16), YOffset.fixed(46), YOffset.fixed(84));
        registerNetherrack(netherrack_diamond_ore, 5, ConstantIntProvider.create(4), YOffset.aboveBottom(11), YOffset.fixed(58));
        registerNetherrack(netherrack_iron_ore, 9, ConstantIntProvider.create(26), YOffset.fixed(34), YOffset.fixed(93));
        registerNetherrack(netherrack_redstone_ore, 9, ConstantIntProvider.create(26), YOffset.fixed(34), YOffset.fixed(61));
        registerNetherrack(netherrack_lapis_ore, 4, ConstantIntProvider.create(6), YOffset.aboveBottom(3), YOffset.fixed(32));

        registerEndStone(end_stone_coal_ore, 10, UniformIntProvider.create(13, 28), YOffset.getBottom(), YOffset.fixed(192));
        registerEndStone(end_stone_copper_ore, 7, UniformIntProvider.create(8, 16), YOffset.fixed(46), YOffset.fixed(84));
        registerEndStone(end_stone_diamond_ore, 5, ConstantIntProvider.create(4), YOffset.aboveBottom(11), YOffset.fixed(58));
        registerEndStone(end_stone_iron_ore, 9, ConstantIntProvider.create(26), YOffset.fixed(34), YOffset.fixed(93));
        registerEndStone(end_stone_redstone_ore, 9, ConstantIntProvider.create(26), YOffset.fixed(34), YOffset.fixed(61));
        registerEndStone(end_stone_lapis_ore, 4, ConstantIntProvider.create(6), YOffset.aboveBottom(3), YOffset.fixed(32));
    }

    public static void registerNetherrack(Block block, int size, IntProvider count, YOffset min, YOffset max) {
        ConfiguredFeature<?, ?> cf = new ConfiguredFeature<>(Feature.ORE,
                new OreFeatureConfig(OreConfiguredFeatures.NETHERRACK, block.getDefaultState(), size));
        PlacedFeature pf = new PlacedFeature(RegistryEntry.of(cf), List.of(
                CountPlacementModifier.of(count),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(min, max)
        ));

        Identifier fid = Registry.BLOCK.getId(block);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, fid, cf);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, fid, pf);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, fid));
    }

    public static void registerEndStone(Block block, int size, IntProvider count, YOffset min, YOffset max) {
        ConfiguredFeature<?, ?> cf = new ConfiguredFeature<>(Feature.ORE,
                new OreFeatureConfig(END_STONE_ORE_REPLACEABLES, block.getDefaultState(), size));
        PlacedFeature pf = new PlacedFeature(RegistryEntry.of(cf), List.of(
                CountPlacementModifier.of(count),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(min, max)
        ));

        Identifier fid = Registry.BLOCK.getId(block);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, fid, cf);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, fid, pf);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, fid));
    }
}
