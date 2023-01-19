package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class ItemDrop implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        dropItem(player);

    }

    @Override
    public int getLevel() {
        return 0;
    }

    private void dropItem(Player player) {
        ItemStack handItem = player.getInventory().getItemInMainHand();
        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        final Vector vector = player.getLocation().getDirection().normalize();

        player.getWorld().dropItem(player.getLocation().add(0, -0.5, 0), handItem).setVelocity(vector.multiply(1));
    }
}
