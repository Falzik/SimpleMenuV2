package me.falzik.listener;


import me.falzik.menu.Menu;
import me.falzik.menu.MenuManager;
import me.falzik.menu.PageMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.AccessFlag;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Falzik
 * Created time: 30.04.2025 16:20
 */

public class InventoryListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        Inventory inventory = e.getClickedInventory();

        if (e.getClickedInventory() == null) return;

        if (e.getView().getTopInventory().getHolder() instanceof PageMenu pageMenu &&
                e.getClickedInventory().equals(e.getView().getTopInventory())) {

            e.setCancelled(true);
            pageMenu.click(player, e.getSlot(), pageMenu.getPageByInventory(e.getView().getTitle()));
        } else if(inventory.getHolder() instanceof Menu menu) {
            e.setCancelled(true);
            menu.click(player, e.getSlot());
        }
    }

    @EventHandler
    public void on(InventoryCloseEvent e) {
        MenuManager.handleClose(e);
    }


}
