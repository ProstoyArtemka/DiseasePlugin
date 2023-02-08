package greenvox.team.ru.events;

import greenvox.team.ru.recipes.SyringeRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnClickOnWaterEvent implements Listener {

    @EventHandler
    public void onClickOnWater(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (!player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(InfectedSyringeRunnable.InfectedSyringeTag))
            e.setCancelled(true);
        if (!player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(SyringeRecipe.SyringeTag))
            e.setCancelled(true);

        if (e.getClickedBlock().isLiquid()) {
            e.setCancelled(true);
        }
    }
}
