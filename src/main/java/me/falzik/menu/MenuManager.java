package me.falzik.menu;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author: Falzik
 * Created time: 30.04.2025 17:02
 */

public class MenuManager {

    private static final Map<UUID, Menu> openMenus = new HashMap<>();
    private static JavaPlugin plugin;

    public static void init(JavaPlugin pluginInstance) {
        plugin = pluginInstance;
    }

    public static void openMenu(Player player, Menu menu) {
        openMenus.put(player.getUniqueId(), menu);
        menu.open(player);
    }

    public static void closeMenu(Player player) {
        openMenus.remove(player.getUniqueId());
        player.closeInventory();
    }

    public static void handleClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu menu = openMenus.get(player.getUniqueId());

        if (menu == null) return;

        if (!menu.getCanClose()) {
            // Переоткрываем меню на следующий тик
            Bukkit.getScheduler().runTask(plugin, () -> menu.open(player));
        } else {
            openMenus.remove(player.getUniqueId());
        }
    }

    public static Menu getOpenMenu(Player player) {
        return openMenus.get(player.getUniqueId());
    }
}
