package greenvox.team.ru.util;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class ItemUtil {
    public static void metaUtil(ItemStack stack, String name, String lore, int modelData,
                                NamespacedKey itemTag, int dataContainerValue) {

        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + name);
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY + lore
        ));

        meta.setCustomModelData(modelData);
        meta.getPersistentDataContainer().set(itemTag, PersistentDataType.INTEGER, dataContainerValue);

        stack.setItemMeta(meta);
    }
}
