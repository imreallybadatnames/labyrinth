package dev.mayaqq.labyrinth;

import dev.mayaqq.labyrinth.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Labyrinth implements ModInitializer {

    public static final String MODID = "labyrinth";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier id(String path) {
        return Identifier.of(MODID, path);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Labyrinth is initializing...");
        LabyrinthItems.register();
        LabyrinthTags.register();
        LabyrinthRecipes.register();
        LabyrinthEvents.register();
        LabyrinthEntities.register();
    }
}
