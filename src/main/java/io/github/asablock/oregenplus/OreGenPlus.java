package io.github.asablock.oregenplus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class OreGenPlus implements ModInitializer {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(id("oregenplus"), groupIconSupplier());

    public static Block gravel_gold_ore;
    public static Block gravel_iron_ore;
    public static Block gravel_coal_ore;
    public static Block gravel_lapis_ore;
    public static Block gravel_diamond_ore;
    public static Block gravel_redstone_ore;
    public static Block gravel_copper_ore;

    public static Block netherrack_iron_ore;
    public static Block netherrack_coal_ore;
    public static Block netherrack_lapis_ore;
    public static Block netherrack_diamond_ore;
    public static Block netherrack_redstone_ore;
    public static Block netherrack_copper_ore;

    public static Block end_stone_gold_ore;
    public static Block end_stone_iron_ore;
    public static Block end_stone_coal_ore;
    public static Block end_stone_lapis_ore;
    public static Block end_stone_diamond_ore;
    public static Block end_stone_redstone_ore;
    public static Block end_stone_copper_ore;

    @Override
    public void onInitialize() {
        gravel_gold_ore = createGravel("gold");
        gravel_iron_ore = createGravel("iron");
        gravel_coal_ore = createGravel("coal");
        gravel_lapis_ore = createGravel("lapis");
        gravel_diamond_ore = createGravel("diamond");
        gravel_redstone_ore = createGravel("redstone");
        gravel_copper_ore = createGravel("copper");

        netherrack_iron_ore = createNetherrack("iron");
        netherrack_coal_ore = createNetherrack("coal");
        netherrack_lapis_ore = createNetherrack("lapis");
        netherrack_diamond_ore = createNetherrack("diamond");
        netherrack_redstone_ore = createNetherrack("redstone");
        netherrack_copper_ore = createNetherrack("copper");

        end_stone_gold_ore = createEndStone("gold");
        end_stone_iron_ore = createEndStone("iron");
        end_stone_coal_ore = createEndStone("coal");
        end_stone_lapis_ore = createEndStone("lapis");
        end_stone_diamond_ore = createEndStone("diamond");
        end_stone_redstone_ore = createEndStone("redstone");
        end_stone_copper_ore = createEndStone("copper");

        OreGenPlusFeatures.registerFeatures();
    }

    private static Block createGravel(String id) {
        Block b = new GravelOreBlock(AbstractBlock.Settings.copy(Blocks.GRAVEL).requiresTool().strength(1.75f, 0.5f));
        Identifier identifier = id(String.format("gravel_%s_ore", id));
        Registry.register(Registry.BLOCK, identifier, b);
        Registry.register(Registry.ITEM, identifier, new BlockItem(b, new Item.Settings().group(ITEM_GROUP)));
        return b;
    }

    private static Block createNetherrack(String id) {
        Block b = new OreBlock(AbstractBlock.Settings.copy(Blocks.NETHERRACK).requiresTool().strength(3.0f, 3.0f));
        Identifier identifier = id(String.format("netherrack_%s_ore", id));
        Registry.register(Registry.BLOCK, identifier, b);
        Registry.register(Registry.ITEM, identifier, new BlockItem(b, new Item.Settings().group(ITEM_GROUP)));
        return b;
    }
    
    private static Block createEndStone(String id) {
        Block b = new OreBlock(AbstractBlock.Settings.copy(Blocks.END_STONE).requiresTool().strength(4.5f, 6.25f));
        Identifier identifier = id(String.format("end_stone_%s_ore", id));
        Registry.register(Registry.BLOCK, identifier, b);
        Registry.register(Registry.ITEM, identifier, new BlockItem(b, new Item.Settings().group(ITEM_GROUP)));
        return b;
    }

    public static Identifier id(String path) {
        return new Identifier("oregenplus", path);
    }

    private static Supplier<ItemStack> groupIconSupplier() {
        return () -> new ItemStack(netherrack_diamond_ore.asItem());
    }

    public static List<OreFeatureConfig.Target> extend(List<OreFeatureConfig.Target> src, OreFeatureConfig.Target rest) {
        List<OreFeatureConfig.Target> list = new ArrayList<>(src);
        list.add(rest);
        return Collections.unmodifiableList(list);
    }
}
