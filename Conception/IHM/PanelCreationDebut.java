package Conception.IHM;

import Conception.Metier.Departement;
import Conception.Metier.TypeDepartement;
import Conception.Metier.Pole;
import Conception.inter.IPanelConception;
import Conception.ControleurConception;


import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.util.LinkedList;



public class PanelCreationDebut extends JPanel implements IPanelConception, ActionListener
{
	private static final int     ERREUR_TABLEAU_INCOMPLET = 0;
	
	private ControleurConception ctrl;
	private FrameConception      frameCpt;
	
	private String               nomPanel;
	private String               nomPlateau;
	
	private Color                couleurPoleDep;
	
	private JPanel               panelSelection;
	private JPanel               panelPlateau;
	private JPanel               panelAction;
	
	private JScrollPane          sclPnPlateau;
	
	private LinkedList<JButton>  lstBtnDebut;
	
	private JButton     btnPrecedent;
	private JButton     btnAnnuler;
	private JButton     btnEffacer;
	private JButton     btnEnregistrer;
	private JButton[][] tabBtnPlateau;
	
	private JLabel      lblCpt;
	

	public PanelCreationDebut( ControleurConception ctrl, FrameConception frameCpt )
	{
		this.ctrl     = ctrl;
		this.frameCpt = frameCpt;
		
		this.setLayout(new BorderLayout());
		
		this.nomPanel = "Étape 3 : Débuts";
		
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		// Panel Nord avec la sélection du département
		this.panelSelection = new JPanel();
		
		// Panel Centrale du plateau
		this.panelPlateau = new JPanel();
		
		// Panel Sud avec les bouton d'action
		this.panelAction   = new JPanel();
		
		
		this.sclPnPlateau = new JScrollPane ( 
		                                      this.panelPlateau,
		                                      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
											);
		
		
		this.lstBtnDebut    = new LinkedList<JButton>();
		
		this.btnPrecedent   = new JButton("Précédent"  );
		this.btnAnnuler     = new JButton("Annuler"    );
		this.btnEffacer     = new JButton("Effacer"    );
		this.btnEnregistrer = new JButton("Enregistrer");
		
		// Couleur du Pôle de départ à placer
		this.lblCpt = new JLabel();
		this.lblCpt.setOpaque(true);
		this.lblCpt.setBackground( this.couleurPoleDep );
		
		
		this.lblCpt.setText(/*Recup nb Manches*/);
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		this.panelSelection.add(new JLabel("Pôle de départ restant à placer : "));
		this.panelSelection.add(this.lblCpt);
		
		this.panelAction.add(this.btnPrecedent  );
		this.panelAction.add(this.btnAnnuler    );
		this.panelAction.add(this.btnEffacer    );
		this.panelAction.add(this.btnEnregistrer);
		
		
		this.add( this.panelSelection, BorderLayout.NORTH  );
		this.add( this.panelAction   , BorderLayout.SOUTH  );
		this.add( this.sclPnPlateau  , BorderLayout.CENTER );
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		// panelAction
		this.btnPrecedent  .addActionListener(this);
		this.btnAnnuler    .addActionListener(this);
		this.btnEffacer    .addActionListener(this);
		this.btnEnregistrer.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		
		for (JButton[] tabBtnLig : this.lstBtnDebut)
		{
			for (JButton btn : tabBtnLig)
			{
				if ( e.getSource() == btn )
					this.tracerCercle(btn);
			}
		}
		
		
		if ( e.getSource() == this.btnPrecedent )
			this.frameCpt.changerPanel(FrameConception.PANEL_CONFIG, true);
		
		if ( e.getSource() == this.btnAnnuler )
			this.frameCpt.changerPanel(FrameConception.PANEL_MENU, true);
		
		if ( e.getSource() == this.btnEffacer)
			this.effacerDebutTableau();
		
		if ( e.getSource() == this.btnEnregistrer )
		{
			if ( this.estComplet() < 0 )
				this.sauvegarder();
			else
				this.messageErreur(PanelCreationDebut.ERREUR_TABLEAU_INCOMPLET);
			
		}
		
	}
	
