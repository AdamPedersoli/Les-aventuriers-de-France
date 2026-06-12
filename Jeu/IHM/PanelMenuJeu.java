package Jeu.IHM;

import Jeu.ControleurJeu;

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
    
	private JPanel pnlDefausse;
	private JPanel pnlManche;
	private PanelPlateau pnlPlateau;

	public PanelMenuJeu(ControleurJeu ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		String[] nomCol = { "Plateaux" };
		this.tblPlateaux = new JTable(this.ctrl.getNomPlateaux(), nomCol);
		this.btnJouer = new JButton("Jouer !");

		JPanel pnlTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlTitre.add(new JLabel("Les Aventuriers de la France"));
		pnlBtn.add(this.btnJouer);

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(new JScrollPane(this.tblPlateaux), BorderLayout.CENTER);
		this.add(pnlBtn, BorderLayout.SOUTH);

		this.btnJouer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
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
				if (this.ctrl.estFin()) 
				{

					this.removeAll();
					this.fin();
					this.revalidate();
					this.repaint();
				}
				else
				{
					this.pnlDefausse.removeAll();
					this.pnlManche.removeAll();
					this.ctrl.mancheSuivante();
					this.pnlDefausse.add(new JLabel("Défausse :"));
					this.pnlManche.add(new JLabel("Manche : " + (this.ctrl.getManche()+1)));
					this.lblPioche.setIcon(this.ctrl.getPioche().getImage());
					this.pnlPlateau.repaint();
				}
			}
			else
			{
				this.lblPioche.setIcon(this.ctrl.getPioche().getImage());
			}
		}
	}

	private void creerPnlJeu()
	{
		this.setLayout(new BorderLayout());

		this.lblPioche    = new JLabel(this.ctrl.getPioche().getImage());
		this.btnFinTour   = new JButton("Fin du tour");
		this.pnlDefausse  = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.pnlManche 	  = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JScrollPane scrollDefausse = new JScrollPane(this.pnlDefausse);

		scrollDefausse.setPreferredSize(new Dimension(250, 120));

		JPanel pnlCartes  = new JPanel(new BorderLayout());
		JPanel pnlPioche  = new JPanel(new FlowLayout(FlowLayout.CENTER));

		this.pnlManche.add(new JLabel("Manche : " + (this.ctrl.getManche()+1)));
		pnlPioche.add(this.lblPioche);
		this.pnlDefausse.add(new JLabel("Défausse :"));
		pnlCartes.add(pnlPioche, BorderLayout.NORTH);
		pnlCartes.add(scrollDefausse, BorderLayout.CENTER);
		pnlCartes.add(this.pnlManche, BorderLayout.SOUTH);

		JPanel pnlGrille  = new JPanel(new BorderLayout());
		JPanel pnlTitre   = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlBtnTour = new JPanel(new FlowLayout(FlowLayout.CENTER));

		this.pnlPlateau   = new PanelPlateau(this.ctrl);
        
		pnlTitre  .add(new JLabel(this.nomPlateau));
		pnlBtnTour.add(this.btnFinTour);


		pnlGrille.add(pnlTitre, BorderLayout.NORTH);
		pnlGrille.add(this.pnlPlateau, BorderLayout.CENTER);
		pnlGrille.add(pnlBtnTour, BorderLayout.SOUTH);

		pnlCartes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGrille.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		this.add(pnlCartes, BorderLayout.WEST);
		this.add(pnlGrille, BorderLayout.CENTER);

		this.btnFinTour.addActionListener(this);
	}

	private void fin()
	{
		this.setLayout(new BorderLayout());

		JPanel pnlTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlScore = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlTitre.add(new JLabel("Partie terminée !"));
		pnlScore.add(new JLabel("Score final : " + this.ctrl.getScoreFinal()));

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(pnlScore, BorderLayout.CENTER);
	}
}
