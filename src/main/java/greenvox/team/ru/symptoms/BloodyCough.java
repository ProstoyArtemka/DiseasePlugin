package greenvox.team.ru.symptoms;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.recipes.MaskRecipe;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.*;

public class BloodyCough implements Symptom {

    @Override
    public void init(JavaPlugin main) {
    }

    @Override
    public void execute(Player player) {
        if (player.isGliding()) return;

        Vector vectorWithMask = player.getLocation().getDirection().normalize().multiply(-0.5F);
        BlockData fallingDustData = Material.REDSTONE_BLOCK.createBlockData();

        int num1 = 1;
        int num2 = 5;
        double random = new Random().nextInt(num2 - num1) + num1;

        ItemStack head = player.getInventory().getItem(EquipmentSlot.HEAD);

        if (head.getType() != Material.AIR) {
            if (head.getItemMeta().getPersistentDataContainer().has(MaskRecipe.MaskTag)) {

                player.damage(random);
                player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getEyeLocation().add(vectorWithMask), 25, fallingDustData);

                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20,1));

            }
        } else {

            Vector vectorWithOutMask = player.getLocation().getDirection().normalize().multiply(1F);

            player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getEyeLocation().add(vectorWithOutMask), 25, fallingDustData);
            player.damage(random);

            RayTraceResult result = player.getWorld().rayTraceEntities(
                    player.getEyeLocation(),
                    player.getLocation().getDirection(),
                    5,
                    e -> e != player && e instanceof Player
            );

            if (result == null) return;
            Player tracedPlayer = (Player) result.getHitEntity();

            if (tracedPlayer == null) return;
            applyDisease(tracedPlayer);
        }
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

    @Override
    public int getLevel() {
        return 3;
    }
}
