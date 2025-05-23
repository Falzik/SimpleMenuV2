package me.falzik;

import me.falzik.example.TestMenu;
import me.falzik.example.testCMD;
import me.falzik.listener.InventoryListener;
import me.falzik.menu.MenuManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuiAPI extends JavaPlugin {

    private static GuiAPI instance;

    public static GuiAPI getInstance() {
        return instance;
    }

    private static final TestMenu testMenu = new TestMenu();

    public static TestMenu getTestMenu() {
        return testMenu;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        MenuManager.init(this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        getCommand("test").setExecutor(new testCMD());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
