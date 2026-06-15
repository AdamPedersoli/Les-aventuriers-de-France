package jeu.ihm;

import jeu.ControleurJeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelMenuJeu extends JPanel implements ActionListener
{
	private ControleurJeu ctrl;
	private String nomPlateau;
    
    private JTable tblPlateaux;
	private JButton btnJouer;
    
	private JLabel lblPioche;
	private JButton btnFinTour;
	private JButton btnDebug;
    
	private JPanel pnlDefausse;
	private JPanel pnlManche;
	private PanelPlateau pnlPlateau;

	public PanelMenuJeu(ControleurJeu ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		// Création des composants
		String[] nomCol = { "Plateaux" };
		this.tblPlateaux = new JTable(this.ctrl.getNomPlateaux(), nomCol);
		this.btnJouer = new JButton("Jouer !");

		JPanel pnlTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Postionnement des composants
		pnlTitre.add(new JLabel("Les Aventuriers de la France"));
		pnlBtn.add(this.btnJouer);

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tblPlateaux), BorderLayout.CENTER);
		this.add(pnlBtn, BorderLayout.SOUTH);

		// Activation des composants
		this.btnJouer.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// Affichage du mode debug
		if (e.getSource() == this.btnDebug)
		{
			new FrameDebug(this.ctrl);
		}
		
		// Permet de lancer une partie
		if (e.getSource() == this.btnJouer)
		{
			if (this.tblPlateaux.getSelectedRow() == -1) return;
			this.nomPlateau = this.ctrl.getNomPlateaux()[this.tblPlateaux.getSelectedRow()][0]; 
			this.ctrl.modifier(this.nomPlateau);
			
			this.removeAll();
			this.creerPnlJeu();
			this.revalidate();
			this.repaint();
		}

		// Permet d'indiquer la fin d'un tour ( piocher entre autre )
		if (e.getSource() == this.btnFinTour)
		{
			ImageIcon icon = (ImageIcon) this.lblPioche.getIcon();

			Image imgRedim = icon.getImage().getScaledInstance(
				50, 70,
				Image.SCALE_SMOOTH
			);

			JLabel carteDefausse = new JLabel(new ImageIcon(imgRedim));
			this.pnlDefausse.add(carteDefausse);
			this.ctrl.jouerCarte();
			this.pnlPlateau.aJoue = false;
			this.revalidate();
			this.repaint();
			
			if (this.ctrl.estFinManche()) 
			{
				//Si c'est la fin de la partie
				if (this.ctrl.estFin()) 
				{

					this.removeAll();
					this.fin();
					this.revalidate();
					this.repaint();
				}
				// Si c'est une simple fin de manche
				else
				{
					this.pnlDefausse.removeAll();
					this.pnlManche.removeAll();
					this.ctrl.mancheSuivante();
					// Reset de la défausse
					this.pnlDefausse.add(new JLabel("Défausse :"));
					this.pnlManche.add(new JLabel("Manche : " + (this.ctrl.getManche()+1)));
					
					// couleur de la nouvelle manche
					JLabel coulManche = new JLabel("  ");
					coulManche.setOpaque(true);
					coulManche.setBackground(this.ctrl.getLstMoyenTransport().get(this.ctrl.getManche()).getCouleur());
					this.pnlManche.add(coulManche);	
					this.lblPioche.setIcon(this.ctrl.getPioche().getImage());
					
					// Mise a jour de la bordure 
					this.pnlPlateau.majBordure();
					
					// Repaint du panel
					this.pnlPlateau.repaint();
				}
			}
			// Si c'est juste l'action de piocher une nouvelle carte
			else
			{
				this.lblPioche.setIcon(this.ctrl.getPioche().getImage());
			}
		}
	}

	// Panel De Jeu 
	private void creerPnlJeu()
	{
		this.setLayout(new BorderLayout());

		// Création des composants
		this.lblPioche    = new JLabel(this.ctrl.getPioche().getImage());
		this.btnFinTour   = new JButton("Fin du tour");
		this.btnDebug 	  = new JButton("Mode Debug" );
		this.pnlDefausse  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.pnlManche 	  = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JScrollPane scrollDefausse = new JScrollPane(this.pnlDefausse);

		scrollDefausse.setPreferredSize(new Dimension(250, 120));

		JPanel pnlCartes  = new JPanel(new BorderLayout());
		JPanel pnlPioche  = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Ajout des composants
		this.pnlManche.add(new JLabel("Manche : " + (this.ctrl.getManche()+1)));
		
		// Couleur de la manche
		JLabel coulManche = new JLabel("  ");
		coulManche.setOpaque(true);
		coulManche.setBackground(this.ctrl.getLstMoyenTransport().get(this.ctrl.getManche()).getCouleur());
		this.pnlManche.add(coulManche);
		
		// Partie Pioche
		pnlPioche.add(this.lblPioche);
		this.pnlDefausse.add(new JLabel("Défausse :"));
		pnlCartes.add(pnlPioche, BorderLayout.NORTH);
		pnlCartes.add(scrollDefausse, BorderLayout.CENTER);
		pnlCartes.add(this.pnlManche, BorderLayout.SOUTH);

		// Postionnement des composants
		JPanel pnlGrille  = new JPanel(new BorderLayout());
		JPanel pnlTitre   = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlBtnTour = new JPanel(new FlowLayout(FlowLayout.CENTER));

		this.pnlPlateau   = new PanelPlateau(this.ctrl);
        
		pnlTitre  .add(new JLabel(this.nomPlateau));
		pnlBtnTour.add(this.btnFinTour);
		pnlBtnTour.add(this.btnDebug);


		pnlGrille.add(pnlTitre, BorderLayout.NORTH);
		pnlGrille.add(this.pnlPlateau, BorderLayout.CENTER);
		pnlGrille.add(pnlBtnTour, BorderLayout.SOUTH);

		pnlCartes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGrille.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.add(pnlCartes, BorderLayout.WEST);
		this.add(pnlGrille, BorderLayout.CENTER);

		// Activation des composants
		this.btnFinTour.addActionListener(this);
		this.btnDebug.addActionListener(this);
	}

	// Ecran de fin de partie 
	private void fin()
	{
		this.setLayout(new BorderLayout());

		// Création des éléments
		JPanel pnlTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlScore = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//Positionnement des éléments
		pnlTitre.add(new JLabel("Partie terminée !"));
		pnlScore.add(new JLabel("Score final : " + this.ctrl.getScoreFinal()));

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(pnlScore, BorderLayout.CENTER);
	}
}
