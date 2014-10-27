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
    private Color color;
    private WrappedEnchantment[] enchantments;


    private void setItem(ItemStack item){
        this.item = item;
    }

    /**
     * Wrap an itemstack for editing
     * @param item The desired item
     * @return Wrapped item
     */
    public static ItemBuilder wrap(ItemStack item){
        ItemBuilder instance =  new ItemBuilder();
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
        ItemBuilder instance = wrap(item);
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
     * Apply a color to the item.
     * @param color The desired color
     */
    public ItemBuilder color(Color color){
        if(!item.getType().name().toLowerCase().contains("leather_")) {
            System.out.println("ItemBuilder: Tried setting color to a non leather material. Ignoring");
            return this;
        }
        this.color = color;
        return this;
    }

    /**
     * Set the armor slot
     * @param slot The desired slot
     */
    public ItemBuilder slot(ArmorSlot slot){
        this.slot = slot;
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
        if(item.getType().name().toLowerCase().contains("leather_")) {
            LeatherArmorMeta lMeta = (LeatherArmorMeta) item.getItemMeta();
            lMeta.setColor(color);
            item.setItemMeta(lMeta);
        }
        return item;
    }

    /**
     * Gives the built item to the player.
     * @param player The player to give the item to
     */
    public void give(Player player){
        ItemStack item = build();
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
        builder.append("ItemBuilder: {");
        builder.append("\n");
        builder.append("name: ").append(name);
        builder.append("\n");
        builder.append("lore: ");
        if(lore == null){
            builder.append("null");
        } else {
            builder.append("[");
            for (int i = 0; i < lore.length; i++) {
                String s = lore[i];
                builder.append(s);
                if ((i + 1) != lore.length) {
                    builder.append(", ");
                }
            }
            builder.append("]");
        }
        builder.append("\n");
        builder.append("color: ").append(color == null ? "null" : "Red: " + color.getRed() + " Green: " + color.getGreen() + " Blue: " + color.getBlue());
        builder.append("\n");
        builder.append("slot: ").append(slot == null ? "null" : slot.name());
        builder.append("\n");
        builder.append("enchantments: ");
        if(enchantments == null){
            builder.append("null");
        } else {
            builder.append("\n");
            for(int i = 0; i < enchantments.length; i++){
                WrappedEnchantment enchantment = enchantments[i];
                builder.append("  name: ").append(enchantment.getEnchantment().getName())
                        .append("\n")
                        .append("  level: ").append(enchantment.getLevel())
                        .append("\n")
                        .append("  override: ").append(enchantment.isOverride());
                if ((i + 1) != enchantments.length) {
                    builder.append(", ");
                }
            }
        }
        builder.append("\n");
        builder.append("}");
        return builder.toString();
    }
}
