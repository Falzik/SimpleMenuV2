package me.falzik.vanilla.guiAPI.test;


import me.falzik.vanilla.guiAPI.GuiAPI;
import me.falzik.vanilla.guiAPI.menu.ButtonResult;
import me.falzik.vanilla.guiAPI.menu.SimpleMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Author: Falzik
 * Created time: 27.04.2025 19:47
 */

public class KillMenu extends SimpleMenu { // Example

    public KillMenu() {
        super("Kill Menu", Rows.ONE);
    }

    @Override
    public void onSetItems() {
        setItem(0, new ItemStack(Material.REDSTONE), ButtonResult.CLOSE, (player -> {
            player.sendRichMessage("<green>O, hello bro");
            player.sendRichMessage("<green>Take a berry!");
            player.getInventory().addItem(new ItemStack(Material.SWEET_BERRIES));
        }));
    }
}
