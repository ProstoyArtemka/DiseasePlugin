package greenvox.team.ru.recipes;

import greenvox.team.ru.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SyringeRecipe extends ShapedRecipe {
    public static NamespacedKey SyringeKey = NamespacedKey.fromString("syringe", Main.getInstance());
    public static NamespacedKey SyringeTag = NamespacedKey.fromString("syringe_tag", Main.getInstance());
    public static ItemStack Syringe = new ItemStack(Material.GLASS_BOTTLE);

    static {
        ItemMeta meta = Syringe.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Медицинский шприц");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY + "Так и хочется вколоть в кого-то."
        ));
        meta.setCustomModelData(616);

        meta.getPersistentDataContainer().set(SyringeTag, PersistentDataType.INTEGER, 2);

        Syringe.setItemMeta(meta);
    }

    public SyringeRecipe() {
        super(SyringeKey, Syringe);

        this.shape("AAA",
                   "GGF",
                   "AAA");

        setIngredient('A', Material.AIR);
        setIngredient('G', Material.GLASS);
        setIngredient('F', Material.FLINT);

    }
}
