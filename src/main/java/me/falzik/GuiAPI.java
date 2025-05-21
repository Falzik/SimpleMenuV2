package me.falzik;

import me.falzik.listener.InventoryListener;
import me.falzik.menu.MenuManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiAPI extends JavaPlugin {

    private static GuiAPI instance;

    public static GuiAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        MenuManager.init(this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
