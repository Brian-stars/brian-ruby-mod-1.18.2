package net.Brian_stars.brian_ruby_mod;

import net.Brian_stars.brian_ruby_mod.items.Ruby;
import net.Brian_stars.brian_ruby_mod.tools.RubyToolsMaterial;
import net.Brian_stars.brian_ruby_mod.tools.weapon.RubySword;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class BrianRubyMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "brian_ruby_mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//Item
	public static final Item RUBY = new Ruby(new FabricItemSettings().group(ItemGroups.RUBY));
	public static final RubySword RUBY_SWORD = new RubySword(new RubyToolsMaterial(),8,-0.5f,new Item.Settings().group(ItemGroups.RUBY));
	//Block
	public static final Block RUBY_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F,1200.0f).sounds(BlockSoundGroup.STONE).requiresTool());
	public static final Block RUBY_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0F,1200.0f).sounds(BlockSoundGroup.STONE).requiresTool());

	//BlockItem
	public static final BlockItem RUBY_ORE_ITEM = new BlockItem(RUBY_ORE,new FabricItemSettings().group(ItemGroups.RUBY));
	public static final BlockItem RUBY_BLOCK_ITEM = new BlockItem(RUBY_BLOCK,new FabricItemSettings().group(ItemGroups.RUBY));



	private static ConfiguredFeature<?, ?> OVERWORLD_RUBY_ORE_CONFIGURED_FEATURE = new ConfiguredFeature
			(Feature.ORE, new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
					BrianRubyMod.RUBY_ORE.getDefaultState(),
					6)); // 矿脉大小

	public static PlacedFeature OVERWORLD_RUBY_ORE_PLACED_FEATURE = new PlacedFeature(
			RegistryEntry.of(OVERWORLD_RUBY_ORE_CONFIGURED_FEATURE),
			Arrays.asList(
					CountPlacementModifier.of(5), // 每个区块的矿脉数量
					SquarePlacementModifier.of(), // 水平传播
					HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(13))
			)); // 高度


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//ItemRegister
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"ruby"),RUBY);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"ruby_sword"),RUBY_SWORD);

		//BlockItemRegister
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"ruby_ore"),RUBY_ORE_ITEM);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"ruby_block"),RUBY_BLOCK_ITEM);
		//BlockRegister
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"ruby_ore"),RUBY_ORE);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"ruby_block"),RUBY_BLOCK);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(MOD_ID, "overworld_wool_ore"), OVERWORLD_RUBY_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MOD_ID, "overworld_wool_ore"),
				OVERWORLD_RUBY_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(MOD_ID, "overworld_wool_ore")));
	}
}
