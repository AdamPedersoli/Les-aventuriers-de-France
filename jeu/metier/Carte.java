package jeu.metier;

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
		String cheminImg = "Jeu/images/cartes/" + type + "_" + teinteImg + ".png";
		
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
	}
}
