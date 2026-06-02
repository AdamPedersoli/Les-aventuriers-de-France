package Metier;

import java.awt.Color;

public enum MoyenTransport
{
	BATEAU  ( "Bateau"  ),
	AVION   ( "Avion"   ),
	TRAIN   ( "Train"   ),
	VELO    ( "Velo"    ),
	BUS     ( "Bus"     ),
	TRAMWAY ( "Tramway" ),
	PIETON  ( "Piéton"  );
	
	
	private String nom;
	
	private Color couleur;
	
	MoyenTransport( String nom )
	{
		this.nom = nom;
	}
	
	public getNom() { return this.nom; }
	
	public getCouleur() { return this.couleur; }
	
	public void setCouleur( Color c ) { this.couleur = c; }
	
	
}