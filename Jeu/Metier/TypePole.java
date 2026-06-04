package Jeu.Metier;

import java.awt.Color;

/**
* Cette classe énumère tout les différents type de pole.
*/

public class enum TypePole
{
	PORT           ( "Port"            , "port.png"          ,  28,  74, 113 );
	AEROPORT       ( "Aéroport"        , "aeroport.png"      , 235, 241, 239 );
	GARE           ( "Gare"            , "gare.png"          , 216, 182, 133 );
	STATION_VELO   ( "Station de velo" , "station_velo.png"  , 154, 190,  80 );
	ARRET_BUS      ( "Arrêt de bus"    , "arret_bus.png"     , 254, 207,  18 );
	ARRET_TRAMWAY  ( "Arrêt de tramway", "arret_tramway.png" , 135, 123, 198 );
	PASSAGE_PIETON ( "Passage piéton"  , "passage_pieton.png", 255, 144,   0 );
	
	
	/**
	* Nom du pôle.
	*/
	private String nom;
	
	/**
	* Image du pôle.
	*/
	private ImageIcon image;
	
	/**
	* Couleur du pôle.
	*/
	private Color couleur;
	
	/**
	* Crée un nouveau type de pôle avec le nom, le nom de l'image et les composantes rouge, vert, bleu de la couleur.
	*
	* @param nom le nom du pôle.
	* @param nomImage le nom de l'image dans le dossier images/poles.
	* @param r la composantes rouge de la couleur entre 0 et 255.
	* @param v la composantes verte de la couleur entre 0 et 255.
	* @param b la composantes bleue de la couleur entre 0 et 255.
	*/
	TypePole( String nom, String nomImage, int r, int v, int b )
	{
		this.nom = nom;
		
		this.couleur = new Color( r, v, b );
		
		String cheminImage = "../images/poles/" + nomImage;
		
		this.image = new ImageIcon( cheminImage, this.nom );
	}
	
	/**
	* Méthode pour obtenir le nom du pôle.
	*
	* @return le nom du pôle.
	*/
	public String    getNom   () { return this.nom;     }
	
	/**
	* Méthode pour obtenir l'image du pôle.
	*
	* @return l'image du pôle.
	*/
	public ImageIcon getImage () { return this.image;   }
	
	/**
	* Méthode pour obtenir la couleur du pôle.
	*
	* @return la couleur du pôle.
	*/
	public Color getCouleur   () { return this.couleur; }
	
}