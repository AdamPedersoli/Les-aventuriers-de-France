package Conception;
import IHM.*;
import Metier.Plateau;

public class ControleurConception
{
	private Plateau plateau;
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

	public void setPlateau(String nomPlateau, int lig, int col, int nbPoles, int nbDep, int nbManches)
	{
		
	}

	public Plateau getPlateau()
	{
		return this.plateau;
	}

	public void creerPlateau(String nom, int lig, int col, int nbDep, int nbPole, int nbManches)
	{
		this.plateau = new Plateau(nom, lig, col, nbManches, nbDep, nbPole);

		this.plateau.initCase();
	}
	
	public static void main(String[] args) 
	{
		ControleurConception controleurConception = new ControleurConception();
	}
}