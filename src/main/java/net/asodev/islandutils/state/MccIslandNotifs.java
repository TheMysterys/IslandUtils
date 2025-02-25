package net.asodev.islandutils.state;

import net.asodev.islandutils.options.IslandOptions;
import net.asodev.islandutils.options.categories.CraftingOptions;
import net.asodev.islandutils.modules.crafting.state.CraftingItem;
import net.asodev.islandutils.modules.crafting.state.CraftingItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;

public class MccIslandNotifs {
    private static Component completedCrafts = Component.literal("Completed Crafts:").setStyle(Style.EMPTY.withBold(true).withColor(ChatFormatting.WHITE));

    public static List<Component> getNotifLines() {
        List<Component> components = new ArrayList<>();

        List<Component> craftingLists = new ArrayList<>();
        boolean anycomplete = false;
        CraftingOptions options = IslandOptions.getCrafting();
        if (options.isEnableCraftingNotifs() && options.isNotifyServerList()) { // "i'm a never-nester"
            for (CraftingItem item : CraftingItems.getItems()) {
                if (!item.isComplete()) continue;
                craftingLists.add(Component.literal("  ").append(item.getTitle()));
                anycomplete = true;
            }
        }

        if (anycomplete) {
            components.add(completedCrafts);
            components.addAll(craftingLists);
        }

        return components;
    }

}
