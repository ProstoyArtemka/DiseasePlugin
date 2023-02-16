package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hallucinations implements Symptom {

    public static List<String> MessagesList = new ArrayList<>();

    @Override
    public void init(JavaPlugin main) {
        MessagesList = main.getConfig().getStringList("hallucinations_messages");
    }

    private Player getRandomPlayer(List<? extends Player> players) {
        return players.get(new Random().nextInt(players.size()));
    }

    private String getRandomMessage() {
        return MessagesList.get(new Random().nextInt(MessagesList.size()));
    }

    @Override
    public void execute(Player player) {
        Player p = getRandomPlayer(Bukkit.getOnlinePlayers().stream().toList());

        p.sendMessage(player.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + getRandomMessage());
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
