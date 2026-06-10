package Jeu.IHM;

import javax.swing.*;
import java.awt.*;

public class FrameMenuJeu extends JFrame
{
	private Controleur ctrl;

	public FrameMenuJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Menu Les aventuriers de la France");
		this.setSize(800, 800);
		this.setLocation(0, 0);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new PanelMenuJeu(this.ctrl), BorderLayout.CENTER);
		this.setVisible(true);
	}
}

/*
CONTROLEUR :
- getNomPlateaux()
	→ retourne tableau String[][] des plateaux affichés dans le menu

- getLstDepartements()
	→ retourne ArrayList<Departement>
	→ utilisé pour afficher les zones colorées du plateau

- getLstCasePole()
	→ retourne ArrayList<Case>
	→ utilisé pour afficher les pôles (cases cliquables)

- getSegments()
	→ retourne ArrayList<Segment>
	→ utilisé pour dessiner les liaisons créées par le joueur

- ajouterSegment(Case a, Case b)
	→ ajoute une liaison entre deux cases si les règles sont respectées
	→ vérifie validité + évite les doublons
	→ return boolean (true si ajouté)

- sontAdjacentes(Case a, Case b)
	→ vérifie si deux cases peuvent être reliées selon les règles du jeu


MODEL :
Case :
- getX(), getY()
	→ coordonnées de la case sur la grille

- getPole()
	→ retourne le pôle associé à la case (image + type)


Segment :
- getCase1(), getCase2()
	→ extrémités de la liaison


Departement :
- getCouleur()
	→ couleur d’affichage du département

- getLstCases()
	→ liste des cases appartenant au département


Pole :
- getTypePole()
	→ type du pôle (gare, port, etc.)


TypePole :
- getImage()
	→ image affichée sur la case
*/
