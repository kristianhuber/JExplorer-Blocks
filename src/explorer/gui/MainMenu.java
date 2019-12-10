package explorer.gui;

/**
 * @Author: Me
 * @Description: The Main Menu for the JExplorer Program
 * 
 */

public class MainMenu {
	private Button singlePlayer, multiPlayer, settings, exit;
	private Font f;
	
	/**
	 * Constructor Method
	 * 
	 */
	public MainMenu() {
		//Initializes the Font
		f = new Font();
		
		//Initializes the Buttons
		singlePlayer = new Button("Eins", "Single Player", 400, 150, 500, 60);
		multiPlayer = new Button("Swei", "Multiplayer", 450, 275, 500, 60);
		settings = new Button("Drei", "Settings", 380, 425, 500, 60);
		exit = new Button("Vear", "Exit", 425, 550, 500, 60);
	}
	
	/**
	 * Constructor Method
	 * 
	 */
	public void render(){
		
		//Draws the Font
		f.drawFont("Java Explorer", 375, 50, 1.5F);
		
		//Updates the Buttons
		singlePlayer.tick();
		multiPlayer.tick();
		settings.tick();
		exit.tick();
	}
}
