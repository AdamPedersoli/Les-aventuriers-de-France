// package Les_aventuriers_de_France;
import IHM.*;

public class Controleur 
{
	private FrameMenu frameMenu;
	// private FrameCreation frameCreation;
	// private FrameSolo frameSolo;
	// private FrameModification frameModification;
	
	public Controleur()
	{
		this.frameMenu = new FrameMenu();
		// this.frameCreation = new FrameCreation();
		// this.frameModification = new FrameModification();
	}
	
	public static void main(String[] args) 
	{
		Controleur controleur = new Controleur();
	}
}