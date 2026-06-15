package Conception.IHM;

import Conception.Metier.Departement;
import Conception.Metier.TypeDepartement;
import Conception.inter.IPanelConception;
import Conception.ControleurConception;


import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;



public class PanelCreationDepartement extends JPanel implements IPanelConception, ActionListener
{
	private static final int     ERREUR_TABLEAU_INCOMPLET = 0;
	
	private ControleurConception ctrl;
	private FrameConception      frameCpt;
	
	private String               nomPanel;
	private String               nomPlateau;
	
	private Color                couleurDep;
	
	private JPanel               panelSelection;
	private JPanel               panelPlateau;
	private JPanel               panelAction;
	
	private JScrollPane          sclPnPlateau;
	
	private	JComboBox<TypeDepartement> jcbDep; //this.ctrl.getNomDeps()); couleur prédéfinie
	
	private JButton     btnPrecedent;
	private JButton     btnAnnuler;
	private JButton     btnEffacer;
	private JButton     btnSuivant;
	private JButton[][] tabBtnPlateau;
	
	private JLabel     lblCouleur;
	

	public PanelCreationDepartement( ControleurConception ctrl, FrameConception frameCpt )
	{
		this.ctrl     = ctrl;
		this.frameCpt = frameCpt;
		
		this.setLayout(new BorderLayout());
		
		this.nomPanel = "Étape 1 : Départements";
		
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
		
		
		this.jcbDep   = new JComboBox<TypeDepartement>();
		
		
		this.btnPrecedent = new JButton("Précédent");
		this.btnAnnuler   = new JButton("Annuler"  );
		this.btnEffacer   = new JButton("Effacer"  );
		this.btnSuivant   = new JButton("Suivant"  );
		
		// Couleur de département
		this.lblCouleur = new JLabel();
		this.lblCouleur.setOpaque(true);
		this.lblCouleur.setBackground( this.couleurDep );
		
		
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		this.panelSelection.add(new JLabel("Choisissez un département à placer : "));
		this.panelSelection.add(this.jcbDep);
		this.panelSelection.add(this.lblCouleur);
		
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
		
		this.jcbDep      .addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		// Si on sélectionne un département
		if (e.getSource() == this.jcbDep )
		{
			this.couleurDep = ((TypeDepartement)this.jcbDep.getSelectedItem()).getCouleur();
			
			this.lblCouleur.setBackground( this.couleurDep );
		}
		
		
		// Si on colorie le plateau
		for (int lig = 0; lig < this.tabBtnPlateau.length; lig++)
		{
			for (int col = 0; col < this.tabBtnPlateau[lig].length; col++)
			{
				if ( e.getSource() == this.tabBtnPlateau[lig][col] )
				{
					if ( this.estSurPlateau( this.couleurDep ) )
					{
						if ( this.aUnVoisin( this.couleurDep, lig, col ) )
							this.tabBtnPlateau[lig][col].setBackground(this.couleurDep);
					}
					else
					{
						this.tabBtnPlateau[lig][col].setBackground(this.couleurDep);
					}
				}
			}
		}
		
		
		if ( e.getSource() == this.btnPrecedent )
			this.frameCpt.changerPanel(FrameConception.PANEL_CONFIG, true);
		
		if ( e.getSource() == this.btnAnnuler )
			this.frameCpt.changerPanel(FrameConception.PANEL_MENU, true);
		
		if ( e.getSource() == this.btnEffacer)
			this.effacerDepartementTableau();
		
		if ( e.getSource() == this.btnSuivant )
		{
			if ( this.estPlein() )
				this.frameCpt.changerPanel(FrameConception.PANEL_CREATION_POLE, false);
			else
				this.messageErreur(PanelCreationDepartement.ERREUR_TABLEAU_INCOMPLET);
			
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
				
				this.tabBtnPlateau[lig][col].setFocusPainted     (false);
				this.tabBtnPlateau[lig][col].setContentAreaFilled(false);
				this.tabBtnPlateau[lig][col].setBackground       (null );
				this.tabBtnPlateau[lig][col].setOpaque           (true );
				
				this.tabBtnPlateau[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				this.tabBtnPlateau[lig][col].setPreferredSize(new Dimension(50, 50));
				
			}
			
		}
		
		
		// Ajout des départements à la combobox (après la création des boutons)
		this.remplirDepartementComboBox();
		
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
		// appeler ajouter dep du Controleur
	}
	
	private void effacerDepartementTableau()
	{
		for ( JButton[] ligTabBtn : this.tabBtnPlateau )
			for ( JButton btn : ligTabBtn )
				btn.setBackground(null);
	}
	
	private void remplirDepartementComboBox()
	{
		int random;
		
		int cpt = 0;
		
		while ( cpt < this.ctrl.getNbDep() )
		{
			random = (int)(Math.random() * TypeDepartement.values().length);
			
			this.jcbDep.addItem( TypeDepartement.values()[random] );
			
			if ( cpt == 0 || this.jcbDep.getItemAt(cpt - 1) != this.jcbDep.getItemAt(cpt) )
				cpt++;
			
		}
		
		this.jcbDep.setSelectedIndex(0);
		
		this.couleurDep = ((TypeDepartement)this.jcbDep.getSelectedItem()).getCouleur();
	}
	
	private boolean estSurPlateau( Color coul )
	{
		for (int lig = 0; lig < this.tabBtnPlateau.length; lig++)
		{
			for (int col = 0; col < this.tabBtnPlateau[lig].length; col++)
			{
				if ( this.tabBtnPlateau[lig][col].getBackground() == coul )
					return true;
			}
		}
		
		return false;
	}
	
	private boolean aUnVoisin( Color coul, int lig, int col )
	{
		int nbLigTab = this.tabBtnPlateau   .length;
		int nbColTab = this.tabBtnPlateau[0].length;
		
		if ( lig - 1 >= 0       && this.tabBtnPlateau[lig - 1][col].getBackground() == coul )
			return true;
		
		if ( col + 1 < nbColTab && this.tabBtnPlateau[lig][col + 1].getBackground() == coul )
			return true;
		
		if ( lig + 1 < nbLigTab && this.tabBtnPlateau[lig + 1][col].getBackground() == coul )
			return true;
		
		if ( col - 1 >= 0       && this.tabBtnPlateau[lig][col - 1].getBackground() == coul )
			return true;
		
		return false;
	}
	
	public boolean estPlein()
	{
		for ( JButton[] tabLigPlateau : this.tabBtnPlateau )
			for ( JButton btn : tabLigPlateau )
				if ( btn.getBackground() == null )
					return false;
		
		return true;
	}
	
	private void messageErreur(int erreur)
	{
		switch (erreur)
		{
			case 0  -> JOptionPane.showMessageDialog(this, "Vous devez compléter tout le plateau", "Erreur", JOptionPane.ERROR_MESSAGE);
			default -> { return; }
		}
	}
}
