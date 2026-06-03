package Metier;

public class Case
{
	private Pole pole;
	private int  posX;
	private int  posY;
	
	public Case( int posX, int posY )
	{
		this( null, posX, posY )		
	}
	
	public Case( Pole p, int posX, int posY )
	{
		this.pole = pole;
		this.posX = posX;
		this.posY = posY;
	}
	
	public int 	getX() 		{ return this.posX; }
	public int 	getY() 		{ return this.posY; }
	public Pole getPole() 	{ return this.pole; }
}
