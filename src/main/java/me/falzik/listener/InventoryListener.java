package me.falzik.listener;


import com.sun.source.tree.UsesTree;
import me.falzik.GuiAPI;
import me.falzik.menu.Menu;
import me.falzik.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import javax.crypto.MacSpi;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Falzik
 * Created time: 30.04.2025 16:20
 */

public class InventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();

        if(!(e.getInventory().getHolder() instanceof Menu menu)) return;

        menu.click(player, e.getSlot());
        e.setCancelled(true);
    }

    @EventHandler
    public void on(InventoryCloseEvent e) {
        MenuManager.handleClose(e);
    }


}
