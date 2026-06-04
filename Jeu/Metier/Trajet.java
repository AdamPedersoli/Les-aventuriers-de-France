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
	}
}
