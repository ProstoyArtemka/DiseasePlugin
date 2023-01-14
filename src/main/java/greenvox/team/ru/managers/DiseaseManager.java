package greenvox.team.ru.managers;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;

import java.util.ArrayList;

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
}
