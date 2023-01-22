package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.recipes.MaskRecipe;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
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

        // Tracing if player looking at another player (Changed lambda)
        RayTraceResult result = player.getWorld().rayTraceEntities(
                player.getEyeLocation(),
                player.getLocation().getDirection(),
                5,
                e -> e != player && e instanceof Player
        );

        //bottle feature
        if (player.getInventory().getItemInMainHand().getType() == Material.GLASS_BOTTLE) {
            bottleBreak(player);
        }

        if (result == null) return;
        Player tracedPlayer = (Player) result.getHitEntity();

        if (tracedPlayer == null) return;
        applyDisease(tracedPlayer);
    }

    private void applyDisease(Player player) {
        ItemStack head = player.getInventory().getItem(EquipmentSlot.HEAD);

        if (head.getType() != Material.AIR)
            if (head.getItemMeta().getPersistentDataContainer().has(MaskRecipe.MaskTag)) return;

        if (!DatabaseManager.isPlayerIsInfected(player))
            DatabaseManager.applyDiseaseToPlayer(player);
        else
            DatabaseManager.setDiseaseLevelToPlayer(player, DatabaseManager.getDiseaseLevelFromPlayer(player) + 1);

        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 1));
    }

    // Moved from CoughManager
    private void pushAway(Player player, double speed) {
        if (!player.isOnline()) return;

        Vector UnitVector = player.getLocation().getDirection().normalize();
        player.setVelocity(UnitVector.multiply(-speed));
    }

    private void bottleBreak(Player player) {

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        itemStack.setAmount(itemStack.getAmount() - 1);

        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 3, new Particle.DustOptions(Color.fromRGB(109, 249, 203), 1));
        player.getWorld().spawnParticle(Particle.BLOCK_CRACK, player.getLocation(),5, Bukkit.createBlockData(Material.GLASS));
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1,1);
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
