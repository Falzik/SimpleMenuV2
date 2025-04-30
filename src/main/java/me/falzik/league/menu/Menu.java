package me.falzik.league.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * Author: Falzik
 * Simple Menu Here!
 */

public interface Menu extends InventoryHolder {

    void click(Player player, int slot);

    void setItem(int slot, ItemStack item);

    void setItem(int slot, ItemStack item, Consumer<Player> action);

    void setDesing(Material material, String name);

    void onSetItems();

    default void open(Player player) {
        onSetItems();
        player.openInventory(getInventory());
    }

}
