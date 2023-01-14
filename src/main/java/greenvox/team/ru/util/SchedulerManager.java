package greenvox.team.ru.util;

import greenvox.team.ru.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class SchedulerManager {

    private static final HashMap<String, BukkitRunnable> Runnables = new HashMap<>();

    public static void runTask(String name, BukkitRunnable runnable) {
        Runnables.put(name, runnable);

        runnable.runTask(Main.getInstance());
    }

    public static void runTaskLater(String name, BukkitRunnable runnable, long delay) {
        Runnables.put(name, runnable);

        runnable.runTaskLater(Main.getInstance(), delay);
    }

    public static void runTaskTimer(String name, BukkitRunnable runnable, long delay, long period) {
        Runnables.put(name, runnable);

        runnable.runTaskTimer(Main.getInstance(), delay, period);
    }

    public static void cancelTask(String name) {
        BukkitRunnable runnable = Runnables.get(name);
        if (runnable == null) return;

        runnable.cancel();
    }
}
