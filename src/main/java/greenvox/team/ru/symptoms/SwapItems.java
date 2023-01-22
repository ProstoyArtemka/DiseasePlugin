package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class SwapItems implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int firstSlot = new Random().nextInt(player.getInventory().getSize());
        int secondSlot = new Random().nextInt(player.getInventory().getSize());

        ItemStack buffer = player.getInventory().getItem(firstSlot);

        player.getInventory().setItem(firstSlot, player.getInventory().getItem(secondSlot));
        player.getInventory().setItem(secondSlot, buffer);
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
