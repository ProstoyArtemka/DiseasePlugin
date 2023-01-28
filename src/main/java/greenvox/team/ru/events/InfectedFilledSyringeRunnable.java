package greenvox.team.ru.events;

import greenvox.team.ru.database.DatabaseManager;
import greenvox.team.ru.recipes.SyringeRecipe;
import greenvox.team.ru.util.SchedulerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;


public class InfectedFilledSyringeRunnable extends BukkitRunnable {
    int mainTimer = 0;
    int progressTimer = 0;
    Player player;
    Player target;
    Vector playerStartLoc;
    Vector targetStartLoc;

    public InfectedFilledSyringeRunnable(Player player, Player target) {

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

    //infection to player
    private void success() {
        UseAtAnotherPlayer.alreadyUse.remove(player.getName());

         if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(SyringeRunnable.InfectedSyringeTag)) {

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 0.9f);
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            player.getInventory().addItem(SyringeRecipe.Syringe);

            target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1));

            DatabaseManager.applyDiseaseToPlayer(target);
            SchedulerManager.cancelTask("InfectedFilled_task_" + player.getName());
        }
    }
    private void fail(){

        UseAtAnotherPlayer.alreadyUse.remove(player.getName());
        String str = "&coooooo";

        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 0.2f, 1);
        player.sendActionBar(ChatColor.translateAlternateColorCodes('&', str));

        SchedulerManager.cancelTask("InfectedFilled_task_" + player.getName());
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

        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) return true;

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