package greenvox.team.ru.recipes;

import com.dre.brewery.api.BreweryApi;
import com.dre.brewery.api.events.brew.BrewModifyEvent;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PillsRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getInventory().contains(Material.POTION)) continue;

            for (ItemStack stack : player.getInventory()) {
                if (stack == null) continue;
                if (stack.getType() != Material.POTION) continue;
                if (!BreweryApi.isBrew(stack)) continue;

                    ItemStack pills = PillsCraft.pills(stack.getItemMeta().getDisplayName());

                    if (pills != null) {
                        player.getInventory().remove(stack);
                        player.getInventory().addItem(pills);

                    }

                    SchedulerManager.cancelTask("pills_runnable");
                }
            }
        }
    }

