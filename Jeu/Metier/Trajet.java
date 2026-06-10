<<<<<<< HEAD
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
=======
public class Trajet
{
	private ArrayList<Case> itineraire;
	
	public Trajet(Case debut)
	{
		this.itineraire = new ArrayList<Case>;
		this.itineraire.add(debut);
		
	}
	
	public boolean ajouter(Case dep, Case dest)
	{
		if ( dep.equals(dest) ) return false;
		
		if ( this.estTete(dep) )
		{
			this.itineraire.add(0, dest);
			return true;
		}
		
		if ( this.estQueue(dep) )
		{
			this.itineraire.add(dest);
			return true;
		}
		
		return false;	
	}
	
	private boolean estQueue(Case p)
	{
		return this.itinaire.get(this.itineraire.size()-1).equals(p);
	}
	private boolean estTete(Case p)
	{
		return this.itineraire.get(0).equals(p);
	}
	
	public String toString()
	{
		String sRet = "[ ";
		
		for ( Case p : this.itineraire )
		{
			sRet += p.getNom + " ";
		}
		
		return sRet + "]";
>>>>>>> lubin
	}
}
