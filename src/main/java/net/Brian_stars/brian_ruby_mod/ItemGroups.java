package net.Brian_stars.brian_ruby_mod;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroups {

    public static final ItemGroup RUBY = FabricItemGroupBuilder.build(
            new Identifier(BrianRubyMod.MOD_ID,"ruby"),
            () -> new ItemStack(BrianRubyMod.RUBY)
    );

}
