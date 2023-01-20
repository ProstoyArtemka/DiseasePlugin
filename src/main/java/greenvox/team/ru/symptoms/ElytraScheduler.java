package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class ElytraScheduler extends BukkitRunnable implements Listener {

    private int Count = 0;
    private int Start = 1;
    private int End = 5;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!DatabaseManager.isPlayerIsInfected(player)) continue;
            if (!player.isOnline()) return;
            if (!player.isGliding()) return;

            Particle.DustOptions color = new Particle.DustOptions(Color.fromRGB(78, 4, 177), 0.5F);
            player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 5, color);
            int seconds = new Random().nextInt(End - Start) + Start;
            player.setGliding(false);

            Count++;
            if (Count >= seconds) {
                SchedulerManager.cancelTask("elytra_scheduler");
            }

        }
    }
}