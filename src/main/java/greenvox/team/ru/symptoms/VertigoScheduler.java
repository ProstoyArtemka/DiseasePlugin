package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class VertigoScheduler extends BukkitRunnable {
        int count = 0;
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (DatabaseManager.isPlayerIsInfected(player)) {

                int minSec = 5;
                int maxSec = 10;
                int minVelocity = -1;
                int maxVelocity = 2;
                int range = maxVelocity - minVelocity + 1;

                int sec = (int) ((Math.random() * (maxSec - minSec)) + minSec);
                int random = (int) (Math.random() * range) + minVelocity;

                Vector playerVelocity = player.getVelocity().normalize();

                playerVelocity.setY(random);
                playerVelocity.setX(random);

                count++;

                if (count >= sec) {
                    SchedulerManager.cancelTask("vertigo_task");
                }
            }
        }
    }
}