	// Méthode pour initialiser le panel avec les valeurs que l'on récupère au panel précédent
	public void init()
	{
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		// Nom du plateau
		this.nomPlateau = this.ctrl.getNom();
		
		// On vide le panel
		this.panelPlateau.removeAll();
		
		// on change le layout pour un gridlayout de la taille du plateau avec des cases de 50x50px
		int nbLig = this.ctrl.getTailleYPlateau();
		int nbCol = this.ctrl.getTailleXPlateau();
		
		this.panelPlateau.setLayout       ( new GridLayout( nbCol     , nbLig      ) );
		this.panelPlateau.setPreferredSize( new Dimension ( nbCol * 50, nbLig * 50 ) );
		
		
		// Création des boutons du plateau
		this.tabBtnPlateau = new JButton[this.ctrl.getTailleYPlateau()][this.ctrl.getTailleXPlateau()];
		
		for(int lig = 0; lig < this.tabBtnPlateau.length; lig++)
		{
			for(int col = 0; col < this.tabBtnPlateau[lig].length; col++)
			{
				this.tabBtnPlateau[lig][col] = new JButton();
				
				this.tabBtnPlateau[lig][col].setContentAreaFilled(false);
				this.tabBtnPlateau[lig][col].setFocusPainted     (false);
				this.tabBtnPlateau[lig][col].setBackground       (null /*récup couleur dep*/);
				this.tabBtnPlateau[lig][col].setOpaque           (true );
				this.tabBtnPlateau[lig][col].setIcon             (null /*Mettre image pole*/);
				
				this.tabBtnPlateau[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				this.tabBtnPlateau[lig][col].setPreferredSize(new Dimension(50, 50));
				
			}
			
		}
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		// Ajout des labels du plateau dans le panel Plateau
		for (int lig = 0; lig < this.tabBtnPlateau.length; lig++)
			for (int col = 0; col < this.tabBtnPlateau[lig].length; col++)
				this.panelPlateau.add( this.tabBtnPlateau[lig][col] );
		
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		// Ajout des listener sur le plateau
		for (int lig = 0; lig < this.tabBtnPlateau.length; lig++)
			for (int col = 0; col < this.tabBtnPlateau[lig].length; col++)
				this.tabBtnPlateau[lig][col].addActionListener(this);
		
	}
	
	public String getNom() { return this.nomPanel; }
	
	public void sauvegarder()
	{
		this.ctrl.sauvegarder();
		
		this.frameCpt.changerPanel( FrameConception.PANEL_MENU, false );
	}
	
	public void paintChildren(Graphics g)
	{
		super.paintChildren(g);
		
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.BLACK);
		
		Point p;
		
		int marge = 1; // espace entre le bouton et le cercle
		
		for ( JButton btn : this.lstBtnDebut )
		{
			// coordonnées du bouton relatives à ce panel
			p = SwingUtilities.convertPoint(btn, btn.getWidth/2, btn.getHeight()/2, this);
			
			g2d.drawOval(
							p.x - marge,
							p.y - marge,
							btn.getWidth () + marge * 2,
							btn.getHeight() + marge * 2
						);
		}
		
		
	}
	
	private void effacerDebutTableau()
	{
		for ( JButton[] ligTabBtn : this.tabBtnPlateau )
			for ( JButton btn : ligTabBtn )
				btn.setBackground(null);
	}
	
	public int estComplet()
	{
		// comparer taille liste et nb Manches
		// renvoi le nombre de début à placer restant et -1 si ils y sont tous
	}
	
	private void tracerCercle(JButton btn)
	{
		if ( lstBtnDebut.contains(btn) )
			this.lstBtnDebut.remove(btn);
		else
			this.lstBtnDebut.add(btn);
		
		this.repaint();
	}
	
	private void messageErreur(int erreur)
	{
		switch (erreur)
		{
			case 0  -> JOptionPane.showMessageDialog(this, "Il reste " + this.estComplet() + "départ à placer.", "Erreur", JOptionPane.ERROR_MESSAGE);
			default -> { return; }
		}
	}
}
