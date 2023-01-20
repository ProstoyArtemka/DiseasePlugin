package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Hunger implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int start = 15;
        int end = 20;
        int seconds = new Random().nextInt(end - start) + start;

        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, seconds * 20, 1));
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
