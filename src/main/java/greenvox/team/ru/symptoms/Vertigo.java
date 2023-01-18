package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Vertigo implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int minSec = 5;
        int maxSec = 10;
        int count = 0;
        int minVelocity = -1;
        int maxVelocity = 2;
        int range = maxVelocity - minVelocity + 1;

        Vector playerVelocity = player.getVelocity().normalize();

        int sec = (int) ((Math.random() * (maxSec - minSec)) + minSec);
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, sec, 3));

        for (int i = -1; i <= sec; i++) {
            count++;
            int random = (int) (Math.random() * range) + minVelocity;

            if (count == 1) {
                playerVelocity.setX(random);
            } else if (count == 2) {
                playerVelocity.setY(random);
            } else break;
        }
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
