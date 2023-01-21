package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Blind implements Symptom {

    private static final List<PotionEffectType> Type = Arrays.asList(
            PotionEffectType.BLINDNESS,
            PotionEffectType.DARKNESS
    );

    private PotionEffectType getRandomBlindnessEffect() {
        return Type.get(new Random().nextInt(Type.size()));
    }

    @Override
    public void init(JavaPlugin main) {}

    @Override
    public void execute(Player player) {
        PotionEffectType type = getRandomBlindnessEffect();

        player.addPotionEffect(new PotionEffect(type, 30 * 20, 1));
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
