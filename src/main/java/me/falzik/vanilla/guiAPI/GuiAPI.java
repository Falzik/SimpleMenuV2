package me.falzik.vanilla.guiAPI;

import me.falzik.vanilla.guiAPI.listeners.InventoryListener;
import me.falzik.vanilla.guiAPI.test.killCmd;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getCommand("killgui").setExecutor(new killCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
