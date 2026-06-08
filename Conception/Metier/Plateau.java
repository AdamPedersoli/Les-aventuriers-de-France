package Conception.Metier;

import java.util.ArrayList;
import java.util.Collections;


/**
* Cette classe permet de gérer un Plateau.
*/
public class Plateau
{
	private String                    nomPlateau;
	private Case[][]                  tabCase;
	private int                       tailleX;
	private int                       tailleY;
	private int                       nbManche;
	private int                       nbDept;
	private int                       nbPoleDiff;
	private ArrayList<Departement>    lstDep;
	private ArrayList<Case>           lstCaseDepart;
	
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
		
		this.lstDep = new ArrayList<Departement>();
	}
	
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
			{
				this.tabCase[lig][col] = new Case(lig, col);
			}
		
	}
	
	/**
	* Sauvergarde le plateau sous forme d'un fichier.
	*/
	public void sauvegarder()
	{
		GestionPlateau.sauvegarder(this);
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
	
	/**
	* Ajoute un département à la liste des départements.
	*
	* @param dep le département à ajouter.
	*/
	public void ajouterDep ( TypeDepartement typeDep, int lig, int col )
	{
		this.lstDep.add( new Departement( typeDep ) );
		
		int indexDep = -1;
		
		for (int cpt = 0; cpt < this.lstDep.size(); cpt++)
		{
			if ( this.lstDep.get(cpt).getTypeDep() == typeDep )
				indexDep = cpt;
		}
		
		if ( indexDep == -1 )
		{
			this.lstDep.add( new Departement( typeDep ) );
			
			indexDep = this.lstDep.size() - 1;
		}
		
		this.lstDep.get(indexDep).ajouterCase( this.tabCase[lig][col] );
		
		
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
	* Ajoute un pole à une case avec la ligne et la colonne.
	*
	* @param p le type pole à assigner à la case.
	* @param lig la ligne de la case.
	* @param col la colonne de la case.
	*/
	public void ajouterPole( TypePole tp, int lig, int col )
	{
		this.tabCase[lig][col].setPole( new Pole(tp) );
	}
}
