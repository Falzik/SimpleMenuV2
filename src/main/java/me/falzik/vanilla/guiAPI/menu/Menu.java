package me.falzik.vanilla.guiAPI.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

public interface Menu extends InventoryHolder {

    void click(Player player, int slot);
    void setItem(int slot, ItemStack item, ButtonResult result);
    void setItem(int slot, ItemStack item, ButtonResult result,  Consumer<Player> action);
    void setDesign(Material material, String name, ButtonResult result);
    void onSetItems();

    default void open(Player player) {
        onSetItems();
        player.openInventory(getInventory());
    }

}
