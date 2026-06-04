package Conception.Metier;

// Ecriture
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

// Lecture
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileReader;

public class GestionPlateau
{
	private GestionPlateau () {}
	
	public static void sauvegarder( Plateau p )
	{
		try
		{
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream( "../plateaux/" + p.getNom() + ".data"), "UTF8" ));
			
			pw.println( p.getNom       () + "" );
			pw.println( p.getTailleX   () + "" );
			pw.println( p.getTailleY   () + "" );
			pw.println( p.getNbManche  () + "" );
			pw.println( p.getNbDep     () + "" );
			pw.println( p.getNbPoleDiff() + "" );
			
			// Pole :
			String sRet;
			for ( int lig = 0 ; lig < p.getTailleX() ; lig++ )
			{
				sRet = "";
				for ( int col = 0 ; col < p.getTailleY() ; col++ )
				{
					Pole pole = p.getCase(lig,col).getPole();
					if ( pole == null )
						sRet += null + "\t";
					else
						sRet += pole.getTypePole().ordinal() + "\t";
				}
				pw.println( sRet );
			}
			
			// Case Debut :
			Case c;
			sRet = "";
			for (int cpt = 0; cpt < p.getNbCaseDepart(); cpt++)
			{
				c = p.getCaseDepart(cpt);
				
				sRet += c.getX() + "," + c.getY() + "\t";
			}
				
			pw.println( sRet );
			
			// Departement
			Departement dep;
			sRet = "";
			for (int cpt = 0; cpt < p.getNbDep(); cpt++)
			{
				dep = p.getDep(cpt);
				
				sRet += dep.getTypeDep().ordinal();
				for (int cptCase = 0; cptCase < dep.getNbCase(); cptCase++)
				{
					c = dep.getCase(cptCase);
					
					sRet += "\t" + c.getX() + "," + c.getY();
				}
			}
		}
		catch ( Exception e ) { e.printStackTrace(); }
	}
	
	public static Plateau modifier ( String nomPlateau )
	{
		nomPlateau.replaceAll(" ", "_");
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "../plateaux/" + nomPlateau + ".data" ) );

			int[] infoPlateau = new int[5];

			sc.nextLine();
			for ( int cpt = 1 ; cpt < infoPlateau.length ; cpt++ )
			{
				infoPlateau[cpt-1] = Integer.parseInt( sc.nextLine() );
			}
			
			Plateau plateau = new Plateau ( nomPlateau + "(Copie)", infoPlateau[0], infoPlateau[1], infoPlateau[2], infoPlateau[3], infoPlateau[4] );
			
			String[] tabLigne;
			for ( int lig = 0 ; lig < infoPlateau[1] ; lig++ )
			{
				tabLigne = sc.nextLine().split("\t");
				for ( int col = 0 ; col < infoPlateau[2] ; col++ )
				{
					if ( ! ( tabLigne[col].equals("null") ) )
						plateau.getCase(lig,col).setPole( new Pole( TypePole.values()[ Integer.parseInt( tabLigne[col] ) ] ) );
				}
			}
			
			tabLigne = sc.nextLine().split("\t");
			String[] tabCoord;
			for ( int cpt = 0 ; cpt < tabLigne.length ; cpt++ )
			{
				tabCoord = tabLigne[cpt].split(",");
				plateau.ajouterCaseDepart( plateau.getCase( Integer.parseInt( tabCoord[0] ), Integer.parseInt( tabCoord[1] ) ) );
			}
			
			tabLigne = sc.nextLine().split("\t");
			for ( int cpt = 0 ; cpt < infoPlateau[3] ; cpt++ )
			{
				plateau.ajouterDep( new Departement(TypeDepartement.values()[ Integer.parseInt(tabLigne[0]) ]) );
				for ( int cptCase = 1 ; cptCase < tabLigne.length ; cptCase++ )
				{
					tabCoord = tabLigne[cptCase].split(",");
					plateau.getDep( cpt ).ajouterCase( plateau.getCase( Integer.parseInt( tabCoord[0] ), Integer.parseInt( tabCoord[1] )) );
				}
			}

			sc.close();
			
			return plateau;
		}
		catch (Exception e)
		{ 
			e.printStackTrace();
			return null;
		}
	}
}
