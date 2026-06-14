package Conception.IHM;

import Conception.Metier.Departement;
import Conception.Metier.TypePole;
import Conception.inter.IPanelConception;
import Conception.ControleurConception;


import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import java.util.ArrayList;


public class PanelCreationPole extends JPanel implements IPanelConception, ActionListener, Runnable
{
	private ControleurConception ctrl;
	private FrameConception      frameCpt;
	
	private String               nomPanel;
	private String               nomPlateau;
	
	private TypePole             poleSelect;
	
	private JPanel               panelSelection;
	private JPanel               panelPlateau;
	private JPanel               panelAction;
	
	private JScrollPane          sclPnPlateau;
	
	private JButton       btnPrecedent;
	private JButton       btnAnnuler;
	private JButton       btnEffacer;
	private JButton       btnSuivant;
	
	private JLabel[]      tabLblPole;
	
	private LabelPole[][] tabLblPlateau;
	

	public PanelCreationPole( ControleurConception ctrl, FrameConception frameCpt )
	{
		this.ctrl     = ctrl;
		this.frameCpt = frameCpt;
		
		this.setLayout(new BorderLayout());
		
		this.nomPanel = "Étape 2 : Pôles";
		
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
		
		
		this.btnPrecedent = new JButton("Précédent");
		this.btnAnnuler   = new JButton("Annuler"  );
		this.btnEffacer   = new JButton("Effacer"  );
		this.btnSuivant   = new JButton("Suivant"  );
		
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		
		this.panelAction.add(this.btnPrecedent);
		this.panelAction.add(this.btnAnnuler  );
		this.panelAction.add(this.btnEffacer  );
		this.panelAction.add(this.btnSuivant  );
		
		
		this.add( this.panelSelection, BorderLayout.NORTH  );
		this.add( this.panelAction   , BorderLayout.SOUTH  );
		this.add( this.sclPnPlateau  , BorderLayout.CENTER );
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		// panelAction
		this.btnPrecedent.addActionListener(this);
		this.btnAnnuler  .addActionListener(this);
		this.btnEffacer  .addActionListener(this);
		this.btnSuivant  .addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		
		if ( e.getSource() == this.btnPrecedent )
			this.frameCpt.changerPanel(FrameConception.PANEL_CREATION_DEPARTEMENT, true);
		
		if ( e.getSource() == this.btnAnnuler )
			this.frameCpt.changerPanel(FrameConception.PANEL_MENU, true);
		
		if ( e.getSource() == this.btnEffacer)
			this.effacerPolePlateau();
		
		if ( e.getSource() == this.btnSuivant ) {}
			// this.frameCpt.changerPanel(FrameConception.PANEL_CREATION_DEBUT, false);
		
	}
	
	// Méthode pour initialiser le panel avec les valeurs que l'on récupère au panel précédent
	public void init()
	{
		ArrayList<Integer> lstIndexPoleSelect = this.ctrl.getLstPoleSelect();
		
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		// Nom du plateau
		this.nomPlateau = this.ctrl.getNom();
		
		// On vide la selection
		this.panelSelection.removeAll();
		
		// On vide le plateau
		this.panelPlateau.removeAll();
		
		// on change le layout pour un gridlayout de la taille du plateau avec des cases de 50x50px
		int nbLig = this.ctrl.getTailleYPlateau();
		int nbCol = this.ctrl.getTailleXPlateau();
		
		this.panelPlateau.setLayout       ( new GridLayout( nbCol     , nbLig      ) );
		this.panelPlateau.setPreferredSize( new Dimension ( nbCol * 50, nbLig * 50 ) );
		
		
		this.tabLblPole = new JLabel[lstIndexPoleSelect.size()];
		
		TypePole pole;
		
		for (int cpt = 0; cpt < this.tabLblPole.length; cpt++)
		{
			pole = TypePole.values()[lstIndexPoleSelect.get(cpt)];
			
			this.tabLblPole[cpt] = new JLabel( pole.getImage() );
			
			this.tabLblPole[cpt].setTransferHandler(new PoleSourceHandler(pole));
		}
		
		
		// Création des boutons du plateau
		this.tabLblPlateau = new LabelPole[this.ctrl.getTailleYPlateau()][this.ctrl.getTailleXPlateau()];
		
		for(int lig = 0; lig < this.tabLblPlateau.length; lig++)
		{
			for(int col = 0; col < this.tabLblPlateau[lig].length; col++)
			{
				this.tabLblPlateau[lig][col] = new LabelPole(lig, col, this);
				
			}
			
		}
		
		
		
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		this.panelSelection.add( new JLabel("Glissez déposez un pôle pour l'ajouter : ") );
		
		for (int cpt = 0; cpt < this.tabLblPole.length; cpt++)
			this.panelSelection.add(this.tabLblPole[cpt]);
		
		
		// Ajout des labels du plateau dans le panel Plateau
		for (int lig = 0; lig < this.tabLblPlateau.length; lig++)
			for (int col = 0; col < this.tabLblPlateau[lig].length; col++)
				this.panelPlateau.add( this.tabLblPlateau[lig][col] );
		
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		// Ajout des listener du plateau
		GereSouris gs = new GereSouris();
		
		for (JLabel lblPole : this.tabLblPole)
		{
			lblPole.addMouseMotionListener(gs);
			lblPole.addMouseListener(gs);
		}
		
		for (int lig = 0; lig < this.tabLblPlateau.length; lig++)
		{
			for (int col = 0; col < this.tabLblPlateau[lig].length; col++)
			{
				
				this.tabLblPlateau[lig][col].addMouseMotionListener(gs);
				this.tabLblPlateau[lig][col].addMouseListener(gs);
			}
		}
		
		
	}
	
