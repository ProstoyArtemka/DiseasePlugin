package greenvox.team.ru.symptoms;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class ElytraScheduler extends BukkitRunnable implements Listener {

    private int Count = 0;
    private final long Time;
    private final Player Player;

    public ElytraScheduler(long time, Player player) {
        Time = time;
        Player = player;
    }

    @Override
    public void run() {
            if (!Player.isOnline()) return;
            if (!Player.isGliding()) return;

            Particle.DustOptions color = new Particle.DustOptions(Color.fromRGB(78, 4, 177), 2F);
            Player.getWorld().spawnParticle(Particle.REDSTONE, Player.getLocation(), 5, color);
            Player.setGliding(false);

            Count++;
            if (Count >= Time) {
                SchedulerManager.cancelTask("elytra_task_" + Player.getName());
            }
        }
    }