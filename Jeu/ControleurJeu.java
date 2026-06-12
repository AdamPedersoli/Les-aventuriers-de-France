package Jeu;

import Jeu.IHM.*;
import Jeu.Metier.*;
import java.io.File;

import java.util.ArrayList;

public class ControleurJeu
{
	private Plateau      plateau;
	private FrameMenuJeu frm;
	
	public ControleurJeu()
	{
		frm = new FrameMenuJeu( this );
	}
	
	public String[][] getNomPlateaux()
	{
		String[][] tabNomPlateau;
		
		File dossier = new File("Jeu/Plateau");
		File[] fichiers = dossier.listFiles();
		
		tabNomPlateau = new String[fichiers.length][1];
		
		if ( fichiers != null )
			for ( int cpt = 0 ; cpt < fichiers.length ; cpt++ )
				tabNomPlateau[cpt][0] = fichiers[cpt].getName();
		
		return tabNomPlateau;
	}
	
	public boolean estFinManche()
	{
		return this.plateau.estFinManche();
	}
	
	public void mancheSuivante()
	{
		this.plateau.mancheSuivante();
	}
	
	public boolean estFin()
	{
		return this.plateau.estFin();
	}
	
	public Carte getPioche()
	{
		return this.plateau.getPioche();
	}
	
	public int getScoreFinal()
	{
		return this.plateau.getScoreFinal();
	}
	
	public ArrayList<Departement> getLstDep()
	{
		return this.plateau.getLstDep();
	}
	
	public ArrayList<Case> getLstCaseDepart()
	{
		return this.plateau.getLstCaseDepart();
	}
	
	public ArrayList<MoyenTransport> getLstMoyenTransport()
	{
		return this.plateau.getLstMoyenTransport();
	}
	
	public ArrayList<Case> getLstCasePole()
	{
		return this.plateau.getLstCasePole();
	}
	
	public ArrayList<Trajet> getLstTrajet()
	{
		return this.plateau.getLstTrajet();
	}

	public int getManche()
	{
		return this.plateau.getIndexManche();
	}
	
	public int getTailleX()
	{
		return this.plateau.getTailleX();
	}
	
	public int getTailleY()
	{
		return this.plateau.getTailleY();
	}
	
	public boolean ajouterSegment( Case caseDep, Case caseArr)
	{
		return plateau.ajouterSegment ( caseDep, caseArr );
	}
	
	public void modifier( String s )
	{
		this.plateau = GestionPlateau.modifier(s);
	}
	
	public void jouerCarte()
	{
		this.plateau.jouerCarte();
	}
	
	public static void main ( String[] args )
	{
		new ControleurJeu();
	}
}
