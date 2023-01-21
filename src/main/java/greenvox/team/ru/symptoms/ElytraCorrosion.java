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
       SchedulerManager.runTask("elytra_scheduler", new ElytraScheduler());
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
