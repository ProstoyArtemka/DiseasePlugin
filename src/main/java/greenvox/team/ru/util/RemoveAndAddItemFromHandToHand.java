package greenvox.team.ru.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RemoveAndAddItemFromHandToHand {
    public static void RemoveAndAdd(Player player, int decreaseNum, ItemStack stackToAdd) {

        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - decreaseNum);
        player.getInventory().addItem(stackToAdd);
    }
}
