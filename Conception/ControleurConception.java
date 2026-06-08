package Conception;
import Conception.IHM.*;
import Conception.Metier.Plateau;

public class ControleurConception
{
	private Plateau plateau;
	private FrameMenu frameMenu;
	private FrameConfig frameConfig;
	private FrameCreation frameCreation;
	private FrameModification frameModification;
	
	public ControleurConception()
	{
		this.frameMenu = new FrameMenu(this);
	}

	public void setPlateau(String nomPlateau, int lig, int col, int nbPoles, int nbDep, int nbManches)
	{
		this.plateau = new Plateau(nomPlateau, lig, col, nbPoles, nbDep, nbManches);
		this.plateau.initCase();
	}

	public void setPole( int lig, int col )
	{
		
	}
	
	public void setCaseAtDep( int indexTypeDep, int ligCase, int colCase )
	{
	
	}

	public Plateau getPlateau()
	{
		return this.plateau;
	}
	
	public String getNom()
	{
		return this.plateau.getNom();
	}
	
	public int getTailleXPlateau()
	{
		return this.plateau.getTailleX();
	}
	
	public int getTailleYPlateau()
	{
		return this.plateau.getTailleY();
	}
	
	public static void main(String[] args) 
	{
		new ControleurConception();
	}
}
