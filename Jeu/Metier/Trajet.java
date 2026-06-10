package Jeu.Metier;

import java.util.ArrayList;

public class Trajet
{
	private ArrayList<Segment> lstSegment;
	
	public Trajet()
	{
		this.lstSegment = new ArrayList<Segment>();
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
	
	public boolean ajouterSegment ( Case caseDep, Case caseArr )
	{
		if ( caseDep == null || caseDep.getPole() == null ||
		     caseArr == null || caseArr.getPole() == null || caseArr.getPole().estVisite() )
			return false;

		if ( this.estVoisin( caseDep.getPole(), caseArr.getPole() ) )
		{
			this.lstSegment.add( new Segment( caseDep, caseArr ) );
			return true;
		}
		return false;
	}

	private boolean estVoisin( Pole poleDepart, Pole poleArr )
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
}
