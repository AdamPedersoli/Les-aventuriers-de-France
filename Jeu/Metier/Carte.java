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
	}
}
