package me.falzik.example;

import me.falzik.menu.PageMenu;
import me.falzik.menu.SimpleMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TestMenu extends PageMenu {

    public TestMenu() {
        super("&c&lTest Menu", SimpleMenu.Rows.TWO, 3);
    }

    @Override
    public int setPrevPosition() {
        return 12;
    }

    @Override
    public int setNextPosition() {
        return 14;
    }

    @Override
    public ItemStack setNextPage() {
        return new ItemStack(Material.SPECTRAL_ARROW);
    }

    @Override
    public ItemStack setPrevPage() {
        return new ItemStack(Material.ARROW);
    }

    @Override
    public String getFirstPage() {
        return "&7Будучи на первой странице вы не можете перейти на предыдущею страницу!";
    }

    @Override
    public String getLastPage() {
        return "&7Вы на последней странице!";
    }
}
