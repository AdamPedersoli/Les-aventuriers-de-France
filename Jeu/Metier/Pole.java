package Jeu.Metier;

import java.awt.Color;
import java.util.ArrayList;

/**
* Cette classe permet de créer un pôle.
*/

public class Pole
{
	
	/**
	* Type du pôle.
	*/
	private TypePole typePole;
	
	/**
	* Position du pôle sur le plateau.
	*/
	private Case position;
	
	/**
	* booléen si le pôle a été visité par un joueur.
	*/
	private boolean estVisite;
	
	/**
	* Liste des voisin du pôle.
	*/
	private ArrayList<Case> lstVoisin;
	
	/**
	* Crée un pôle avec un nom et une couleur.
	*
	* @param typePole le type du pôle.
	*/
	public Pole ( TypePole typePole )
	{
		this.typePole = typePole;
		
		this.estVisite = false;
		
		this.lstVoisin = new ArrayList<Case>();
	}
	
	/**
	* Méthode pour obtenir le type du pôle.
	*
	* @return le type du pôle.
	*/
	public TypePole getTypePole () { return this.typePole; }
	
	/**
	* Méthode pour savoir si le pôle a été visité.
	*
	* @return true si le pôle a été visité sinon false.
	*/
	public boolean estVisite () { return this.estVisite; }
	
	/**
	* Méthode pour fixer si le pôle a été visité.
	*
	* @param estVisite booléen de la visite du pôle.
	*/
	public void setEstVisite ( boolean estVisite )
	{
		this.estVisite = estVisite;
	}
	
	/**
	* Méthode pour ajouter un voisin au pôle.
	*
	* @param voisin le voisin ajouté.
	*/
	public void ajouterVoisin( Case voisin )
	{
		this.lstVoisin.add( voisin );
	}
	
	/**
	* Méthode pour savoir si le pôle passé en paramètre est voisin de celui qui appel la méthode.
	*
	* @param p le pôle à vérifier.
	* @return vrai s'il est voisin sinon faux.
	*/
	public boolean estVoisin( Case c )
	{
		return this.lstVoisin.contains( c );
	}
	
	public ArrayList<Case> getLstVoisin()
	{
		return this.lstVoisin;
	}
<<<<<<< HEAD
=======
	
>>>>>>> 77e3917 (Amélioration de l'ihm)
}
