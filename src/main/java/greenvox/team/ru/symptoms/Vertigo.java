package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Vertigo implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int minSec = 5;
        int maxSec = 10;

        int sec = (int) ((Math.random() * (maxSec - minSec)) + minSec);
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, sec, 3));

        SchedulerManager.runTask("vertigo_task", new VertigoScheduler());
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
