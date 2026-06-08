package Conception.Metier;

import java.awt.Color;

/**
* Cette classe énumère tout les départements de France.
*/
public enum TypeDepartement
{
	
	// Île-de-France
	D75_PARIS             ("Paris"            , 220, 60, 60),
	D77_SEINE_ET_MARNE    ("Seine-et-Marne"   , 210, 50, 50),
	D78_YVELINES          ("Yvelines"         , 200, 40, 40),
	D91_ESSONNE           ("Essonne"          , 230, 70, 70),
	D92_HAUTS_DE_SEINE    ("Hauts-de-Seine"   , 240, 80, 80),
	D93_SEINE_SAINT_DENIS ("Seine-Saint-Denis", 190, 30, 30),
	D94_VAL_DE_MARNE      ("Val-de-Marne"     , 250, 90, 90),
	D95_VAL_D_OISE        ("Val-d'Oise"       , 215, 55, 55),

	// Auvergne-Rhône-Alpes
	D01_AIN          ("Ain"         , 60, 180, 120),
	D03_ALLIER       ("Allier"      , 50, 170, 110),
	D07_ARDECHE      ("Ardèche"     , 70, 190, 130),
	D15_CANTAL       ("Cantal"      , 40, 160, 100),
	D26_DROME        ("Drôme"       , 80, 200, 140),
	D38_ISERE        ("Isère"       , 55, 175, 115),
	D42_LOIRE        ("Loire"       , 65, 185, 125),
	D43_HAUTE_LOIRE  ("Haute-Loire" , 45, 165, 105),
	D63_PUY_DE_DOME  ("Puy-de-Dôme" , 75, 195, 135),
	D69_RHONE        ("Rhône"       , 85, 205, 145),
	D73_SAVOIE       ("Savoie"      , 35, 155, 95),
	D74_HAUTE_SAVOIE ("Haute-Savoie", 90, 210, 150),

	// Bourgogne-Franche-Comté
	D21_COTE_D_OR             ("Côte-d'Or"            , 180, 120, 60),
	D25_DOUBS                 ("Doubs"                , 170, 110, 50),
	D39_JURA                  ("Jura"                 , 190, 130, 70),
	D58_NIEVRE                ("Nièvre"               , 160, 100, 40),
	D70_HAUTE_SAONE           ("Haute-Saône"          , 200, 140, 80),
	D71_SAONE_ET_LOIRE        ("Saône-et-Loire"       , 175, 115, 55),
	D89_YONNE                 ("Yonne"                , 185, 125, 65),
	D90_TERRITOIRE_DE_BELFORT ("Territoire de Belfort", 165, 105, 45),

	// Bretagne
	D22_COTES_D_ARMOR   ("Côtes-d'Armor"  , 60, 100, 220),
	D29_FINISTERE       ("Finistère"      , 50, 90, 210),
	D35_ILLE_ET_VILAINE ("Ille-et-Vilaine", 70, 110, 230),
	D56_MORBIHAN        ("Morbihan"       , 40, 80, 200),

	// Centre-Val de Loire
	D18_CHER           ("Cher"          , 180, 60, 180),
	D28_EURE_ET_LOIR   ("Eure-et-Loir"  , 170, 50, 170),
	D36_INDRE          ("Indre"         , 190, 70, 190),
	D37_INDRE_ET_LOIRE ("Indre-et-Loire", 160, 40, 160),
	D41_LOIR_ET_CHER   ("Loir-et-Cher"  , 200, 80, 200),
	D45_LOIRET         ("Loiret"        , 175, 55, 175),

	// Corse
	D2A_CORSE_DU_SUD ("Corse-du-Sud", 210, 180, 60),
	D2B_HAUTE_CORSE  ("Haute-Corse" , 220, 190, 70),

	// Grand Est
	D08_ARDENNES           ("Ardennes"          ,  80, 130, 180),
	D10_AUBE               ("Aube"              ,  70, 120, 170),
	D51_MARNE              ("Marne"             ,  90, 140, 190),
	D52_HAUTE_MARNE        ("Haute-Marne"       ,  60, 110, 160),
	D54_MEURTHE_ET_MOSELLE ("Meurthe-et-Moselle", 100, 150, 200),
	D55_MEUSE              ("Meuse"             ,  75, 125, 175),
	D57_MOSELLE            ("Moselle"           ,  85, 135, 185),
	D67_BAS_RHIN           ("Bas-Rhin"          ,  65, 115, 165),
	D68_HAUT_RHIN          ("Haut-Rhin"         ,  95, 145, 195),
	D88_VOSGES             ("Vosges"            , 110, 155, 205),

	// Hauts-de-France
	D02_AISNE         ("Aisne"        , 60, 200, 200),
	D59_NORD          ("Nord"         , 50, 190, 190),
	D60_OISE          ("Oise"         , 70, 210, 210),
	D62_PAS_DE_CALAIS ("Pas-de-Calais", 40, 180, 180),
	D80_SOMME         ("Somme"        , 80, 220, 220),

