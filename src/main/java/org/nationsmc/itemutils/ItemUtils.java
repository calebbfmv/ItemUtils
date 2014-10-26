package org.nationsmc.itemutils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Tim [calebbfmv]
 * Created by Tim [calebbfmv] on 10/26/2014.
 */
public class ItemUtils extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        GlowEnchant.register();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if(item == null || item.getType() == Material.AIR){
            return;
        }
        SpecialItem specialItem = SpecialItem.getFromItem(item);
        if(specialItem == null){
            return;
        }
        specialItem.onInteract(event);
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if(item == null || item.getType() == Material.AIR){
            return;
        }
        SpecialItem specialItem = SpecialItem.getFromItem(item);
        if(specialItem == null){
            return;
        }
        specialItem.onInteractEntity(event);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if(item == null || item.getType() == Material.AIR){
            return;
        }
        SpecialItem specialItem = SpecialItem.getFromItem(item);
        if(specialItem == null){
            return;
        }
        specialItem.onPlace(event);
    }
}
