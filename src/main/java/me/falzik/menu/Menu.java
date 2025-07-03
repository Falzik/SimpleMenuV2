package me.falzik.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface Menu extends InventoryHolder {

    void click(Player player, int slot);
    void setItem(int slot, ItemStack itemStack);
    void setItem(int slot, ItemStack itemStack, Consumer<Player> action);
    void setItem(int slot, ItemStack itemStack, Consumer<Player> action, Consumer<ItemStack> itemStackAction);
    void addDesign(Material material, String name);
    void onSetItems();
    boolean getCanClose();

    default void open(Player player) {
        onSetItems();
        player.openInventory(getInventory());
    }

}
