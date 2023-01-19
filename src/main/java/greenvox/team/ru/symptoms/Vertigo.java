package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Vertigo implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int start = 10;
        int end = 15;
        String taskName = "vertigo_task_" + player.getName();

        int seconds = new Random().nextInt(end - start) + start;
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, seconds * 20, 3));

        SchedulerManager.runTaskTimer(taskName, new VertigoScheduler(seconds, player), 0, 5);
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
