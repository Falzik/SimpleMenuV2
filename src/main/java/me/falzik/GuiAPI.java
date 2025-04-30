package me.falzik;

import me.falzik.league.menu.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
