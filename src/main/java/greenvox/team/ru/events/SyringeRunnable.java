package greenvox.team.ru.events;

import greenvox.team.ru.Main;
import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.recipes.SyringeRecipe;
import greenvox.team.ru.util.RayTrace;
import greenvox.team.ru.util.PlayerUtil;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;


public class SyringeRunnable extends BukkitRunnable {
    private int mainTimer = 0;
    private int progressTimer = 0;
    private final Player player;
    private final Player target;
    private final Vector playerStartLoc;
    private final Vector targetStartLoc;
    public static ItemStack InfectedSyringe = new ItemStack(Material.GLASS_BOTTLE);
    public static NamespacedKey InfectedSyringeTag = NamespacedKey.fromString("filled_tag", Main.getInstance());

    static {
        ItemMeta meta = InfectedSyringe.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Шприц с кровью");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY + "Кровь в данном шприце имеет странноватый оттенок."
        ));
        meta.setCustomModelData(615);
        meta.getPersistentDataContainer().set(InfectedSyringeTag, PersistentDataType.INTEGER, 3);
        InfectedSyringe.setItemMeta(meta);
    }


    public SyringeRunnable(Player player, Player target) {

        this.player = player;
        playerStartLoc = player.getLocation().toVector();

        this.target = target;
        targetStartLoc = target.getLocation().toVector();
    }

    @Override
    public void run() {
        if(needCancel()) {
            fail();
            UseAtAnotherPlayer.alreadyUse.remove(player.getName());
        }

        if(mainTimer % 5 == 0) progress();

        mainTimer++;
    }

    private void success() {

        UseAtAnotherPlayer.alreadyUse.remove(player.getName());

        if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(SyringeRecipe.SyringeTag)) {
            if (DatabaseManager.isPlayerIsInfected(target)) {

                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 0.9f);

                PlayerUtil.DecreaseItemInPlayerHand(player, 1, InfectedSyringe);

                target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1));

                SchedulerManager.cancelTask("syringe_task_" + player.getName());
            }
        }
    }
    private void fail(){

        UseAtAnotherPlayer.alreadyUse.remove(player.getName());
        String str = "&coooooo";

        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 0.2f, 1);
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', str));

        SchedulerManager.cancelTask("syringe_task_" + player.getName());
    }

    private void progress(){
        StringBuilder str = new StringBuilder("&aoooooo");
        str.insert(progressTimer+3, "&c");

        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', String.valueOf(str)));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 1);

        progressTimer ++;
        if(progressTimer == 6) success();

    }

    private boolean needCancel(){

        if(!player.isSneaking()) return true;
        if(!player.isOnline()) return true;

        if(player.isDead()) return true;
        if(target.isDead()) return true;

        if(!player.getLocation().toVector().equals(playerStartLoc)) return true;
        if(!target.getLocation().toVector().equals(targetStartLoc)) return true;

        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return true;
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) return true;

        if(RayTrace.TracePlayer(player,2) == null) return true;
        if(!RayTrace.TracePlayer(player,2).getHitEntity().equals(target)) return true;

        return false;
    }
}