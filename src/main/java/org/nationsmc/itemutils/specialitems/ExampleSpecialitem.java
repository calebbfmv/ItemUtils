package org.nationsmc.itemutils.specialitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.nationsmc.itemutils.GlowEnchant;
import org.nationsmc.itemutils.ItemFactory;
import org.nationsmc.itemutils.WrappedEnchantment;

/**
 * @author Tim [calebbfmv]
 * Created by Tim [calebbfmv] on 10/26/2014.
 */
public class ExampleSpecialitem extends SpecialItem{

    public ExampleSpecialitem() {
        super(ItemFactory.wrap(new ItemStack(Material.EMERALD))
        .name(ChatColor.GREEN + "Sparkly Emerald")
        .lore(ChatColor.GOLD + "Sparkly!!!!")
        .enchant(new WrappedEnchantment(GlowEnchant.getGlowEnchant()))
        .build());
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        event.getPlayer().sendMessage("Sparkly!");
    }

    @Override
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        String name = event.getRightClicked().getType().name().toLowerCase();
        event.getPlayer().sendMessage("You right clicked " + (startsWithVowel(name, false) ? "an" : "a") + name);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        String name = event.getBlock().getType().name().toLowerCase();
        event.getPlayer().sendMessage("You placed " + (startsWithVowel(name, false) ? "an" : "a") + name);
    }

    private boolean startsWithVowel(String name, boolean yAndW) {
        name = name.toLowerCase();
        if(!yAndW) {
            return name.startsWith("a")
                  || name.startsWith("e")
                  || name.startsWith("o")
                  || name.startsWith("i")
                  || name.startsWith("u");
        }
        return name.startsWith("a")
              || name.startsWith("e")
              || name.startsWith("o")
              || name.startsWith("i")
              || name.startsWith("u")
              || name.startsWith("y")
              || name.startsWith("w");
    }
}
