package me.falzik.test;


import me.falzik.menu.SimpleMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Author: Falzik
 * Created time: 30.04.2025 16:12
 */

public class KillGUI extends SimpleMenu {

    public KillGUI() {
        super("&c&lKill GUI", Rows.ONE, false);
    }

    @Override
    public void onSetItems() {
        setItem(0, new ItemStack(Material.REDSTONE), (player -> {
            player.setHealth(0.0);
        }));
        setItem(4, new ItemStack(Material.ARROW), (player ->  {}), (itemStack -> {
            getInventory().getItem(0).setAmount(getInventory().getItem(0).getAmount() + 1);

            setCanClose(true);
        }));
    }
}
