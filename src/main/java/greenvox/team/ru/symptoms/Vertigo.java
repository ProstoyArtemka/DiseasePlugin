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
        int start = 5;
        int end = 10;

        int seconds = new Random().nextInt(end - start) + start;
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, seconds, 3));

        SchedulerManager.runTask("vertigo_task", new VertigoScheduler());
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
