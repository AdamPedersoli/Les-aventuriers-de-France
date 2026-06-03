package Metier;

import java.awt.Color;

/**
* Cette classe permet de gérer les moyen de transport.
*/

public enum MoyenTransport
{
	BATEAU  ( "Bateau"  ),
	AVION   ( "Avion"   ),
	TRAIN   ( "Train"   ),
	VELO    ( "Velo"    ),
	BUS     ( "Bus"     ),
	TRAMWAY ( "Tramway" ),
	PIETON  ( "Piéton"  );
	
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
	*/
	MoyenTransport( String nom )
	{
		this.nom = nom;
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
	
	/**
	* Fixe la couleur du moyen de transport.
	*
	* @param c la couleur que l'on fixe.
	*/
	public void setCouleur( Color c ) { this.couleur = c; }
	
	
}