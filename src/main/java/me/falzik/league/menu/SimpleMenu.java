package me.falzik.league.menu;


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
 * Created time: 27.04.2025 19:36
 */

public abstract class SimpleMenu implements me.falzik.league.menu.Menu {

    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final Inventory inventory;

    public SimpleMenu(String title, Rows rows) {
        this.inventory = Bukkit.createInventory(this, rows.getSize(), ChatColor.translateAlternateColorCodes('&', title));
    }

    @Override
    public void click(Player player, int slot) {
        final Consumer<Player> action = this.actions.get(slot);

        if(action != null) {
            action.accept(player);
        }
    }

    @Override
    public void setDesing(Material material, String name) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                ItemStack itemStack = new ItemStack(material);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                itemStack.setItemMeta(itemMeta);
                setItem(i, itemStack);
            }
        }
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        setItem(slot, item, player -> {});
    }

    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        this.actions.put(slot, action);
        getInventory().setItem(slot, item);
    }

   public abstract void onSetItems();

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public enum Rows {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int size;

        Rows(int rows) {
            this.size = rows * 9;
        }

        public int getSize() {
            return size;
        }
    }
}
