package Jeu.Metier;

import java.awt.Point;

/**
* Cette classe permet de gérer les cases.
*/

public class Case
{
	/**
	* Pôle présent sur la case.
	*/
	private Pole pole;
	
	/**
	* Position X de la case.
	*/
	private int posX;
	
	/**
	* Position Y de la case.
	*/
	private int posY;
	
	/**
	* Crée une case avec la position X, Y et le pôle de la case.
	*
	* @param posX la position X de la case.
	* @param posY la position Y de la case.
	* @param pole le pôle de la case.
	*/
	public Case( int posX, int posY, Pole pole)
	{
		this.posX = posX;
		this.posY = posY;
		
		this.pole = pole;
	}
	
	/**
	* Crée une case avec la position X et Y.
	*
	* @param posX la position X de la case.
	* @param posY la position Y de la case.
	*/
	public Case( int posX, int posY )
	{
		this ( posX, posY, null);
	}
	
	/**
	* Méthode pour obtenir la position X de la case.
	*
	* @return la coordonnée X de la case.
	*/
	public int  getX    () { return this.posX; }
	
	/**
	* Méthode pour obtenir la position Y de la case.
	*
	* @return la coordonnée Y de la case.
	*/
	public int  getY    () { return this.posY; }
	
	/**
	* Méthode pour obtenir le point de la position de la case.
	*
	* @return un point de la position.
	*/
	public Point getPoint() { return new Point( this.posX, this.posY ); }
	
	/**
	* Méthode pout obtenir le pôle de la case.
	*
	* @return le pôle de la case.
	*/
	public Pole getPole () { return this.pole; }
	
	/**
	* Méthode pour fixer le pôle de la case.
	*
	* @param p le pole à fixer.
	*/
	public void setPole( Pole p )
	{
		this.pole = p;
	}
	
}