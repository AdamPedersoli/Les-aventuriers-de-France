package Metier;

import java.awt.Color;
import java.util.ArrayList;

/**
* Cette classe permet de créer un pôle.
*/

public class Pole
{
	
	/**
	* Port.
	*/
	public static String PORT           = "Port";
	
	/**
	* Aéroport.
	*/
	public static String AEROPORT       = "Aéroport";
	
	/**
	* Gare.
	*/
	public static String GARE           = "Gare";
	
	/**
	* Velo.
	*/
	public static String STATION_VELO   = "Station de velo";
	
	/**
	* Arrêt de bus.
	*/
	public static String ARRET_BUS      = "Arrêt de bus";
	
	/**
	* Arrêt de tramway.
	*/
	public static String ARRET_TRAMWAY  = "Arrêt de tramway";
	
	/**
	* Passage piéton.
	*/
	public static String PASSAGE_PIETON = "Passage piéton";
	
	/**
	* Nom du pôle.
	*/
	private String nom;
	
	/**
	* Couleur du pôle.
	*/
	private Color couleur;
	
	/**
	* booléen si le pôle a été visité par un joueur.
	*/
	private boolean estVisite;
	
	/**
	* Liste des voisin du pôle.
	*/
	private ArrayList<Pole> lstVoisin;
	
	/**
	* Crée un pôle avec un nom et une couleur.
	*
	* @param nom le nom du pôle.
	* @param c la couleur du pôle.
	*/
	public Pole ( String nom, Color c )
	{
		this.nom = nom;
		
		this.couleur = c;
		
		this.estVisite = false;
		
		this.lstVoisin = new ArrayList<Pole>();
	}
	
	/**
	* Méthode pour obtenir le nom du pôle.
	*
	* @return le nom du pôle.
	*/
	public String getNom     () { return this.nom;     }
	
	/**
	* Méthode pour obtenir la couleur du pôle.
	*
	* @return la couleur du pôle.
	*/
	public Color  getCouleur () { return this.couleur; }
	
	/**
	* Méthode pour savoir si le pôle a été visité.
	*
	* @return true si le pôle a été visité sinon false.
	*/
	public boolean estVisite () { return this.estVisite; }
	
	/**
	* Méthode pour fixer si le pôle a été visité.
	*
	* @param estVisite booléen de la visite du pôle.
	*/
	public void setEstVisite ( boolean estVisite )
	{
		this.estVisite = estVisite;
	}
	
	/**
	* Méthode pour ajouter un voisin au pôle.
	*
	* @param voisin le voisin ajouté.
	*/
	public void ajouterVoisin( Pole voisin )
	{
		this.lstVoisin.add( voisin );
	}
	
	/**
	* Méthode pour savoir si le pôle passé en paramètre est voisin de celui qui appel la méthode.
	*
	* @param p le pôle à vérifier.
	* @return vrai s'il est voisin sinon faux.
	*/
	public boolean estVoisin( Pole p )
	{
		return this.lstVoisin.contains( p );
	}
	
}