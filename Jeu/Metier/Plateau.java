package Jeu.Metier;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public static Plateau CreerPlateau ( String nomPlateau )
	{
		Plateau p = GestionPlateau.modifier(nomPlateau);
		
		if ( p == null )
			return null;
		else
			return new Plateau ( p );
	}
	
	public void initCase()
	{
		this.tabCase = new Case[this.tailleX][this.tailleY];
		for ( int lig = 0; lig < this.tabCase.length; lig++ )
			for ( int col = 0; col < this.tabCase[lig].length; col++ )
			{
				this.tabCase[lig][col] = new Case(lig, col);
			}
		
	}
	
	public void sauvegarder()
	{
		GestionPlateau.sauvegarder(this);
	}
	
	public String getNom          () { return this.nomPlateau;           }
	public int    getTailleX      () { return this.tailleX;              }
	public int    getTailleY      () { return this.tailleY;              }
	public int    getNbManche     () { return this.nbManche;             }
	public int    getNbDep        () { return this.nbDept;               }
	public int    getNbPoleDiff   () { return this.nbPoleDiff;           }
	public int    getNbCaseDepart () { return this.lstCaseDepart.size(); }
	
	public Case getCase ( int lig, int col )
	{
		return this.tabCase[lig][col];
	}
	
	public Departement getDep ( int indice )
	{
		return this.lstDep.get(indice);
	}
	
	public Case getCaseDepart ( int indice )
	{
		return this.lstCaseDepart.get(indice);
	}
	
	
	public void ajouterDep ( Departement dep )
	{
		this.lstDep.add(dep);
	}
	
	public void ajouterCaseDepart ( Case c )
	{
		this.lstCaseDepart.add(c);
	}
}
