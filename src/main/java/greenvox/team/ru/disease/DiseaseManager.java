package greenvox.team.ru.disease;

import greenvox.team.ru.Main;
import greenvox.team.ru.symptoms.*;
import greenvox.team.ru.symptoms.dream.Dream;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DiseaseManager {
    public static List<Symptom> Symptoms = Arrays.asList(
            new Blind(),
            new Cold(),
            new Cough(),
            //new Faint(),
            new Vertigo(),
            new Fall(),
            new Fire(),
            new Weakness(),
            new Hunger(),
            new ElytraCorrosion(),
            new Puke(),
            new SwapItems(),
            new Dream()
    );

    private static Symptom getRandomSymptomFromLevel(int level) {
        Random random = new Random();
        ArrayList<Symptom> s = new ArrayList<>();

        for (Symptom p : Symptoms) {
            if (p.getLevel() <= level)
                s.add(p);
        }

        int index = random.nextInt(s.size());
        return s.get(index);
    }


    public static void executeRandomSymptom(int level, Player p) {
        getRandomSymptomFromLevel(level).execute(p.getPlayer());
    }

    public static void runDiseaseTimer() {
        int time = new Random().nextInt(40 * 20) + 40 * 20;

        SchedulerManager.runTaskLater("disease_executor", new DiseaseExecutor(), time);
    }

    public static void initSymptoms() {
        for (Symptom s : Symptoms) {
            s.init(Main.getInstance());
        }
    }
}
