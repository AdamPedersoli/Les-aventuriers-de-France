package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// import controleur;

public class PanelSolo extends JPanel implements ActionListener
{
	// panel Principal
	private FrameMenu frameMenu;
	private JPanel panelGrilleDroite;
	private JPanel panelJeuGauche;

	private JPanel plateauDeJeu;
	// private Controleur ctrl;

	public PanelSolo(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
		// labels de droite
		JLabel labelScore = new JLabel("Score : " /*+ frameMenu.getCtrl().getScore()*/);
		JLabel labelScoreFinal = new JLabel("Score Final : " /*+ frameMenu.getCtrl().getScoreFinal()*/);

		// label de gauche 
		JPanel panelPioche   = new JPanel();
		JPanel panelCartes   = new JPanel();
		JPanel panelBoutons  = new JPanel();
		JButton buttonPasser = new JButton("Passer");
		JButton buttonPioche = new JButton("");

		// init panel
		this.setLayout(new GridLayout(1, 2));
		this.panelGrilleDroite = new JPanel();
		this.panelJeuGauche = new JPanel();
		this.panelGrilleDroite.setLayout(new GridLayout(4,1));
		this.panelJeuGauche.setLayout(new GridLayout(4,1));

		this.plateauDeJeu = new JPanel();
		this.plateauDeJeu.setLayout(new GridLayout(4, 4));

		// éléments de Droite
		this.panelGrilleDroite.add(new JLabel("Nom du Plateau : " /*+ frameMenu.getCtrl().getNomPlateau()*/));
		this.panelGrilleDroite.add(this.plateauDeJeu);
		this.panelGrilleDroite.add(labelScore);
		this.panelGrilleDroite.add(labelScoreFinal);

		// éléments de Gauche
		panelPioche.add(buttonPioche);
		panelBoutons.add(buttonPasser);
		this.panelJeuGauche.add(panelPioche);
		this.panelJeuGauche.add(panelCartes);
		this.panelJeuGauche.add(panelBoutons);

		this.add(this.panelJeuGauche);
		this.add(this.panelGrilleDroite);
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}