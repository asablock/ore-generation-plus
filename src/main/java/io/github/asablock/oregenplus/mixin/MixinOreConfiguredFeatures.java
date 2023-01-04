package io.github.asablock.oregenplus.mixin;

import io.github.asablock.oregenplus.OreGenPlus;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(OreConfiguredFeatures.class)
public class MixinOreConfiguredFeatures {
    @Shadow @Final @Mutable public static List<OreFeatureConfig.Target> IRON_ORES;

    @Shadow @Final @Mutable public static List<OreFeatureConfig.Target> OVERWORLD_GOLD_ORES;

    @Shadow @Final @Mutable public static List<OreFeatureConfig.Target> DIAMOND_ORES;

    @Shadow @Final @Mutable public static List<OreFeatureConfig.Target> LAPIS_ORES;

    @Shadow @Final @Mutable public static List<OreFeatureConfig.Target> COPPER_ORES;

    @Shadow @Final @Mutable public static List<OreFeatureConfig.Target> COAL_ORES;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/gen/feature/OreConfiguredFeatures;COAL_ORES:Ljava/util/List;", shift = At.Shift.AFTER))
    private static void clinit(CallbackInfo ci) {
        RuleTest gravelOreReplaceable = new BlockMatchRuleTest(Blocks.GRAVEL);

        IRON_ORES = OreGenPlus.extend(IRON_ORES,
                OreFeatureConfig.createTarget(gravelOreReplaceable, OreGenPlus.gravel_iron_ore.getDefaultState()));
        OVERWORLD_GOLD_ORES = OreGenPlus.extend(OVERWORLD_GOLD_ORES,
                OreFeatureConfig.createTarget(gravelOreReplaceable, OreGenPlus.gravel_gold_ore.getDefaultState()));
        DIAMOND_ORES = OreGenPlus.extend(DIAMOND_ORES,
                OreFeatureConfig.createTarget(gravelOreReplaceable, OreGenPlus.gravel_diamond_ore.getDefaultState()));
        LAPIS_ORES = OreGenPlus.extend(LAPIS_ORES,
                OreFeatureConfig.createTarget(gravelOreReplaceable, OreGenPlus.gravel_lapis_ore.getDefaultState()));
        COPPER_ORES = OreGenPlus.extend(COPPER_ORES,
                OreFeatureConfig.createTarget(gravelOreReplaceable, OreGenPlus.gravel_copper_ore.getDefaultState()));
        COAL_ORES = OreGenPlus.extend(COAL_ORES,
                OreFeatureConfig.createTarget(gravelOreReplaceable, OreGenPlus.gravel_coal_ore.getDefaultState()));
    }
}
