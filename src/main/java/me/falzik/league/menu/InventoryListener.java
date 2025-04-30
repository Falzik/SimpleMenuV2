package me.falzik.league.menu;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Author: Falzik
 * Created time: 27.04.2025 19:44
 */

public class InventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        final Inventory clickedInventory = e.getClickedInventory();
        if(clickedInventory == null) return;

        if(!(clickedInventory.getHolder() instanceof final Menu menu)) return;

        final Player player = (Player) e.getWhoClicked();

        menu.click(player, e.getSlot());
        e.setCancelled(true);
    }

}
