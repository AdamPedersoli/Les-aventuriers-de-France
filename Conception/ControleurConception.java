//package Controleur;
import IHM.*;

public class ControleurConception
{
	private FrameMenu frameMenu;
	private FrameConfig frameConfig;
	private FrameCreation frameCreation;
	private FrameModification frameModification;
	
	public ControleurConception()
	{
		this.frameMenu = new FrameMenu();
		this.frameConfig = new FrameConfig(frameMenu);
		this.frameCreation = new FrameCreation(frameMenu);
		this.frameModification = new FrameModification(frameMenu);
	}

	public void setPlateau(String nomPlateau, int lig, int col, int nbPoles, int nbDep, int nbManches){}
	
	public static void main(String[] args) 
	{
		ControleurConception controleurConception = new ControleurConception();
	}
}