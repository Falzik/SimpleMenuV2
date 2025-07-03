package me.falzik.menu;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Author: Falzik
 * Created time: 30.04.2025 16:01
 */

public abstract class SimpleMenu implements Menu {

    private final Inventory inventory;

    private final Map<Integer, Consumer<Player>> playerAction = new HashMap<>();
    private final Map<Integer, Consumer<ItemStack>> itemStackAction = new HashMap<>();
    private final Map<Integer, ItemStack> itemStackMap = new HashMap<>();

    public SimpleMenu() {
        this.inventory = Bukkit.createInventory(this, getRows().getSize(), ChatColor.translateAlternateColorCodes('&', getTitle()));
    }

    public abstract String getTitle();
    public abstract Rows getRows();
    public abstract boolean isCanClose();

    @Override
    public void click(Player player, int slot) {
        Consumer<Player> playerAction = this.playerAction.get(slot);
        Consumer<ItemStack> itemStackAction = this.itemStackAction.get(slot);
        ItemStack itemStack = this.itemStackMap.get(slot);

        if(playerAction != null) {
            playerAction.accept(player);
        }

        if(itemStackAction != null) {
            itemStackAction.accept(itemStack);
        }
    }

    @Override
    public void addDesign(Material material, String name) {
        for (int i = 0; i < getInventory().getSize(); i++) {
            if(getInventory().getItem(i) == null) {
                ItemStack itemStack = new ItemStack(material);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                itemStack.setItemMeta(itemMeta);

                setItem(i, itemStack);
            }
        }
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        setItem(slot, itemStack, (player -> {}), (itemStack1 -> {}));
    }

    @Override
    public void setItem(int slot, ItemStack itemStack, Consumer<Player> action) {
        setItem(slot, itemStack, action, (itemStack1 -> {}));
    }

    @Override
    public boolean getCanClose() {
        return isCanClose();
    }

    @Override
    public void setItem(int slot, ItemStack itemStack, Consumer<Player> action, Consumer<ItemStack> itemStackAction) {
        this.playerAction.put(slot, action);
        this.itemStackAction.put(slot, itemStackAction);
        this.itemStackMap.put(slot, itemStack);

        getInventory().setItem(slot, itemStack);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public enum Rows {

        ONE(1),
        TWO(2),
        THREE(3),
        FOURTH(4),
        FIVE(5);

        private final int size;


        Rows(int size) {
            this.size = size;
        }

        public int getSize() {
            return size * 9;
        }
    }
}
