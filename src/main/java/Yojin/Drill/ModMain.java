package Yojin.Drill;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import java.util.Properties;


public class ModMain implements ModInitializer, RecipeEntrypoint, GameStartEntrypoint {
    public static final String MOD_ID = "Drill";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static int itemId;
    @Override
    public void onInitialize() {
		ModItems.initItems();
        LOGGER.info("Drill initialized.");
    }

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}





}
