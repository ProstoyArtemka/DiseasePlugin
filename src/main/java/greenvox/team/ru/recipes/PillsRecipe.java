package greenvox.team.ru.recipes;

import greenvox.team.ru.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class PillsRecipe extends ShapedRecipe {

    public static NamespacedKey PillsKey = NamespacedKey.fromString("pills", Main.getInstance());
    public static NamespacedKey PillsTag = NamespacedKey.fromString("pills_tag", Main.getInstance());
    public static ItemStack Pills = new ItemStack(Material.SUSPICIOUS_STEW);

    static {
        ItemMeta meta = Pills.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Пилюли");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY + "Возможно сможет повлиять на вашу болезнь."
        ));
        meta.setCustomModelData(614);

        meta.getPersistentDataContainer().set(PillsTag, PersistentDataType.INTEGER, 4);

        Pills.setItemMeta(meta);
    }

    public PillsRecipe() {
        super(PillsKey, Pills);

        this.shape("MSF",
                "BHL",
                "CRA");

        setIngredient('M', Material.MILK_BUCKET);
        setIngredient('S', Material.BROWN_MUSHROOM);
        setIngredient('F', Material.FERMENTED_SPIDER_EYE);
        setIngredient('B', Material.BLAZE_POWDER);
        setIngredient('H', Material.HONEY_BOTTLE);
        setIngredient('L', Material.LILY_OF_THE_VALLEY);
        setIngredient('C', Material.GOLDEN_CARROT);
        setIngredient('R', Material.GLOW_BERRIES);
        setIngredient('A', Material.GOLDEN_APPLE);
    }
}