	public String getNom() { return this.nomPanel; }
	
	private void effacerPolePlateau()
	{
		for ( LabelPole[] ligTabLbl : this.tabLblPlateau )
			for ( LabelPole lbl : ligTabLbl )
				lbl.setIcon(null);
		
		this.run();
	}
	
	public void sauvegarder()
	{
		for ( LabelPole[] tabLigLbl : this.tabLblPlateau )
		{
			for ( LabelPole lbl : tabLigLbl )
			{
				this.ctrl.ajouterPole( lbl.getLig(), lbl.getCol(), lbl.getPole() );
			}
		}
	}
	
	public void run()
	{
		this.repaint();
	}
	
	public void paintChildren(Graphics g)
	{
		super.paintChildren(g);

		int lig, col;
		for ( int ligLbl = 0 ; ligLbl < this.tabLblPlateau.length ; ligLbl++ )
			for ( int colLbl = 0 ; colLbl < this.tabLblPlateau[ligLbl].length ; colLbl++ )
				if ( this.tabLblPlateau[ligLbl][colLbl].getIcon() != null )
				{
					for ( int cpt = 0 ; cpt < 8 ; cpt++ )
					{
						lig = ligLbl;
						col = colLbl;
						
						switch (cpt)
						{
							case 0, 1, 2 -> lig--;
							case 4, 5, 6 -> lig++;
						}

						switch (cpt)
						{
							case 0, 6, 7 -> col--;
							case 2, 3, 4 -> col++;
						}
						
						
						while ( lig >= 0 && lig < this.tabLblPlateau        .length &&
							    col >= 0 && col < this.tabLblPlateau[ligLbl].length &&
							    tabLblPlateau[lig][col].getIcon() == null              )
						{
						
							// Ligne :
							switch ( cpt )
							{
								case 0, 1, 2 -> lig-- ;
								case 4, 5, 6 -> lig++ ;
							}
							
							// Colonne :
							switch ( cpt )
							{
								case 0, 6, 7 -> col-- ;
								case 2, 3, 4 -> col++ ;
							}
						}
						
						if ( lig >= 0 && lig < this.tabLblPlateau        .length &&
							 col >= 0 && col < this.tabLblPlateau[ligLbl].length &&
							 tabLblPlateau[lig][col].getIcon() != null )
						{
							LabelPole lblA = this.tabLblPlateau[lig   ][col   ];
							LabelPole lblB = this.tabLblPlateau[ligLbl][colLbl];
							
							Point pA = SwingUtilities.convertPoint(lblA, lblA.getWidth()/2, lblA.getHeight()/2, this);
							Point pB = SwingUtilities.convertPoint(lblB, lblB.getWidth()/2, lblB.getHeight()/2, this);
							
							
							g.setColor( Color.BLACK );
							g.drawLine( (int)pA.getX(), (int)pA.getY(), (int)pB.getX(), (int)pB.getY() );
						}
					}
				}
	}
	
	private class GereSouris extends MouseAdapter
	{
		
		public void mouseDragged(MouseEvent e)
		{
			for (JLabel lbl : PanelCreationPole.this.tabLblPole)
			{
				if ( e.getSource() == lbl )
					lbl.getTransferHandler().exportAsDrag(lbl, e, TransferHandler.COPY);
			}
		}
		
		public void mousePressed(MouseEvent e)
		{
			if ( SwingUtilities.isRightMouseButton(e) )
			{
				for (LabelPole[] tabLigLbl : PanelCreationPole.this.tabLblPlateau)
					for (LabelPole lbl : tabLigLbl)
						if ( e.getSource() == lbl )
						{
							lbl.setPole(null);
						}
				
			}
		}
	}
}
