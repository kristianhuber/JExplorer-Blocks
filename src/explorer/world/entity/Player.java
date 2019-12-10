package explorer.world.entity;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.input.Keyboard;

import explorer.main.JExplorer;
import explorer.main.JExplorer.RenderType;

/**
 * @Author: Me
 * @Description: This One is Self-Explanatory
 * 
 */

public class Player extends Entity{
	public static final float CAMERASPEED = 0.05F, SCROLLSPEED = 0.75F;

	/**
	 * Constructor Method
	 * 
	 */
	public Player() {

		super("player");
		
		//Sets up the Projection
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(75, 1.6F, 0.3F, 50);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		
		//Initializes Variables
		this.x = -5;
		this.y = -1.5F;
		this.z = -5;
	}

	/**
	 * Override the Entity Class
	 * 
	 */
	public void tick() {
		prevX = x;
		prevY = y;
		prevZ = z;

		setView();
		keyTick();
		drawStatBar();
	}

	/**
	 * Displays the statistics
	 * 
	 */
	public void drawStatBar() {

	}

	/**
	 * Sets the Camera Position
	 * 
	 */
	private void setView() {

		glRotatef(pitchX, 0, 1, 0);

		glTranslatef(x, y, z);
	}

	/**
	 * Handles the Keyboard
	 * 
	 */
	private void keyTick() {

		//Exits the Program
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {

			JExplorer.R = RenderType.MAIN_MENU;
			JExplorer.initProjection();
			JExplorer.W = null;
		}
		
		//Up and Down Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {

			this.y -= 0.05F;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

			this.y += 0.05F;
		}

		//Forward and Back Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {

			move(CAMERASPEED, 1);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {

			move(-CAMERASPEED, 1);
		}

		//Side to Side Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {

			move(CAMERASPEED, 0);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {

			move(-CAMERASPEED, 0);
		}

		//Side to Side View
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {

			pitchX += SCROLLSPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {

			pitchX -= SCROLLSPEED;
		}
	}
}
