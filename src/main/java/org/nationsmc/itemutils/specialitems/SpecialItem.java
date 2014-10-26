package org.nationsmc.itemutils.specialitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Create an Item with special properties to be called on certain events
 * @author Tim [calebbfmv]
 * Created by Tim [calebbfmv] on 10/26/2014.
 */
public abstract class SpecialItem implements ISpecialItem {

    private ItemStack item;
    private static Map<ItemStack, SpecialItem> specialItemsCache = new HashMap<>();

    public SpecialItem(ItemStack item){
        this.item = item;
        specialItemsCache.put(item, this);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    /**
     * Get the SpecialItem cached for param ItemStack
     * @param itemStack The ItemStack to check the cache
     * @return The SpecialItem for this ItemStack (can be null)
     */
    public static SpecialItem getFromItem(ItemStack itemStack){
        return specialItemsCache.get(itemStack);
    }

    /**
     * Give the item to a player
     * @param player The player to give the item to
     */
    public void give(Player player){
        player.getInventory().addItem(getItem());
    }
}
