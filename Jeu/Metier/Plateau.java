<<<<<<< HEAD
package Jeu.Metier;
=======
package Metier;
>>>>>>> lubin

import java.util.ArrayList;
import java.util.Collections;

<<<<<<< HEAD
import java.awt.Color;

/**
* Cette classe permet de gérer un Plateau.
*/
public class Plateau
{
	/**
	* Nom du plateau.
	*/
	private String                    nomPlateau;
	
	/**
	* Plateau du jeu.
	*/
	private Case[][]                  tabCase;
	
	/**
	* Taille du plateau sur l'axe X.
	*/
	private int                       tailleX;
	
	/**
	* Taille du plateau sur l'axe Y.
	*/
	private int                       tailleY;
	
	/**
	* Nombre de manche de la partie.
	*/
	private int                       nbManche;
	
	/**
	* Nombre de département.
	*/
	private int                       nbDept;
	
	/**
	* Nombre de pôle différents.
	*/
	private int                       nbPoleDiff;
	
	/**
	* Liste des départements.
	*/
	private ArrayList<Departement>    lstDep;
	
	/**
	* Liste des cases de départs.
	*/
	private ArrayList<Case>           lstCaseDepart;
	
	/**
	* Liste des moyen de transport.
	*/
	private ArrayList<MoyenTransport> lstMoyenTransport;
	
	/**
	* Liste des case contenant un pole.
	*/
	private ArrayList<Case>           lstCasePole;
	
	/**
	* Liste des cartes de la pioche.
	*/
	private ArrayList<Carte>          pioche;
	
	/**
	* Liste des cartes de la defausse.
	*/
	private ArrayList<Carte>          defausse;
	
	/**
	* Liste des trajet.
	*/
	private ArrayList<Trajet>         lstTrajet;
	
	private int                       indexManche;
	
	/**
	* Crée un plateau avec le nom, la taille, le nombre de manche, le nombre de départements et le nombre de pôles différents.
	*
	* @param nomPlateau le nom du plateau.
	* @param tailleX la taille sur l'axe X du plateau.
	* @param tailleY la taille sur l'axe Y du plateau.
	* @param nbManche le nombre de manche de la partie.
	* @param nbDept le nombre de départements.
	* @param nbPoleDiff le nombre de pôle différents.
	*/
	public Plateau( String nomPlateau, int tailleX, int tailleY, int nbManche, int nbDept, int nbPoleDiff )
	{
		this.nomPlateau = nomPlateau;
		this.tailleX    = tailleX;
		this.tailleY    = tailleY;
		this.nbManche   = nbManche;
		this.nbDept     = nbDept;
		this.nbPoleDiff = nbPoleDiff;
		this.indexManche = 0;
		
		this.lstDep            = new ArrayList<Departement>   ();
		this.lstCaseDepart     = new ArrayList<Case>          ();
		this.lstMoyenTransport = new ArrayList<MoyenTransport>();
		this.lstCasePole       = new ArrayList<Case>          ();
		this.pioche            = new ArrayList<Carte>         ();
		this.defausse          = new ArrayList<Carte>         ();
		this.lstTrajet         = new ArrayList<Trajet>        ();
	}
	
	/**
	* Crée un plateau avec un autre.
	*
	* @param p le plateau à recopier.
	*/
	private Plateau( Plateau p )
	{
		this( p.nomPlateau, p.tailleX, p.tailleY, p.nbManche, p.nbDept, p.nbPoleDiff );
	}
	
	/**
	* Crée un plateau à partir d'un enregistré.
	*
	* @param nomPlateau nom du fichier du plateau enregistré.
	*/
	public static Plateau CreerPlateau ( String nomPlateau )
	{
		Plateau p = GestionPlateau.modifier(nomPlateau);
		
		if ( p == null )
			return null;
		else
			return new Plateau ( p );
	}
	
	/**
	* Initialise les cases du plateau.
	*/
	public void initCase()
	{
		this.tabCase = new Case[this.tailleX][this.tailleY];
		for ( int lig = 0; lig < this.tabCase.length; lig++ )
			for ( int col = 0; col < this.tabCase[lig].length; col++ )
				this.tabCase[lig][col] = new Case(lig, col);
	}
	
	/**
	* Initialise les transport
	*/
	public void initMoyenTransport()
	{
		int indexTransport;
		for ( int cpt = 0 ; cpt < this.nbManche ; cpt++ )
			while ( this.lstMoyenTransport.size() > cpt )
			{
				indexTransport = ( (int) Math.random() * MoyenTransport.values().length );
				for ( MoyenTransport mT : this.lstMoyenTransport )
					if ( MoyenTransport.values()[indexTransport].equals(mT) )
						break;
				this.lstMoyenTransport.add( MoyenTransport.values()[indexTransport] );
			}
	}
	
