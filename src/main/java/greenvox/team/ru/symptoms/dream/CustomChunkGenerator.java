package greenvox.team.ru.symptoms.dream;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        for (int X = 0; X < 16; X++) {

            for (int Z = 0; Z < 16; Z++) {
                chunk.setBlock(X, 0, Z, Material.BARRIER);
                biome.setBiome(X, 0, Z, Biome.CRIMSON_FOREST);
            }
        }

        return chunk;
    }

}
