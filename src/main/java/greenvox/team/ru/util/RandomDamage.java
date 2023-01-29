package greenvox.team.ru.util;

import org.bukkit.entity.Player;

import java.util.Random;

public class RandomDamage {

    public static void randomDamage(Player player, double bound1, double bound2) {
        double random = new Random().nextDouble(bound1 - bound2) + bound1;
        player.damage(random);

    }
}
