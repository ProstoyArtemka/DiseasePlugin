package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.recipes.SyringeRecipe;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class UseAtAnotherPlayer implements Listener {
    public static List<String> alreadyUse = new ArrayList<>();

    static {

    }


    @EventHandler
    public void useAtAnotherPlayer(PlayerInteractEntityEvent e) {
        if (alreadyUse.contains(e.getPlayer().getName())) {
            alreadyUse.remove(e.getPlayer().getName());
        }

        Entity entity = e.getRightClicked();
        if (!(entity instanceof Player)) return;

        Player player = e.getPlayer();

        if (DatabaseManager.isPlayerIsInfected((Player) entity)) {
            String taskName = "syringe_task_" + player.getName();

            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(SyringeRecipe.SyringeTag)) {

                    alreadyUse.add(player.getName());
                    SchedulerManager.runTaskTimer(taskName, new SyringeRunnable(player, (Player) entity), 1, 1);

                }
            }
        }

        if (!DatabaseManager.isPlayerIsInfected((Player) entity)) {

            String taskName2 = "InfectedFilled_task_" + player.getName();

            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(InfectedSyringeRunnable.InfectedSyringeTag)) {

                    alreadyUse.add(player.getName());
                    SchedulerManager.runTaskTimer(taskName2, new InfectedSyringeRunnable(player, (Player) entity), 1, 1);
                }
            }
        }
    }
}
