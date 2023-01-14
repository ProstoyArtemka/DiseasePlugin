package greenvox.team.ru.disease;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.managers.DiseaseManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DiseaseExecutor extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            int level = DatabaseManager.getDiseaseLevelFromPlayer(p);

            DiseaseManager.executeRandomSymptom(level, p);
        }

        DiseaseManager.runDiseaseTimer();
    }
}
