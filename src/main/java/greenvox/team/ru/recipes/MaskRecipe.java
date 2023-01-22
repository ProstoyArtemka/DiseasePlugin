package greenvox.team.ru.recipes;

import greenvox.team.ru.Main;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class MaskRecipe extends ShapedRecipe {

    public static NamespacedKey MaskKey = NamespacedKey.fromString("mask", Main.getInstance());
    public static NamespacedKey MaskTag = NamespacedKey.fromString("mask_tag", Main.getInstance());
    public static ItemStack MaskResult = new ItemStack(Material.LEATHER_HELMET);

    static {
        LeatherArmorMeta meta = (LeatherArmorMeta) MaskResult.getItemMeta();

        meta.setColor(Color.WHITE);
        meta.setDisplayName(ChatColor.GRAY + "Самодельная маска");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_PURPLE + "Возможно спасёт от заражения..."
        ));
        meta.setCustomModelData(613);

        meta.getPersistentDataContainer().set(MaskTag, PersistentDataType.INTEGER, 1);
        meta.addItemFlags(ItemFlag.HIDE_DYE);

        MaskResult.setItemMeta(meta);
    }

    public MaskRecipe() {
        super(MaskKey, MaskResult);

        this.shape("YPG",
                   "SOS",
                   "CWF");

        setIngredient('S', Material.STRING);
        setIngredient('W', Material.WHITE_WOOL);
        setIngredient('C', Material.COBWEB);
        setIngredient('F', Material.FEATHER);

        setIngredient('O', Material.CHARCOAL);
        setIngredient('P', Material.POPPED_CHORUS_FRUIT);
        setIngredient('G', Material.GOLD_INGOT);
        setIngredient('Y', Material.PRISMARINE_CRYSTALS);

    }
}
