package jeu.metier;

// Ecriture
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

// Lecture
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileReader;

/**
* Cette classe permet de gérer l'enregistrement et la restitution de plateau.
*/
public class GestionPlateau
{
	private GestionPlateau () {}

	/**
	* Restitue un plateau sauvegardé avec son nom.
	*
	* @param nomPlateau le nom du plateau sauvegarder à restituer.
	*/
	public static Plateau modifier ( String nomPlateau )
	{
		nomPlateau = nomPlateau.replaceAll(" ", "_");
		
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "jeu/plateau/" + nomPlateau ) );

			int[] infoPlateau = new int[5];

			sc.nextLine();
			for ( int cpt = 1 ; cpt <= infoPlateau.length ; cpt++ )
			{
				infoPlateau[cpt-1] = Integer.parseInt( sc.nextLine() );
			}
			
			Plateau plateau = new Plateau ( nomPlateau, infoPlateau[0], infoPlateau[1], infoPlateau[2], infoPlateau[3], infoPlateau[4] );
			
			plateau.initCase();
			plateau.initMoyenTransport();
			
			String[] tabLigne;
			for ( int lig = 0 ; lig < infoPlateau[1] ; lig++ )
			{
				System.out.println( "Ligne : " + lig );
				tabLigne = sc.nextLine().split("\t");
				for ( int col = 0 ; col <= tabLigne.length - 1; col++ )
				{
					if ( ! ( tabLigne[col].equals("null") ) )
					{
						System.out.println( tabLigne[col] );
						plateau.getCase(lig,col).setPole( new Pole( TypePole.values()[ Integer.parseInt( tabLigne[col] ) ] ) );
						plateau.ajouterCasePole( plateau.getCase(lig, col) );
					}
				}
			}
			
			plateau.initVoisin();
			
			sc.nextLine();
			
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
				tabLigne = sc.nextLine().split("\t");
			}

			plateau.initCarte();

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
