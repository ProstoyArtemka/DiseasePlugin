package greenvox.team.ru.events;

import greenvox.team.ru.events.UseAtAnotherPlayer;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Arrays;


public class SyringeRunnable extends BukkitRunnable {
    int mainTimer = 0;
    int progressTimer = 0;
    Player player;
    Player target;
    Vector playerStartLoc;
    Vector targetStartLoc;
static {
    ItemStack itemStack = new ItemStack(Material.GLASS_BOTTLE);
    ItemMeta meta = (ItemMeta) itemStack.getItemMeta();

    meta.setDisplayName(ChatColor.WHITE + "Шприц с зараженной кровью");
    meta.setLore(Arrays.asList(
            ChatColor.GRAY + "Кровь в данном шприце имеет странноватый оттенок"
    ));
    meta.setCustomModelData(615);
    itemStack.setItemMeta(meta);
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

        ItemStack itemStack = new ItemStack(Material.GLASS_BOTTLE);
        ItemMeta meta = (ItemMeta) itemStack.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "Шприц с зараженной кровью");
        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "Кровь в данном шприце имеет странноватый оттенок"
        ));
        meta.setCustomModelData(615);
        itemStack.setItemMeta(meta);

        UseAtAnotherPlayer.alreadyUse.remove(player.getName());

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 0.9f);
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        player.getInventory().addItem(itemStack);
        target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1));

        SchedulerManager.cancelTask("syringe_task_" + player.getName());
    }
    private void fail(){

        UseAtAnotherPlayer.alreadyUse.remove(player.getName());
        String str = "&coooooo";

        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 0.2f, 1);
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', str));

        SchedulerManager.cancelTask("syringe_task_" + player.getName());
    }

    private void progress(){
        StringBuffer str = new StringBuffer("&aoooooo");
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
        if(rayTrace() == null) return true;
        if(!rayTrace().getHitEntity().equals(target)) return true;

        return false;
    }
    private RayTraceResult rayTrace(){
        RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                2,
                entity -> entity!=player && entity instanceof Player
        );
        return rayTraceResult;
    }
}