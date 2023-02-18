package greenvox.team.ru.disease;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class DiseaseExecutor extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!DatabaseManager.isPlayerIsInfected(p)) continue;
            if (DatabaseManager.isAteVaccine(p)) continue;
            int level = DatabaseManager.getDiseaseLevelFromPlayer(p);
            if (level < 0) continue;

            SchedulerManager.runTaskLater("symptom_execute_" + p.getName(), new BukkitRunnable() {
                @Override
                public void run() {
                    DiseaseManager.executeRandomSymptom(level, p);
                }
            }, new Random().nextInt(30) * 20);
        }

        DiseaseManager.runDiseaseTimer();
    }
}
