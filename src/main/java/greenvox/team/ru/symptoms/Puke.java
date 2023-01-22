package greenvox.team.ru.symptoms;

import com.dre.brewery.BPlayer;
import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Puke implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int start = 10;
        int end = 15;
        int seconds = new Random().nextInt(end - start) + start;

        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, seconds * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, seconds * 20, 1));
        BPlayer.addPuke(player, seconds * 20);
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
