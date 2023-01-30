package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import greenvox.team.ru.recipes.MaskRecipe;
import greenvox.team.ru.util.ApplyDisease;
import greenvox.team.ru.util.PlayerUtil;
import greenvox.team.ru.util.RayTrace;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class BloodyCough implements Symptom {

    @Override
    public void init(JavaPlugin main) {
    }

    @Override
    public void execute(Player player) {
        if (player.isGliding()) return;

        Vector vectorWithMask = player.getLocation().getDirection().normalize().multiply(-0.5F);
        Vector vectorWithOutMask = player.getLocation().getDirection().normalize().multiply(1F);
        BlockData fallingDustData = Material.REDSTONE_BLOCK.createBlockData();


        ItemStack head = player.getInventory().getItem(EquipmentSlot.HEAD);

        if (head.getType() != Material.AIR) {
            if (head.getItemMeta().getPersistentDataContainer().has(MaskRecipe.MaskTag)) {
               PlayerUtil.RandomDamage(player, 5, 1);
               spawnParticle(player, vectorWithMask, fallingDustData);

               player.playSound(player.getLocation(), Sound.ENTITY_PANDA_SNEEZE, 1,1);
               player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20,1));

            }
        } else {
            spawnParticle(player, vectorWithOutMask, fallingDustData);
            PlayerUtil.RandomDamage(player, 5, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_PANDA_SNEEZE, 1,1);

            if (RayTrace.TracePlayer(player, 5) == null) return;
            Player tracedPlayer = (Player) RayTrace.TracePlayer(player, 5).getHitEntity();

            if (tracedPlayer == null) return;
            ApplyDisease.applyDisease(player, tracedPlayer);
        }
    }

    @Override
    public int getLevel() {
        return 3;
    }

    private void spawnParticle(Player player, Vector vector, BlockData data) {
        player.getWorld().spawnParticle(Particle.BLOCK_DUST, player.getEyeLocation().add(vector), 25, data);
    }
}
