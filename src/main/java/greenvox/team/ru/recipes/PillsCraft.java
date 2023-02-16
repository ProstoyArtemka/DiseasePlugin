package greenvox.team.ru.recipes;
import com.dre.brewery.api.events.brew.BrewDrinkEvent;
import com.dre.brewery.api.events.brew.BrewModifyEvent;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.ItemUtil;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;


public class PillsCraft implements Listener {

    public static NamespacedKey PillsTag = NamespacedKey.fromString("pills_tag", Main.getInstance());

    public static ItemStack pills(String name) {
        name = name.replaceAll(ChatColor.WHITE.toString(), "");

        ItemStack pills1 = new ItemStack(Material.SUSPICIOUS_STEW, 1);


        ItemUtil.metaUtil(pills1, "Пилюля", "Возможно сможет ослабить вашу болезнь.",
                614, PillsTag, 4);

        return pills1;
    }

    @EventHandler
    public void OnBrewEvent(BrewModifyEvent e) {
        if (pills(e.getItemMeta().getDisplayName()) == null) return;

        if (e.getBrew().getCurrentRecipe() != null) {
            if (!e.getBrew().getCurrentRecipe().hasName("Странная смесь")) return;

            SchedulerManager.runTaskLater("pills_runnable", new PillsRunnable(), 1);
        }
    }
}

