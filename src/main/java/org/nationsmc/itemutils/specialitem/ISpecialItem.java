package org.nationsmc.itemutils.specialitem;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Tim [calebbfmv]
 * Created by Tim [calebbfmv] on 10/26/2014.
 */
public interface ISpecialItem {
    /**
     * Get the item
     * @return the item
     */
    public ItemStack getItem();

    /**
     * Called on PlayerInteractEvent
     */
    public void onInteract(PlayerInteractEvent event);

    /**
     * Called upon right clicking an entity
     */
    public void onInteractEntity(PlayerInteractEntityEvent event);

    /**
     * Called when the player places a block
     */
    public void onPlace(BlockPlaceEvent event);
}
