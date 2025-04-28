package me.falzik.vanilla.guiAPI.listeners;


import me.falzik.vanilla.guiAPI.menu.ButtonResult;
import me.falzik.vanilla.guiAPI.menu.Menu;
import me.falzik.vanilla.guiAPI.menu.SimpleMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Author: Falzik
 * Created time: 27.04.2025 19:44
 */

public class InventoryListener implements Listener { //

    @EventHandler
    public void on(InventoryClickEvent e) {
        final Inventory clickedInventory = e.getClickedInventory();
        if(clickedInventory == null) return;

        if(!(clickedInventory.getHolder() instanceof final Menu menu)) return;

        final Player player = (Player) e.getWhoClicked();

        if(SimpleMenu.getItemClickResult().get(e.getSlot()) == ButtonResult.CANCEL) {
            e.setCancelled(true);
            menu.click(player, e.getSlot());
        } else if(SimpleMenu.getItemClickResult().get(e.getSlot()) == ButtonResult.CLOSE) {
            e.setCancelled(true);
            menu.click(player, e.getSlot());
        }
    }

}
