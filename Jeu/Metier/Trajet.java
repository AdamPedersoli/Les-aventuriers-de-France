package Jeu.Metier;

import java.util.ArrayList;

public class Trajet
{
	private ArrayList<Segment> lstSegment;
	private ArrayList<Case>    lstCase;
	
	public Trajet()
	{
		this.lstSegment = new ArrayList<Segment>();
		this.lstCase    = new ArrayList<Case>();
	}
	
	public boolean estDansTrajet( Case c )
	{
		if ( c == null )
			return false;

		for ( Segment s : lstSegment )
			if ( s.getCaseA().equals(c) )
				return true;

		return false;
	}
	
	public boolean ajouterSegment ( Case caseDep, Case caseArr, boolean estPremier )
	{
		if ( caseDep == null || caseDep.getPole() == null ||
		     caseArr == null || caseArr.getPole() == null || this.lstCase.contains( caseArr ) )
			return false;

		if ( this.estVoisin( caseDep.getPole(), caseArr ) )
		{
			if ( estPremier )
			{
				this.lstSegment.add( new Segment( caseDep, caseArr ) );
				this.lstCase   .add( caseDep );
				caseDep.getPole().setEstVisite(true);
				this.lstCase   .add( caseArr );
				caseArr.getPole().setEstVisite(true);
				return true;
			}
			else
			{
				if ( caseDep == this.lstCase.get(0) )
				{
					this.lstSegment.add( new Segment( caseDep, caseArr ) );
					this.lstCase   .add( 0, caseArr );
					caseArr.getPole().setEstVisite(true);
					return true;
				}
				
				if ( caseDep == this.lstCase.get( this.lstCase.size()-1 ) )
				{
					this.lstSegment.add( new Segment( caseDep, caseArr ) );
					this.lstCase   .add( caseArr );
					caseArr.getPole().setEstVisite(true);
					return true;
				}
			}
		}
		return false;
	}

	private boolean estVoisin( Pole poleDepart, Case poleArr )
	{
		return poleDepart.estVoisin(poleArr);
	}
	
	public int getTaillelstSegment()
	{
		return this.lstSegment.size();
	}
	
	public ArrayList<Segment> getLstSegment()
	{
		return this.lstSegment;
	}
	
	public ArrayList<Case> getLstCase()
	{
		return this.lstCase;
	}
	
	public Case getPremier()
	{
		return this.lstSegment.get(0).getCaseA();
	}

	public Case getDernier()
	{
		return this.lstSegment.get( this.lstSegment.size() - 1 ).getCaseB();
	}
}
