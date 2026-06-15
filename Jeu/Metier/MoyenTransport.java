package Jeu.Metier;

import java.awt.Color;

/**
* Cette classe permet de gérer les moyen de transport.
*/

public enum MoyenTransport
{
	BATEAU  ( "Bateau" , 255,   0,   0),
	AVION   ( "Avion"  ,   0, 255, 255),
	TRAIN   ( "Train"  ,   0,   0, 255),
	VELO    ( "Velo"   , 255, 255,   0),
	BUS     ( "Bus"    ,   0, 255, 255),
	TRAMWAY ( "Tramway", 255,   0, 255),
	PIETON  ( "Piéton" , 255, 128,   0);
	
	/**
	* Nom du moyen de transport.
	*/
	private String nom;
	
	/**
	* Couleur du moyen de transport.
	*/
	private Color couleur;
	
	/**
	* Crée un moyen de transport avec le nom.
	*
	* @param nom le nom du moyen de transport.
	* @param r   la quantiter de rouge de 0 à 255
	* @param v   la quantiter de vert  de 0 à 255
	* @param b   la quantiter de bleu  de 0 à 255
	*/
	MoyenTransport( String nom, int r, int v, int b )
	{
		this.nom = nom;
		this.couleur = new Color( r, v, b );
	}
	
	/**
	* Méthode pour obtenir le nom du moyen de transport.
	*
	* @return le nom du moyen de tranport.
	*/
	public String getNom    () { return this.nom;     }
	
	/**
	* Méthode pour obtenir la couleur du moyen de transport.
	*
	* @return la couleur du moyen de transport.
	*/
	public Color getCouleur () { return this.couleur; }
	
	
}
