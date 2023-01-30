package greenvox.team.ru.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerUtil {
    public static void DecreaseItemInPlayerHand(Player player, int decreaseNum, ItemStack stackToAdd) {

        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - decreaseNum);
        player.getInventory().addItem(stackToAdd);
    }

    public static void RandomDamage(Player player, double bound1, double bound2) {
        double random = new Random().nextDouble(bound1 - bound2) + bound1;
        player.damage(random);
    }
}
