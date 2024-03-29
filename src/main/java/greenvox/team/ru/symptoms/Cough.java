package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.recipes.MaskRecipe;
import greenvox.team.ru.util.ApplyDisease;
import greenvox.team.ru.util.RayTrace;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


public class Cough implements Symptom {

    @Override
    public void init(JavaPlugin main) { }

    @Override
    public void execute(Player player) {
        ItemStack head = player.getInventory().getItem(EquipmentSlot.HEAD);

        if (head.getType() != Material.AIR)
            if (head.getItemMeta().getPersistentDataContainer().has(MaskRecipe.MaskTag)) return;

        // Changed name from colorize to color.
        Particle.DustOptions color = new Particle.DustOptions(Color.fromRGB(109, 249, 203), 0.5F);

        player.spawnParticle(Particle.REDSTONE, player.getEyeLocation(), 5, color);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PANDA_SNEEZE,2, 1);

        pushAway(player, 0.25f);

        if (RayTrace.TracePlayer(player, 5) == null) return;
        Player tracedPlayer = (Player) RayTrace.TracePlayer(player, 5).getHitEntity();

        if (tracedPlayer == null) return;
        if (DatabaseManager.isAteVaccine(tracedPlayer)) return;
        ApplyDisease.applyDisease(player, tracedPlayer);
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
