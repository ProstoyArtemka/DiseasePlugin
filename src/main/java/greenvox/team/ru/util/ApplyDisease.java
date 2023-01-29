package greenvox.team.ru.util;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.recipes.MaskRecipe;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ApplyDisease {
    public static void applyDisease(Player player, Player tracedPlayer) {
        ItemStack head = player.getInventory().getItem(EquipmentSlot.HEAD);

        if (head.getType() != Material.AIR)
            if (head.getItemMeta().getPersistentDataContainer().has(MaskRecipe.MaskTag)) return;

        if (!DatabaseManager.isPlayerIsInfected(tracedPlayer))
            DatabaseManager.applyDiseaseToPlayer(tracedPlayer);
        else
            DatabaseManager.setDiseaseLevelToPlayer(tracedPlayer, DatabaseManager.getDiseaseLevelFromPlayer(tracedPlayer) + 1);

        tracedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5 * 20, 1));
    }
}
