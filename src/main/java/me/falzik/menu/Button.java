package me.falzik.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class Button {

    private final ItemStack itemStack;

    private Button switchItem;

    public final Consumer<Player> action;
    public Consumer<ItemStack> itemAction;

    public Button(ItemStack itemStack, Consumer<Player> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    public void completeAction(Player player) {
        if(player != null) {
            action.accept(player);
        }
    }

    public Button getSwitchItem() {
        return switchItem;
    }

    public void switchButton(Button switchItem) {
        this.switchItem = switchItem;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Consumer<ItemStack> getItemAction() {
        return itemAction;
    }

    public void setItemAction(Consumer<ItemStack> itemAction) {
        this.itemAction = itemAction;
    }
}