	// Normandie
	D14_CALVADOS       ("Calvados"      , 200, 160, 60),
	D27_EURE           ("Eure"          , 190, 150, 50),
	D50_MANCHE         ("Manche"        , 210, 170, 70),
	D61_ORNE           ("Orne"          , 180, 140, 40),
	D76_SEINE_MARITIME ("Seine-Maritime", 220, 180, 80),

	// Nouvelle-Aquitaine
	D16_CHARENTE             ("Charente"            , 220, 120, 40),
	D17_CHARENTE_MARITIME    ("Charente-Maritime"   , 210, 110, 30),
	D19_CORREZE              ("Corrèze"             , 230, 130, 50),
	D23_CREUSE               ("Creuse"              , 200, 100, 20),
	D24_DORDOGNE             ("Dordogne"            , 240, 140, 60),
	D33_GIRONDE              ("Gironde"             , 215, 115, 35),
	D40_LANDES               ("Landes"              , 225, 125, 45),
	D47_LOT_ET_GARONNE       ("Lot-et-Garonne"      , 205, 105, 25),
	D64_PYRENEES_ATLANTIQUES ("Pyrénées-Atlantiques", 235, 135, 55),
	D79_DEUX_SEVRES          ("Deux-Sèvres"         , 195,  95, 15),
	D86_VIENNE               ("Vienne"              , 245, 145, 65),
	D87_HAUTE_VIENNE         ("Haute-Vienne"        , 185,  85,  5),

	// Occitanie
	D09_ARIEGE              ("Ariège"             , 120, 60, 180),
	D11_AUDE                ("Aude"               , 110, 50, 170),
	D12_AVEYRON             ("Aveyron"            , 130, 70, 190),
	D30_GARD                ("Gard"               , 100, 40, 160),
	D31_HAUTE_GARONNE       ("Haute-Garonne"      , 140, 80, 200),
	D32_GERS                ("Gers"               , 115, 55, 175),
	D34_HERAULT             ("Hérault"            , 125, 65, 185),
	D46_LOT                 ("Lot"                , 105, 45, 165),
	D48_LOZERE              ("Lozère"             , 135, 75, 195),
	D65_HAUTES_PYRENEES     ("Hautes-Pyrénées"    ,  95, 35, 155),
	D66_PYRENEES_ORIENTALES ("Pyrénées-Orientales", 145, 85, 205),
	D81_TARN                ("Tarn"               , 120, 60, 180),
	D82_TARN_ET_GARONNE     ("Tarn-et-Garonne"    , 108, 48, 168),

	// Pays de la Loire
	D44_LOIRE_ATLANTIQUE ("Loire-Atlantique", 60, 160,  80),
	D49_MAINE_ET_LOIRE   ("Maine-et-Loire"  , 50, 150,  70),
	D53_MAYENNE          ("Mayenne"         , 70, 170,  90),
	D72_SARTHE           ("Sarthe"          , 40, 140,  60),
	D85_VENDEE           ("Vendée"          , 80, 180, 100),

	// Provence-Alpes-Côte d'Azur
	D04_ALPES_DE_HAUTE_PROVENCE ("Alpes-de-Haute-Provence", 220, 100, 60),
	D05_HAUTES_ALPES            ("Hautes-Alpes"           , 210,  90, 50),
	D06_ALPES_MARITIMES         ("Alpes-Maritimes"        , 230, 110, 70),
	D13_BOUCHES_DU_RHONE        ("Bouches-du-Rhône"       , 200,  80, 40),
	D83_VAR                     ("Var"                    , 240, 120, 80),
	D84_VAUCLUSE                ("Vaucluse"               , 215,  95, 55),

	// DROM
	D971_GUADELOUPE ("Guadeloupe", 60, 220, 160),
	D972_MARTINIQUE ("Martinique", 50, 210, 150),
	D973_GUYANE     ("Guyane"    , 70, 230, 170),
	D974_LA_REUNION ("La Réunion", 80, 240, 180),
	D976_MAYOTTE    ("Mayotte"   , 40, 200, 140);
	
	
	/**
	* Nom du département.
	*/
	private String nom;
	
	/**
	* Couleur du département.
	*/
	private Color  couleur;
	
	
	/**
	* Crée un département avec le nom, la valeur de la couleur rouge,
	* la valeur de la couleur verte et la valeur de la couleur bleu entre 0 et 255.
	*
	* @param nom le nom du département.
	* @param r la composante rouge de la couleur entre 0 et 255.
	* @param v la composante verte de la couleur entre 0 et 255.
	* @param b la composante bleue de la couleur entre 0 et 255.
	*/
	TypeDepartement( String nom, int r, int v, int b )
	{
		this.nom = nom;
		
		this.couleur = new Color( r, v, b );
	}
	
	/**
	* Méthode pour obtenir le nom du département.
	*
	* @return le nom du département.
	*/
	public String getNom()     { return this.nom;     }
	
	/**
	* Méthode pour obtenir la couleur du département.
	*
	* @return la couleur du département.
	*/
	public Color  getCouleur() { return this.couleur; }
	
	public String toString()
	{
		return this.nom;
	}
}
