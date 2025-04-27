package me.falzik.vanilla.guiAPI.test;


import me.falzik.vanilla.guiAPI.menu.ButtonResult;
import me.falzik.vanilla.guiAPI.menu.SimpleMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Author: Falzik
 * Created time: 27.04.2025 19:47
 */

public class KillMenu extends SimpleMenu {

    public KillMenu() {
        super("Убийственное меню", Rows.ONE);
    }

    @Override
    public void onSetItems() {
        setItem(0, new ItemStack(Material.REDSTONE), ButtonResult.CLOSE, (player -> {
            player.sendRichMessage("<green>Я не ожидал что кто-то ещё этим пользуется");
            player.sendRichMessage("<green>Держи конфетку за это!");
            player.getInventory().addItem(new ItemStack(Material.SWEET_BERRIES));
        }));
    }
}
