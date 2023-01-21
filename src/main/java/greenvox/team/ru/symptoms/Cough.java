package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.disease.Symptom;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;


public class Cough implements Symptom {

    @Override
    public void init(JavaPlugin main) { }

    @Override
    public void execute(Player player) {
        // Changed name from colorize to color.
        Particle.DustOptions color = new Particle.DustOptions(Color.fromRGB(109, 249, 203), 0.5F);

        player.spawnParticle(Particle.REDSTONE, player.getEyeLocation(), 5, color);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PANDA_SNEEZE,2, 1);
        pushAway(player, 0.25f);

        // Tracing if player looking at another player (Changed lambda)
        RayTraceResult result = player.getWorld().rayTraceEntities(
                player.getEyeLocation(),
                player.getLocation().getDirection(),
                5,
                e -> e != player && e instanceof Player
        );

        if (result == null) return;
        Player tracedPlayer = (Player) result.getHitEntity();

        if (tracedPlayer == null) return;
        DatabaseManager.applyDiseaseToPlayer(tracedPlayer);
    }

    // Moved from CoughManager
    private void pushAway(Player player, double speed) {
        if (!player.isOnline()) return;

        Vector UnitVector = player.getLocation().getDirection().normalize();
        player.setVelocity(UnitVector.multiply(-speed));
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
