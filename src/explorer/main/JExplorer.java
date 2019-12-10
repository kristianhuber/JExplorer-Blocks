package explorer.main;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import explorer.gui.MainMenu;
import explorer.world.World;

/**
 * @Author: Me
 * @Description: The Main Class for the JExplorer Program
 * 
 */

public class JExplorer {
	public static final int WIDTH = 1366, HEIGHT = 768;
	public static RenderType R = RenderType.MAIN_MENU;
	public static MainMenu M;
	public static World W;

	/**
	 * Render Types
	 * 
	 */
	public enum RenderType {

		MAIN_MENU, GAME;
	}

	/**
	 * Constructor Method
	 * 
	 */
	public JExplorer() {

		// Creates the Display
		try {
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("JExplorer Pre-Alpha[v1.0]");
			Display.setVSyncEnabled(true);
			Display.setResizable(false);
			Display.sync(60);

			Display.create();

		} catch (LWJGLException e) {

			e.printStackTrace();
		}

		// Initializes Projection
		initProjection();

		// Initializing Variables
		M = new MainMenu();
	}
	
	public static void initProjection(){
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
	}

	/**
	 * Where the Loop Runs
	 * 
	 */
	public void start() {

		// Main Loop
		while (!Display.isCloseRequested()) {

			// Resets the Screen
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();

			// Decides What to Render
			if (R == RenderType.MAIN_MENU) {

				M.render();
			} else if (R == RenderType.GAME) {

				W.tick();
			}

			// Updates the Display
			Display.update();
		}

		// Ends the Program
		Display.destroy();
	}

	/**
	 * Entry Method
	 * 
	 */
	public static void main(String[] args) {

		new JExplorer().start();
	}
}
