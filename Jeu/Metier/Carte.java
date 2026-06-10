<<<<<<< HEAD
package Jeu.Metier;

import javax.swing.ImageIcon;

public class Carte
{
	private String    type;
	private char      teinte;
	private ImageIcon image;
	
	public Carte( String type, char teinte )
	{
		this.type   = type;
		this.teinte = teinte;
		
		String teinteImg = teinte == 'c' ? "clair" : "fonce";
		String cheminImg = "Jeu/images/cartes/" + type + "_" + teinteImg;
		
		this.image = new ImageIcon( cheminImg );
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public char getTeinte()
	{
		return this.teinte;
	}
	
	public ImageIcon getImage()
	{
		return this.image;
=======
package Metier;

public class Carte
{	
	private char   teinte;
	private String   type;
	
	public Carte(char teinte, String type);
	{
		this.type = type;
		this.teinte = teinte;
	}
	
	public String getType() { return this.type;   }
	public char getTeinte() { return this.teinte; }
	
	public void   setType(char teinte) {this.teinte = teinte;}
	public void setTeinte(String type) {this.type = type    ;}
	
	public String toString()
	{
		return "Carte de type :" + this.type + " et de teinte :" + this.teinte;
>>>>>>> lubin
	}
}