	/**
	* Initialise la pioche
	*/
	public void initCarte()
	{
		TypePole[] typePoleUtilise = new TypePole[nbPoleDiff];
		String nomPole;

		for ( Case[] ligCase : this.tabCase )
			for ( Case ligColCase : ligCase )
			{
				for ( TypePole typeUtilise : typePoleUtilise )
					if ( ligColCase.getPole().getTypePole().equals(typePoleUtilise) )
						break;
				nomPole = ligColCase.getPole().getTypePole().getNom().toLowerCase();
				
				this.pioche.add( new Carte( nomPole, 'c' ) );
				this.pioche.add( new Carte( nomPole, 'f' ) );
			}
		this.pioche.add( new Carte( "fusee", 'c' ) );
		this.pioche.add( new Carte( "fusee", 'f' ) );
		
		Collections.shuffle( pioche );
	}

	/**
	* Mettre la derniere carte de la pioche dans la defausse.
	*/
	public void jouerCarte()
	{
		this.defausse.add( this.pioche.remove( this.pioche.size()-1 ) );
	}

	/**
	* Supprimer le premiere element de la liste pour avoir la manche actuel
	*/
	public void mancheSuivante()
	{
		this.indexManche++;
	}

	/**
	* Méthode pour obtenir le nom du plateau.
	*
	* @return le nom du plateau.
	*/
	public String getNom          () { return this.nomPlateau;           }
	
	/**
	* Méthode pour obtenir la taille sur l'axe X du plateau.
	*
	* @return la taille du plateau sur X.
	*/
	public int    getTailleX      () { return this.tailleX;              }
	
	/**
	* Méthode pour obtenir la taille sur l'axe Y du plateau.
	*
	* @return la taille du plateau sur Y.
	*/
	public int    getTailleY      () { return this.tailleY;              }
	
	/**
	* Méthode pour obtenir le nombre de manche de la partie.
	*
	* @return le nombre de manche de la partie.
	*/
	public int    getNbManche     () { return this.nbManche;             }
	
	/**
	* Méthode pour obtenir le nombre de departement du plateau.
	*
	* @return le nombre de département du plateau.
	*/
	public int    getNbDep        () { return this.nbDept;               }
	
	/**
	* Méthode pour obtenir le nombre de pôle différents du plateau.
	*
	* @return le nombre de pôle différents du plateau.
	*/
	public int    getNbPoleDiff   () { return this.nbPoleDiff;           }
	
	/**
	* Méthode pour obtenir le nombre de case de départ du plateau.
	*
	* @return le nombre de cases de départ du plateau.
	*/
	public int    getNbCaseDepart () { return this.lstCaseDepart.size(); }
	
	/**
	* Méthode pour obtenir une case du plateau avec la ligne et la colonne.
	*
	* @param lig la ligne du plateau de la case à renvoyer.
	* @param col la colonne du plateau de la case à renvoyer.
	* @return la case du plateau à la ligne et colonne cherché.
	*/
	public Case getCase ( int lig, int col )
	{
		return this.tabCase[lig][col];
	}
	
	/**
	* Méthode pour obtenir un département avec l'indice dans la liste.
	*
	* @param indice l'indice du département dans la liste.
	* @return le département de l'indice dans la liste.
	*/
	public Departement getDep ( int indice )
	{
		return this.lstDep.get(indice);
	}
	
	public ArrayList<Departement> getLstDep()
	{
		return this.lstDep;
	}
	
	/**
	* Méthode pour obtenir une case de départ avec l'indice dans la liste.
	*
	* @param indice l'indice de la case de départ dans la liste.
	* @return la case de l'indice cherché.
	*/
	public Case getCaseDepart ( int indice )
	{
		return this.lstCaseDepart.get(indice);
	}
	
	public ArrayList<Case> getLstCaseDepart()
	{
		return this.lstCaseDepart;
	}
	
	/**
	* Methode pour obtenir la carte a jouer.
	*
	* @return la carte a jouer.
	*/
	public Carte getPioche()
	{
		return this.pioche.get(this.pioche.size()-1);
	}
	
	/**
	* Methode pour obtenir la liste des case contenant un pole.
	*
	* @return la liste des case contenant un pole.
	*/
	public ArrayList<Case> getLstCasePole()
	{
		return this.lstCasePole;
	}
	
	/*
	* Methode pour obtenir la liste des moyen de transport.
	*
	* @return la liste des moyen de transport
	*/
	public ArrayList<MoyenTransport> getLstMoyenTransport()
	{
		return this.lstMoyenTransport;
	}
	
	public ArrayList<Trajet> getLstTrajet()
	{
		return this.lstTrajet;
	}
	
	/**
	* Methode pour obtenir l'index de la manche actuelle
	*
	* @return l'index de la manche actuel.
	*/
	public int getIndexManche()
	{
		return this.indexManche;
	}
	
	/**
	* Methode pour obtenir le moyen de transport de la manche actuel.
	*
	* @return le Moyen de transport de la manche actuel.
	*/
	public MoyenTransport getTransportActuel()
	{
		return this.lstMoyenTransport.get(this.indexManche);
	}
	
	/**
	* Methode pour obtenir la couleur du moyen de transport de la manche actuel.
	*
	* @return la couleur du moyen de transport actuel.
	*/
	public Color getColorTransportActuel()
	{
		return this.getTransportActuel().getCouleur();
	}
	
