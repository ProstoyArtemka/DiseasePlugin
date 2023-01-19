package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Cold implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int min = 5;
        int max = 16;
        int sec = (int) ((Math.random() * (max - min)) + min);
        player.setFreezeTicks(sec*20);
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
