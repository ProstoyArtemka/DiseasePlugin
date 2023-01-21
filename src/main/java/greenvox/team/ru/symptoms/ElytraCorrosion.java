package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class ElytraCorrosion implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int start = 1;
        int end = 4;
        String taskName = "elytra_task_" + player.getName();
        int seconds = new Random().nextInt(end - start) + start;

       SchedulerManager.runTaskTimer(taskName, new ElytraScheduler(seconds, player), 3, 1);
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
