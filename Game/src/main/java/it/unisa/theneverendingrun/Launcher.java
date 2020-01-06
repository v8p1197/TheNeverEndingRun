package it.unisa.theneverendingrun;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;
import it.unisa.theneverendingrun.config.PropertiesCache;
import it.unisa.theneverendingrun.config.PropertiesConstants;
import it.unisa.theneverendingrun.engine.GameEngine;
import org.mini2Dx.desktop.DesktopMini2DxConfig;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class.getName());

	private static DesktopMini2DxConfig configureApplication() {

		var configuration = new DesktopMini2DxConfig(GameEngine.GAME_IDENTIFIER);
		configuration.setFromDisplayMode(DesktopMini2DxConfig.getDesktopDisplayMode());
		configuration.fullscreen = false;
		configuration.resizable = false;

		try {
			var properties = PropertiesCache.getInstance();
			configuration.width *= Double.parseDouble(properties.getProperty(PropertiesConstants.SCREEN_WIDTH));
			configuration.height *= Double.parseDouble(properties.getProperty(PropertiesConstants.SCREEN_HEIGHT));
			configuration.vSyncEnabled = Boolean.parseBoolean(properties.getProperty(PropertiesConstants.VSYNC_ENABLED));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			System.exit(2);
		}


		return configuration;
	}

	private static void setupLogger() throws IOException {

		// suppress the logging output to the console
		var rootLogger = Logger.getLogger("");
		var handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}

		// create a TXT logger file
		var fileTxt = new FileHandler("Logging " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " .txt", true);
		// create a TXT formatter
		var formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		rootLogger.addHandler(fileTxt);
	}

	public static void main(String[] arg) {
		try {
			setupLogger();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problems with creating the log files");
		}

		var configuration = configureApplication();
		var desktopMini2DxGame = new DesktopMini2DxGame(new GameEngine(), configuration);
	}
}
