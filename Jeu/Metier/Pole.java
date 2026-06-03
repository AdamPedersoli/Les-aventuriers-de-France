package Metier;

import java.awt.Color;
import java.util.ArrayList;

public class Pole
{
	public static String PORT           = "Port";
	public static String AEROPORT       = "Aéroport";
	public static String GARE           = "Gare";
	public static String STATION_VELO   = "Station de velo";
	public static String ARRET_BUS      = "Arrêt de bus";
	public static String ARRET_TRAMWAY  = "Arrêt de tramway";
	public static String PASSAGE_PIETON = "Passage piéton";
	
	
	private String nom;
	
	private Color couleur;
	
	private boolean estVisite;
	
	private ArrayList<Pole> lstVoisin;
	
	public Pole ( String nom, Color c )
	{
		this.nom = nom;
		
		this.couleur = c;
		
		this.estVisite = false;
		
		this.lstVoisin = new ArrayList<Pole>();
	}
	
	public String getNom     () { return this.nom;     }
	public Color  getCouleur () { return this.couleur; }
	
	public boolean estVisite () { return this.estVisite; }
	
	public void setEstVisite ( boolean estVisite )
	{
		this.estVisite = estVisite;
	}
	
	public void ajouterVoisin( Pole voisin )
	{
		this.lstVoisin.add( voisin );
	}
	
	public boolean estVoisin( Pole p )
	{
		return this.lstVoisin.contains( p );
	}
	
}