package greenvox.team.ru.util;

import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

public class RayTrace {
    public static RayTraceResult TracePlayer(Player originPlayer, int distance){
        return originPlayer.getWorld().rayTraceEntities(
                 originPlayer.getEyeLocation(),
                 originPlayer.getEyeLocation().getDirection(),
                 distance,
                 entity -> entity != originPlayer && entity instanceof Player
        );
    }
}