	/**
	* Ajoute un département à la liste des départements.
	*
	* @param dep le département à ajouter.
	*/
	public void ajouterDep ( Departement dep )
	{
		this.lstDep.add(dep);
	}
	
	/**
	* Ajoute une case à la liste des cases de départ.
	*
	* @param c la case à ajouter à la liste.
	*/
	public void ajouterCaseDepart ( Case c )
	{
		this.lstCaseDepart.add(c);
	}
	
	/**
	* Ajoute un trajet si nouveau et y rajoute le trajet.
	*
	* @param caseDep la case de depart du segment.
	* @param caseArr la case d'arriver du segment.
	* @return un boolean si l'ajout a fonctionner.
	*/
	public boolean ajouterSegment ( Case caseDep, Case caseArr )
	{
		if ( this.lstTrajet.get( this.getIndexManche() ) == null )
		{
			if ( caseDep == this.lstCaseDepart.get( this.getIndexManche() ) )
			{
				this.lstTrajet.add( new Trajet() );
				this.lstTrajet.get( this.getIndexManche() ).ajouterSegment( caseDep, caseArr );
				return true;
			}
			else
				return false;
		}
		return this.lstTrajet.get( this.getIndexManche() ).ajouterSegment( caseDep, caseArr );
	}
	
	public boolean estFinManche()
	{
		int cpt = 0;
		for ( Carte c : this.defausse )
			if ( c.getTeinte() == 'f' )
				cpt++;

		return nbPoleDiff + 1 == cpt; 
	}
	
	public boolean estFin()
	{
		return this.indexManche < this.nbManche;
	}
	
	public int getScoreFinal()
	{
		int nbZoneCapture = 0, nbPoleZone = 0, nbPoleMax = 0;
		
		for ( Departement d : this.lstDep )
			for ( int cpt = 0 ; cpt < d.getNbCase() ; cpt++ )
				if ( d.getCase(cpt).getPole().estVisite() )
					nbPoleZone++;
			nbPoleMax = Math.max( nbPoleZone, nbPoleMax );
			if ( nbPoleZone > 0 )
				nbZoneCapture++;

		return nbZoneCapture * nbPoleMax;
	}
	
	/**
	* Ajouter une case contenant un pole
	*
	* @param c la case a ajouter.
	*/
	public void ajouterCasePole ( Case c )
	{
		this.lstCasePole.add(c);
	}
=======
public class Plateau
{
	private Case[][]                  tabCase;
	private int                       tailleX;
	private int                       tailleY;
	private int                       nbManche;
	private int                       nbDept;
	private int 					  numMancheActuelle;
	private int[]                     scoreManche;
	private MoyenTransport            mancheActuelle;
	private ArrayList<Carte>          pioche;
	private ArrayList<Carte>          defausse;
	private ArrayList<MoyenTransport> moyenTransportUtilise;
	private ArrayList<Trajet>         lstTrajet;				  
	
	public Plateau( int tailleX, int tailleY, int nbManche, int nbDept )
	{
		this.tailleX  = tailleX;
		this.tailleY  = tailleY;
		this.nbManche = nbManche;
		this.nbDept   = nbDept;
		this.numMancheActuelle = 0;
		this.scoreManche = new int[this.nbManche]; 
		this.pioche   = new ArrayList<Carte>();
		this.defausse = new ArrayList<Carte>();
		
	}
	
	public Plateau( Plateau p )
	{
		this( p.tailleX, p.tailleY, p.nbManche, p.nbDept );
	}
	
	public void initPioche( String[] tabPole )
	{
		for ( String sPole : tabPole )
		{
			this.pioche.add( new Carte( 'C', sPole ) );
			this.pioche.add( new Carte( 'F', sPole ) );
		}	
	}
	
	public void initTransport( MoyenTransport[] tabTransport )
	{
		this.moyenTransportUtilise = new ArrayList<MoyenTransport>();
		
		for ( MoyenTransport transport : tabTransport )
		{
			this.moyenTransportUtilise.add(transport);
		}
		
		Collections.shuffle(this.moyenTransportUtilise);
		
		this.mancheActuelle = this.moyenTransportUtilise.get(this.numMancheActuelle);
	}
	
	public void initCase()
	{
		this.tabCases = new Case[this.tailleX][this.tailleY];
		for ( int lig = 0; lig < this.tabCase.length; lig++ )
			for ( int col = 0; col < this.tabCase[lig].length; col++ )
			{
				this.tabCases[lig][col] = new Cases(lig, col);
			}
		
	}
	
	public void recupererPlateau()
	{
		// recupere avec fichier.data
	}
	
	public void resetVisitePole()
	{
		for ( int lig = 0; lig < this.tabCase.length; lig++ )
			for ( int col = 0; col < this.tabCase[lig].length; col++ )
			{
				if (this.tabCase[lig][col].getPole() != null ) this.tabCase[lig][col].getPole().setEstVisite(false);
			}
	}
	
	public void initTrajet( int x, int y )
	{
		lstTrajet.add( new Trajet(this.tabCase[x][y]) );
	}
	
	
>>>>>>> lubin
}
