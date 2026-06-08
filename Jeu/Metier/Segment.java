package Jeu.Metier;

import java.awt.Point;


/**
* Cette classe permet de créer un segment.
*/
public class Segment
{
	/**
	* 1er point du segment.
	*/
	private Point A;
	
	/**
	* 2er point du segment.
	*/
	private Point B;
	
	/**
	* Crée un segment avec 2 points.
	*
	* @param A le 1er point.
	* @param B le 2ème point.
	*/
	public Segment( Point A, Point B )
	{
		this.A = A;
		
		this.B = B;
	}
	
	/**
	* Méthode pour obtenir le 1er point du segment.
	* 
	* @return le 1er point du segment.
	*/
	public Point getA () { return this.A; }
	
	/**
	* Méthode pour obtenir le 2ème point du segment.
	* 
	* @return le 2ème point du segment.
	*/
	public Point getB () { return this.B; }
	
	/**
	* Méthode pour savoir si le segment se croise avec un autre.
	*
	* @param seg le segment avec lequel on veut vérifier le croisement.
	* @return true si les segments se croise sinon false.
	*/
	public boolean seCroise( Segment seg )
	{
		double x1 = this.A.getX();
		double y1 = this.A.getY();
		
		double x2 = this.B.getX();
		double y2 = this.B.getY();
		
		double x3 = seg.A.getX();
		double y3 = seg.A.getY();
		
		double x4 = seg.B.getX();
		double y4 = seg.B.getY();
		
		
		double a1 = 0.0;
		double b1 = 0.0;
		
		double a2 = 0.0;
		double b2 = 0.0;
		
		double x = 0.0;
		double y = 0.0;
		
		
		
		if ( p1.equals(p2) || p1.equals(p3) || p1.equals(p4) ||
		     p2.equals(p3) || p2.equals(p4) ||
			 p3.equals(p4)                                      )
			return false;
		
		a1 = (y2 - y1) / (x2 - x1);
		
		b1 = y1 - a1 * x1;
		
		System.out.println( "y = " + a1 + "x + " + b1 );
		
		
		
		a2 = (y4 - y3) / (x4 - x3);
		
		b2 = y3 - a2 * x3;
		
		System.out.println( "y = " + a2 + "x + " + b2 );
		
		
		
		if ( a1 == a2 )
		{
			System.out.println("segments parallèle");
			return false;
		}
		
		
		if ( x1 == x2 )
			x = x1;
		else
			x = (b2 - b1) / (a1 - a2);
		
		
		if ( x3 == x4 )
		{
			x = x3;
			
			y = a1 * x + b1;
		}
		else
		{
			y = a2 * x + b2;
		}
		
		
		
		
		double xMaxSeg1 = Double.max( x1, x2 );
		double yMaxSeg1 = Double.max( y1, y2 );
		
		double xMinSeg1 = Double.min( x1, x2 );
		double yMinSeg1 = Double.min( y1, y2 );
		
		
		double xMaxSeg2 = Double.max( x3, x4 );
		double yMaxSeg2 = Double.max( y3, y4 );
		
		double xMinSeg2 = Double.min( x3, x4 );
		double yMinSeg2 = Double.min( y3, y4 );
		
		
		System.out.println( "intersection :" + " (" + x + ";" + y + ")");
		
		
		if ( Double.isFinite(x) && Double.isFinite(y) )
		{
			System.out.println( "(" + xMaxSeg1 + ":" + yMaxSeg1 + ")" + " " + "(" + xMinSeg1 + ":" + yMinSeg1 + ")" );
			
			return x >= xMinSeg1 && x <= xMaxSeg1 && y >= yMinSeg1 && y <= yMaxSeg1 &&
			       x >= xMinSeg2 && x <= xMaxSeg2 && y >= yMinSeg2 && y <= yMaxSeg2;
		}
		else
			return false;
		
	}
}