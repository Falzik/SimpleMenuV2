package me.falzik.menu;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
* Author: Falzik
* Created time: 21.05.2025 17:33
*/

public abstract class PageMenu implements InventoryHolder {

    private final Inventory mainPage;
    private final String title;
    private final SimpleMenu.Rows rows;
    private ItemStack prevPage = setPrevPage();
    private ItemStack nextPage = setNextPage();
    private final int availableSlots;

    private int pages = 1;
    private int prevSlot = setPrevPosition();
    private int nextSlot = setNextPosition();

    private final Map<Integer, Inventory> inventoryByPage = new HashMap<>();
    private final Map<Integer, Map<Integer, Consumer<Player>>> actionByPage = new HashMap<>();
    private final Map<Integer, Map<Integer, ItemStack>> itemStackByPage = new HashMap<>();

    public PageMenu(String title, SimpleMenu.Rows rows, int availableSlots) {
        this.title = title;
        this.rows = rows;
        this.availableSlots = availableSlots;

        this.mainPage = Bukkit.createInventory(this, rows.getSize(),
                ChatColor.translateAlternateColorCodes('&', title));

        inventoryByPage.put(1, mainPage);
        actionByPage.put(1, new HashMap<>());
        itemStackByPage.put(1, new HashMap<>());
    }

    public abstract int setPrevPosition();

    public abstract int setNextPosition();

    public void createPage() {
        pages++;
        Inventory inventory = Bukkit.createInventory(this, rows.getSize(), ChatColor.translateAlternateColorCodes('&', title + " &7Страница: " + pages));
        inventoryByPage.put(pages, inventory);
        actionByPage.put(pages, new HashMap<>());
        itemStackByPage.put(pages, new HashMap<>());
    }

    public int getPageByInventory(String title) {
        if (title.contains("Страница: ")) {
            try {
                return Integer.parseInt(title.split("Страница: ")[1].trim());
            } catch (NumberFormatException e) {
                return 1;
            }
        }
        return 1;
    }

    public void click(Player player, int slot, int page) {
        if (!actionByPage.containsKey(page)) return;

        Consumer<Player> action = actionByPage.get(page).get(slot);
        if (action != null) {
            action.accept(player);
        }
    }

    private void setItemInternal(int page, int slot, ItemStack itemStack, Consumer<Player> action) {
        getInventoryByPage(page).setItem(slot, itemStack);
        actionByPage.computeIfAbsent(page, k -> new HashMap<>()).put(slot, action);
        itemStackByPage.computeIfAbsent(page, k -> new HashMap<>()).put(slot, itemStack);
    }

    public Consumer<Player> getAction(int page, int slot) {
        if(actionByPage.containsKey(page)) {
            return actionByPage.get(page).get(slot);
        }

        return null;
    }

    public int getFreeSlot(int page) {
        Inventory inv = getInventoryByPage(page);
        if (inv == null) return -1;

        for (int i = 0; i < availableSlots; i++) {
            if (inv.getItem(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public Inventory getInventoryByPage(int page) {
        return inventoryByPage.get(page);
    }


    public void addItem(ItemStack itemStack, Consumer<Player> playerAction) {
        for (int page = 1; page <= pages; page++) {
            int freeSlot = getFreeSlot(page);
            if (freeSlot != -1) {
                setItemInternal(page, freeSlot, itemStack, playerAction);
                return;
            }
        }

        createPage();
        setItemInternal(pages, getFreeSlot(pages), itemStack, playerAction);
    }

    public void setItem(ItemStack itemStack, int slot, Consumer<Player> action) {
        getInventoryByPage(pages).setItem(slot, itemStack);

        actionByPage.computeIfAbsent(pages, k -> new HashMap<>());
        itemStackByPage.computeIfAbsent(pages, k -> new HashMap<>());

        actionByPage.get(pages).put(slot, action);
        itemStackByPage.get(pages).put(slot, itemStack);
    }

    public void open(Player player, int page) {
        page = Math.max(1, Math.min(page, pages));
        Inventory inventory = inventoryByPage.get(page);
        if (inventory != null) {
            onSetItems(page);
            player.openInventory(inventory);
        }
    }

    private void onSetItems(int page) {
        Inventory inventory = inventoryByPage.get(page);
        if (inventory == null) return;

       setItemInternal(page, prevSlot, prevPage, (player -> {
           if(page - 1 < 1) {
               player.sendMessage(ChatColor.translateAlternateColorCodes('&', getFirstPage()));
           } else {
               open(player, page - 1);
           }
       }));
       setItemInternal(page, nextSlot, nextPage, (player -> {
           if(page + 1 > pages) {
               player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLastPage()));
           } else {
               open(player, page + 1);
           }
       }));
    }

    public void removeItemWithAction(ItemStack itemStackToRemove, int page) {
        Map<Integer, ItemStack> items = itemStackByPage.get(page);
        Map<Integer, Consumer<Player>> actions = actionByPage.get(page);
        Inventory inventory = inventoryByPage.get(page);

        if (items == null || actions == null || inventory == null) return;

        for (Map.Entry<Integer, ItemStack> entry : new HashMap<>(items).entrySet()) {
            int slot = entry.getKey();
            ItemStack item = entry.getValue();

            if (item != null && item.isSimilar(itemStackToRemove)) {
                items.remove(slot);
                actions.remove(slot);
                inventory.setItem(slot, null);
                break;
            }
        }
    }

    public abstract ItemStack setNextPage();
    public abstract ItemStack setPrevPage();

    public abstract String getFirstPage();
    public abstract String getLastPage();

    public int getPages() {
        return pages;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return mainPage; // return first page menu
    }
}
