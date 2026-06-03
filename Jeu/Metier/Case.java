package Metier;

public class Case
{
	private Pole pole;
	
	private int posX;
	private int posY;
	
	
	public Case( int posX, int posY, Pole pole)
	{
		this.posX = posX;
		this.posY = posY;
		
		this.pole = pole;
	}
	
	public Case( int posX, int posY )
	{
		this ( posX, posY, null);
	}
	
	
	public int  getX    () { return this.posX; }
	public int  getY    () { return this.posY; }
	public Pole getPole () { return this.pole; }
	
	public void setPole( Pole p )
	{
		this.pole = p;
	}
	
}