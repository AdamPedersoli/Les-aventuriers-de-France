package Metier;

public enum Pole
{
	PORT           ( "Port"             );
	AEROPORT       ( "Aéroport"         );
	GARE           ( "Gare"             );
	STATION_VELO   ( "Station de velo"  );
	ARRET_BUS      ( "Arrêt de bus"     );
	ARRET_TRAMWAY  ( "Arrêt de tramway" );
	PASSAGE_PIETON ( "Passage piéton"   );
	
	
	
	private String nom;
	
	private boolean estVisite;
	
	private Pole suivant;
	
	private Pole precedent;
	
	Pole ( String nom )
	{
		this.nom = nom;
		
		this.estVisite = false;
		this.suivant   = null;
		this.precedent = null;
	}
	
	public String getNom () { return this.nom;       }
	public Pole   getSvt () { return this.suivant;   }
	public Pole   getPrc () { return this.precedent; }
	
	public boolean estVisite() { return this.estVisite; }
	
	
	public void setSvt ( Pole suivant   ) { this.suivant   = suivant;   }
	public void setPrc ( Pole precedent ) { this.precedent = precedent; }
	
	
}