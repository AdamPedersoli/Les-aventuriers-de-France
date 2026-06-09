package Conception.Metier;

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
	* Crée un pôle avec un nom et une couleur.
	*
	* @param typePole le type du pôle.
	*/
	public Pole ( TypePole typePole )
	{
		this.typePole = typePole;
	}
	
	/**
	* Méthode pour obtenir le type du pôle.
	*
	* @return le type du pôle.
	*/
	public TypePole getTypePole () { return this.typePole; }
	
	
}