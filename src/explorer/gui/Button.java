package explorer.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import javax.swing.JOptionPane;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import explorer.main.JExplorer;
import explorer.main.JExplorer.RenderType;
import explorer.world.World;

/**
 * @Author: Me
 * @Description: Click a Rectangle that Does Stuff
 * 
 */

public class Button {
	private Texture normal, highlight;
	private int x, y, width, height;
	private boolean isSelected;
	private String text, ID;

	/**
	 * Constructor Method
	 * 
	 */
	public Button(String ID, String text, int x, int y, int width, int height) {

		//Loads the Texture
		try {

			normal = TextureLoader.getTexture("PNG", getClass()
					.getResourceAsStream("/explorer/assets/gui/button.png"));
		} catch (Exception e) {

			e.printStackTrace();
		}

		//Loads the Highlight Texture
		try {

			highlight = TextureLoader.getTexture(
					"PNG",
					getClass().getResourceAsStream(
							"/explorer/assets/gui/button_highlight.png"));
		} catch (Exception e) {

			e.printStackTrace();
		}

		//Initialize Variables
		this.height = height;
		this.width = width;
		this.text = text;
		this.ID = ID;
		this.x = x;
		this.y = y;
	}

	/**
	 * Handle the Events
	 * 
	 */
	public void tick() {

		//Decides if the Mouse is Over the Button
		if (Mouse.getX() > this.x && Mouse.getX() < this.x + width
				&& (JExplorer.HEIGHT - Mouse.getY()) > this.y
				&& (JExplorer.HEIGHT - Mouse.getY()) < this.y + height) {

			isSelected = true;
		} else {

			isSelected = false;
		}
		
		//Click Events
		if(this.isSelected && Mouse.isButtonDown(0)){
			
			switch(ID){
			case "Eins":
				
				//SinglePlayer: Starts the Game
				JExplorer.R = RenderType.GAME;
				JExplorer.W = new World();
				break;
			case "Swei":
				
				//Online: Not an Option Yet
				JOptionPane.showMessageDialog(null, "Multiplayer Under Construction.");
				break;
			case "Drei":
				
				//Settings: There are None as of Now
				JOptionPane.showMessageDialog(null, "There are No Settings as of Now.");
				break;
			case "Vear":
				
				//Exit: Exits the Program
				try{
					
					Thread.sleep(500);
				}catch(Exception e){
					
					e.printStackTrace();
				}
				
				System.exit(0);
				break;
			}
		}

		render();
	}

	/**
	 * Draws the Button on the Screen
	 * 
	 */
	public void render() {

		///Draws the Text First
		new Font().drawFont(text, x + (width / 5), y + (height / 3));
		
		//Draws the Square
		glPushMatrix();
		{
			//Decides Which Texture to Use
			if (isSelected) {

				highlight.bind();
			} else {

				normal.bind();
			}

			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);
				glVertex2f(x, y);
				glTexCoord2f(0, 1);
				glVertex2f(x + width, y);
				glTexCoord2f(1, 1);
				glVertex2f(x + width, y + height);
				glTexCoord2f(1, 0);
				glVertex2f(x, y + height);
			}
			glEnd();
		}
		glPopMatrix();
	}
}
