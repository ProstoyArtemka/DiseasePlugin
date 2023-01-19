package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Fire implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        int start = 5;
        int end = 10;
        int seconds = new Random().nextInt(end - start) + start;

        player.setFireTicks(seconds * 20);
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
