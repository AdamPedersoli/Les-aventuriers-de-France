package Jeu.Metier;

import java.awt.Point;

public class Segment
{
	
	private Point A;
	
	private Point B;
	
	
	public Segment( Point A, Point B )
	{
		this.A = A;
		
		this.B = B;
	}
	
	public Point getA () { return this.A; }
	public Point getB () { return this.B; }
	
	public boolean seCroise( Segment seg )
	{
		
	}
}