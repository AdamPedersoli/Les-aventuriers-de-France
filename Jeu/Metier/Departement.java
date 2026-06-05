package Conception.Metier;

import java.awt.Color;
import java.util.ArrayList;


/**
* Cette classe énumère les départements de France.
*/
public class Departement
{	
	/**
	* Département assigné.
	*/
	private TypeDepartement typeDepartement;
	
	/**
	* Liste des cases que le département recouvre.
	*/
	private ArrayList<Case> lstCase;
	
	/**
	* Crée un département avec le nom, la valeur de la couleur rouge,
	* la valeur de la couleur verte et la valeur de la couleur bleu entre 0 et 255.
	*
	* @param typeDepartement le département que l'on souhaite instancié.
	*/
	Departement( TypeDepartement typeDepartement )
	{
		this.typeDepartement = typeDepartement;
		
		this.lstCase = new ArrayList<Case>();
	}
	
	/**
	* Méthode pour obtenir le type du département.
	*
	* @return le type de département.
	*/
	public TypeDepartement getTypeDepartement() { return this.typeDepartement; }
	
	/**
	* Méthode pour obtenir le nombre de case relié au département.
	*
	* @return le nombre de case courverte par le département.
	*/
	public int getNbCase() { return this.lstCase.size(); }

	/**
	* Ajoute une case à la liste des cases.
	*
	* @param c la case à ajouter.
	*/
	public void ajouterCase( Case c )
	{
		this.lstCase.add(c);
	}
	
	/**
	* Méthode pour obtenir une case la liste avec l'indice.
	*
	* @param indice l'indice de la case dans la liste.
	* @return la case correspondant à l'indice.
	*/
	public Case getCase( int indice )
	{
		return this.lstCase.get(indice);
	}
	
	/**
	* Méthode pour récupérer une version string à afficher.
	*
	* @return une forme affichable du département.
	*/
	public String toString()
	{
		return this.typeDepartement.getNom() + ", nb Case courverte : " + this.lstCase.size();
	}
}