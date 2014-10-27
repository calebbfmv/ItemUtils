package org.nationsmc.itemutils;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

/**
 * @author Tim [calebbfmv]
 * Created by Tim [calebbfmv] on 10/22/2014.
 */
public class ItemBuilder {

    private ItemStack item;
    private String name;
    private String[] lore;
    private ArmorSlot slot;
    private WrappedEnchantment[] enchantments;

    private static ItemBuilder instance = new ItemBuilder();

    private void setItem(ItemStack item){
        this.item = item;
    }

    /**
     * Wrap an itemstack for editing
     * @param item The desired item
     * @return Wrapped item
     */
    public static ItemBuilder wrap(ItemStack item){
        instance.lore = null;
        instance.name = null;
        instance.slot = null;
        instance.item = null;
        instance.enchantments = null;
        instance.setItem(item);
        return instance;
    }

    /**
     * Wrap an itemstack for editing
     * @param item The desired item
     * @param slot The armor slot
     * @return Wrapped Item
     */
    public static ItemBuilder wrap(ItemStack item, ArmorSlot slot){
        wrap(item);
        instance.setSlot(slot);
        return instance;
    }

    /**
     * Add a name to the itemstack
     * @param name The desired name
     */
    public ItemBuilder name(String name){
        this.name = name;
        return this;
    }

    /**
     * Add lore to the itemstack dynamically
     * @param lore The desired lore
     */
    public ItemBuilder lore(String... lore){
        this.lore = lore;
        return this;
    }

    /**
     * Apply enchantments to the item
     * @param enchantments An array of WrappedEnchantment to be applied on building
     */
    public ItemBuilder enchant(WrappedEnchantment... enchantments){
        this.enchantments = enchantments;
        return this;
    }

    /**
     * Built the item
     * @return the new item stack
     */
    public ItemStack build(){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if(lore != null) {
            meta.setLore(Arrays.asList(lore));
        }
        if(enchantments != null){
            for(WrappedEnchantment enchantment : enchantments){
                meta.addEnchant(enchantment.getEnchantment(), enchantment.getLevel(), enchantment.isOverride());
            }
        }
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Build the itemstack with a color.
     * @param color the desired color for the item
     * @return a build item, with color. Will return a normal item if it is not a leather armor piece.
     */
    public ItemStack build(Color color){
        if(!item.getType().name().toLowerCase().contains("leather_")){
            return build();
        }
        item = build();
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Gives the built item to the player.
     * @param player The player to give the item to
     * @param color optional color for leather armor
     */
    public void give(Player player, Color color){
        ItemStack item = build();
        if(color != null){
            item = build(color);
        }
        ArmorSlot slot = getSlot();
        if(slot == null){
            player.getInventory().addItem(item);
            return;
        }
        switch(slot){
            case BOOTS:
                player.getInventory().setBoots(item);
                break;
            case PANTS:
                player.getInventory().setLeggings(item);
                break;
            case CHESTPLATE:
                player.getInventory().setChestplate(item);
                break;
            case HELMET:
                player.getInventory().setHelmet(item);
                break;
        }
    }

    public ArmorSlot getSlot() {
        return slot;
    }

    private void setSlot(ArmorSlot slot) {
        this.slot = slot;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        if(lore == null){
            builder.append("null");
        } else {
            for(int i = 0; i < lore.length; i++){
                String s = lore[i];
                builder.append(s);
                if((i + 1) != lore.length){
                    builder.append(", ");
                }
            }
        }
        return "ItemFactory: Name: " + name + " Lore (\", \" indicates line break): " + builder.toString() + " ItemType: " + item.getType().name();
    }
}
