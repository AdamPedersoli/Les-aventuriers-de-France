package Metier;

import java.awt.Color;

public enum Pole
{
	PORT           ( "Port"             ),
	AEROPORT       ( "Aéroport"         ),
	GARE           ( "Gare"             ),
	STATION_VELO   ( "Station de velo"  ),
	ARRET_BUS      ( "Arrêt de bus"     ),
	ARRET_TRAMWAY  ( "Arrêt de tramway" ),
	PASSAGE_PIETON ( "Passage piéton"   );
	
	
	
	private String nom;
	
	private Color couleur;
	
	private boolean estVisite;
	
	private Pole suivant;
	
	private Pole precedent;
	
	Pole ( String nom, int r, int v, int b )
	{
		this.nom = nom;
		
		this.couleur = new Color( r, v, b);
		
		this.estVisite = false;
		this.suivant   = null;
		this.precedent = null;
	}
	
	public String getNom     () { return this.nom;        }
	public Color  getCouleur () { return this.couleur;    }
	public Pole   getSvt     () { return this.suivant;    }
	public Pole   getPrc     () { return this.precedent;  }
	
	public boolean estVisite () { return this.estVisite;  }
	
	
	public void setSvt ( Pole suivant   ) { this.suivant   = suivant;   }
	public void setPrc ( Pole precedent ) { this.precedent = precedent; }
	
	public void setEstVisite ( boolean estVisite )
	{
		this.estVisite = estVisite
	}
	
}