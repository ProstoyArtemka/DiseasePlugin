package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.DiseaseManager;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.util.CoughManager;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;

import greenvox.team.ru.disease.DiseaseManager.*;


public class Cough implements Symptom {



    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        Particle.DustOptions colorize = new Particle.DustOptions(Color.fromRGB(109, 249, 203), 0.5F);

        player.spawnParticle(Particle.REDSTONE, player.getLocation().add(0,0.5,0), 5, colorize);
        CoughManager.pushAway(player, 1);

        //tracing if player looking at another player
        RayTraceResult result = player.getWorld().rayTraceEntities(
                player.getEyeLocation(),
                player.getLocation().getDirection(),
                5,
                e -> e == player && e instanceof LivingEntity
        );

        if (result != null) {
            Player tracedPlayer = (Player) result.getHitEntity();

            DiseaseManager.executeRandomSymptom(1,tracedPlayer);
        }
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
