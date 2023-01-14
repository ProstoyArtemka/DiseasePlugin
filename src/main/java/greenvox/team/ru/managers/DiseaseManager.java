package greenvox.team.ru.managers;

import greenvox.team.ru.disease.DiseaseExecutor;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.symptoms.Blind;
import greenvox.team.ru.symptoms.Cold;
import greenvox.team.ru.symptoms.Cough;
import greenvox.team.ru.symptoms.Faint;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DiseaseManager {
    // Use Arrays.asList();
    public static ArrayList<Symptom> Symptoms = (ArrayList<Symptom>) Arrays.asList(
            new Blind(),
            new Cold(),
            new Cough(),
            new Faint()
    );



    private static Symptom getRandomSymptomFromLevel(int level) {
    // Return random symptom with disease level.
        Random random = new Random();

        List<Symptom> s = new ArrayList<>();
        for (Symptom p : Symptoms) {
            if (p.getLevel() == level) {
                s.add(p);
            }
        }

        int index = random.nextInt(s.size());
        Symptom getRandomFromList = s.get(index);
        
        return getRandomFromList;
    }


    public static void executeRandomSymptom(int level, Player p) {
        Symptom symptom = getRandomSymptomFromLevel(level);
        // Execute symptom at player.
        symptom.execute(p.getPlayer());
    }

    public static void runDiseaseTimer() {
        int time = new Random().nextInt(20 * 10) + 10 * 20;

        SchedulerManager.runTaskLater("disease_executor", new DiseaseExecutor(), time);
    }
}
