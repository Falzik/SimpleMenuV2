package me.falzik.example;

import me.falzik.GuiAPI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class testCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        TestMenu testMenu = GuiAPI.getTestMenu();
        testMenu.addItem(new ItemStack(Material.GREEN_WOOL), (player1 -> {
            player1.sendMessage("salam!");
        }));
        testMenu.open(player, 1);
        return true;
    }
}
