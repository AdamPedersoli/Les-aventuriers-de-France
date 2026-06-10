package Conception;

import Conception.IHM.FrameConception;

import Conception.Metier.Plateau;
import Conception.Metier.TypeDepartement;
import Conception.Metier.TypePole;

import java.util.ArrayList;

public class ControleurConception
{
	private Plateau plateau;
	private FrameConception frameCpt;
	
	private ArrayList<Integer> lstPoleSelect; // pour transiter la liste entre le panel Config et Création
	
	public ControleurConception()
	{
		this.frameCpt = new FrameConception(this);
	}

	public void setPlateau(String nomPlateau, int lig, int col, int nbPoles, int nbDep, int nbManches)
	{
		this.plateau = new Plateau(nomPlateau, lig, col, nbPoles, nbDep, nbManches);
		this.plateau.initCase();
	}
	
	public void sauvegarder()
	{
		this.plateau.sauvegarder();
	}

	public void ajouterPole( int lig, int col, TypePole typePole )
	{
		this.plateau.ajouterPole( typePole, lig, col );
	}
	
	public void ajouterDep( int lig, int col, TypeDepartement typeDep )
	{
		this.plateau.ajouterDep( typeDep, lig, col );
	}
	
	public void ajouterCaseDepart( int lig, int col )
	{
		this.plateau.ajouterCaseDepart( this.plateau.getCase(lig,col) );
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
	
	public void setLstPoleSelect( ArrayList<Integer> lst )
	{
		this.lstPoleSelect = lst;
	}
	
	public ArrayList<Integer> getLstPoleSelect()
	{
		return this.lstPoleSelect;
	}
	
	public int getNbDep() { return this.plateau.getNbDep(); }
	
	public static void main(String[] args) 
	{
		new ControleurConception();
	}
}
