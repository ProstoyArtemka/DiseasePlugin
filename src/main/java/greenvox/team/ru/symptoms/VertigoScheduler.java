package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class VertigoScheduler extends BukkitRunnable {

    private int Count = 0;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!DatabaseManager.isPlayerIsInfected(player)) continue;

            int start = 5;
            int end = 10;
            int minVelocity = -1;
            int maxVelocity = 2;
            int range = maxVelocity - minVelocity + 1;

            int seconds = new Random().nextInt(end - start) + start;
            double random = (new Random().nextFloat() * range + minVelocity);

            Vector playerVelocity = player.getVelocity().normalize();

            playerVelocity.setY(random);
            playerVelocity.setX(random);

            Count++;

            if (Count >= seconds) {
                SchedulerManager.cancelTask("vertigo_task");
            }
        }
    }
}