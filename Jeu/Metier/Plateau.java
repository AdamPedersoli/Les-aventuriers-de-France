package Metier;

import java.util.ArrayList;
import java.util.Collections;

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
	
	
}
