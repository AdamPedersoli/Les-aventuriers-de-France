package Jeu.IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelMenuJeu extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private String nomPlateau;
    
    private JTable tblPlateaux;
	private JButton btnJouer;
    
	private JLabel lblPioche;
	private JButton btnFinTour;
    
	private JPanel pnlDefausse;
	private PanelPlateau pnlPlateau;

	public PanelMenuJeu(Controleur ctrl)
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
			this.pnlDefausse.add(new JLabel(this.lblPioche.getIcon()));
			this.ctrl.jouerCarte();

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
				    this.ctrl.mancheSuivante();
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
		this.setLayout(new GridLayout(1, 2));

		this.lblPioche    = new JLabel(this.ctrl.getPioche().getImage());
		this.btnFinTour   = new JButton("Fin du tour");
		this.pnlDefausse  = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JPanel pnlCartes  = new JPanel(new BorderLayout());
		JPanel pnlPioche  = new JPanel(new FlowLayout(FlowLayout.CENTER));

		pnlPioche.add(this.lblPioche);
		pnlCartes.add(pnlPioche, BorderLayout.NORTH);
		pnlCartes.add(this.pnlDefausse, BorderLayout.CENTER);

		JPanel pnlGrille  = new JPanel(new BorderLayout());
		JPanel pnlTitre   = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnlBtnTour = new JPanel(new FlowLayout(FlowLayout.CENTER));

        
		this.pnlPlateau   = new PanelPlateau(this.ctrl);
        
		pnlTitre  .add(new JLabel(this.nomPlateau));
		pnlBtnTour.add(this.btnFinTour);


		pnlGrille.add(pnlTitre, BorderLayout.NORTH);
		pnlGrille.add(this.pnlPlateau, BorderLayout.CENTER);
		pnlGrille.add(pnlBtnTour, BorderLayout.SOUTH);

		this.add(pnlCartes);
		this.add(pnlGrille);

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
