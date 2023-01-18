package greenvox.team.ru.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CoughManager {

    public static void pushAway(Player player, double speed) {
        if (!player.isOnline()) return;

        final Vector UnitVector = player.getLocation().toVector().subtract(player.getLocation().toVector().normalize());
        player.setVelocity(UnitVector.multiply(speed));
    }
}
