package greenvox.team.ru.symptoms;

import greenvox.team.ru.disease.Symptom;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Fall implements Symptom {
    @Override
    public void init(JavaPlugin main) {

    }

    @Override
    public void execute(Player player) {
        player.chat("/lay");
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
