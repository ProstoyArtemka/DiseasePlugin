package greenvox.team.ru.managers;

import greenvox.team.ru.disease.DiseaseExecutor;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class DiseaseManager {
    // Use Arrays.asList();
    public static ArrayList<Symptom> Symptoms = null;

    private static Symptom getRandomSymptomFromLevel(int level) {
        // Return random symptom with disease level.
        return null;
    }

    public static void executeRandomSymptom(int level, Player p) {
        Symptom symptom = getRandomSymptomFromLevel(level);

        // Execute symptom at player.
    }

    public static void runDiseaseTimer() {
        int time = new Random().nextInt(20 * 10) + 10 * 20;

        SchedulerManager.runTaskLater("disease_executor", new DiseaseExecutor(), time);
    }
}
