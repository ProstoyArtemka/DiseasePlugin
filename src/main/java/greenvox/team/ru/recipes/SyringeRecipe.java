package greenvox.team.ru.recipes;

import greenvox.team.ru.Main;
import greenvox.team.ru.util.ItemUtil;
import jdk.dynalink.NamespaceOperation;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.naming.Name;
import java.util.Arrays;

public class SyringeRecipe extends ShapedRecipe {
    public static NamespacedKey SyringeKey = NamespacedKey.fromString("syringe", Main.getInstance());
    public static NamespacedKey SyringeTag = NamespacedKey.fromString("syringe_tag", Main.getInstance());
    public static ItemStack Syringe = new ItemStack(Material.GLASS_BOTTLE);

    public static ItemStack InfectedSyringe = new ItemStack(Material.GLASS_BOTTLE);
    public static NamespacedKey InfectedSyringeTag = NamespacedKey.fromString("filled_tag", Main.getInstance());

    public static ItemStack NormalSyringe = new ItemStack(Material.GLASS_BOTTLE);
    public static NamespacedKey NormalSyringeTag = NamespacedKey.fromString("filledWithNormalBlood_tag", Main.getInstance());



    static {
        ItemUtil.metaUtil(Syringe, "Медицинский шприц", "Так и хочется вколоть в кого-то.",
                616, SyringeTag, 2);
    }

    static {
        ItemUtil.metaUtil(InfectedSyringe, "Шприц с кровью", "Кровь в данном шприце имеет странноватый оттенок.",
                615, InfectedSyringeTag, 3);
    }

    static {
        ItemUtil.metaUtil(NormalSyringe, "Шприц с кровью", "Кровь в данном шприце имеет обычный оттенок.",
                615, NormalSyringeTag, 4);

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
