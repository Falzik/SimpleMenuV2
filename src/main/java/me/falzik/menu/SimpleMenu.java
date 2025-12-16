package me.falzik.menu;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Author: Falzik
 * Created time: 30.04.2025 16:01
 */

public abstract class SimpleMenu implements Menu {

    private final Inventory inventory;

    private final Map<Integer, Button> actionMap = new HashMap<>();

    public SimpleMenu() {
        this.inventory = Bukkit.createInventory(this, getRows().getSize(), ChatColor.translateAlternateColorCodes('&', getTitle()));
    }

    public abstract String getTitle();
    public abstract Rows getRows();
    public abstract boolean isCanClose();

    @Override
    public void click(Player player, int slot) {
        Consumer<Player> playerAction = this.actionMap.get(slot).action;
        Consumer<ItemStack> itemStackAction = this.actionMap.get(slot).itemAction;

        Button button = actionMap.get(slot);
        Button switchButton = button.getSwitchItem();

        if(switchButton != null) {
            setItem(slot, switchButton);
        }

        if(playerAction != null) {
            playerAction.accept(player);
        }

        if(itemStackAction != null) {
            itemStackAction.accept(button.getItemStack());
        }
    }

    @Override
    public void addDesign(Material material, String name) {
        for (int i = 0; i < getInventory().getSize(); i++) {
            if(getInventory().getItem(i) == null) {
                ItemStack itemStack = new ItemStack(material);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                itemStack.setItemMeta(itemMeta);

                setItem(i, new Button(itemStack, player -> {}));
            }
        }
    }

    @Override
    public void setItem(int slot, Button itemStack) {
        setItem(slot, itemStack, (player -> {}), (itemStack1 -> {}));
    }

    @Override
    public void setItem(int slot, Button itemStack, Consumer<Player> action) {
        setItem(slot, itemStack, action, (itemStack1 -> {}));
    }

    @Override
    public boolean getCanClose() {
        return isCanClose();
    }

    @Override
    public void setItem(int slot, Button itemStack, Consumer<Player> action, Consumer<Button> itemStackAction) {
        this.actionMap.put(slot, itemStack);

        getInventory().setItem(slot, itemStack.getItemStack());
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public enum Rows {

        ONE(1),
        TWO(2),
        THREE(3),
        FOURTH(4),
        FIVE(5);

        private final int size;


        Rows(int size) {
            this.size = size;
        }

        public int getSize() {
            return size * 9;
        }
    }
}
