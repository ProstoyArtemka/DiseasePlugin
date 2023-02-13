package greenvox.team.ru.recipes;
import com.dre.brewery.api.events.brew.BrewModifyEvent;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.ItemUtil;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class PillsCraft implements Listener {

    public static NamespacedKey PillsTag = NamespacedKey.fromString("pills_tag", Main.getInstance());

    public static ItemStack pills(String name) {
        ItemStack pills = new ItemStack(Material.SUSPICIOUS_STEW, 1);

        ItemUtil.metaUtil(pills, "Пилюля", "Возожно сможет ослабить вашу болезнь.",
                614, PillsTag, 4);

        return pills;
    }

    @EventHandler
    public void OnBrewEvent(BrewModifyEvent e) {
        if (pills(e.getItemMeta().getDisplayName()) == null) return;

        SchedulerManager.runTaskLater("pills_runnable", new PillsRunnable(), 2);
    }
}



