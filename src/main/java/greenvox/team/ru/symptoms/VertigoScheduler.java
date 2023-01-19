package greenvox.team.ru.symptoms;

import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class VertigoScheduler extends BukkitRunnable {

    private int Count = 0;
    private final long Time;
    private final Player Player;

    public VertigoScheduler(long time, Player player) {
        Time = time;
        Player = player;
    }

    @Override
    public void run() {
        if (!Player.isOnline()) return;

        Bukkit.getLogger().info(String.valueOf(Count));
        Bukkit.getLogger().info(Player.getName());

        double minVelocity = -0.1;
        double maxVelocity = 0.2;

        Vector playerVelocity = Player.getVelocity();
        playerVelocity.setX((new Random().nextFloat() * maxVelocity) + minVelocity);
        playerVelocity.setZ((new Random().nextFloat() * maxVelocity) + minVelocity);

        Player.setVelocity(playerVelocity);

        Count++;

        if (Count >= Time * 4) {
            SchedulerManager.cancelTask("vertigo_task_" + Player.getName());
        }
    }
}