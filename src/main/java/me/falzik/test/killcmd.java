package me.falzik.test;


import me.falzik.menu.MenuManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Author: Falzik
 * Created time: 30.04.2025 16:21
 */

public class killcmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        MenuManager.openMenu(player, new KillGUI());
        return true;
    }
}
