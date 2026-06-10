package Conception.IHM;

import Conception.Metier.Departement;
import Conception.Metier.Plateau;
import Conception.Metier.TypeDepartement;
import Conception.Metier.TypePole;
import Conception.inter.IPanelConception;
import Conception.ControleurConception;


import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.io.File;



public class PanelCreationDebut extends JPanel implements IPanelConception, ActionListener
{
	private final int           NOMBRE_DEPARTEMENT_COMBOBOX = 5;
	
	private ControleurConception ctrl;
	private FrameConception      frameCpt;
	private String               nomPanel;
	
	private String               nomPlateau;
	private Plateau              plateau;
	private JPanel               panelInfo;
	private JPanel               panelVisuel;
	private JPanel               panelPlateau;
	
	private	JComboBox<TypePole>        jcbPoles; //this.ctrl.getNomPoles());
	private	JComboBox<TypeDepartement> jcbDep; //this.ctrl.getNomDeps()); couleur prédéfinie
	
	private JButton btnRefreshDep;
	private JButton btnValiderDep;
	private JButton btnValiderTransport;
	private JButton btnAnnuler;
	private JButton btnEffacer;
	private JButton btnApercu;
	private JButton btnValider;
	
	private JScrollBar scrollbarRGB;
	
	private JLabel[][] tabLblPlateau;
	private JLabel     lblPole;
	private JLabel     labelCouleur;
	
	private TypePole type;
	
	private ArrayList<Integer> lstIndexTypePole;
	
	
	private boolean estDep;
	private boolean estTransport;
	private ArrayList<Color> lstCouleurTransport;

	public PanelCreationDebut( ControleurConception ctrl, FrameConception frameCpt )
	{
		this.ctrl     = ctrl;
		this.frameCpt = frameCpt;
		this.setLayout(new BorderLayout());
		
		this.nomPanel = "Étape 3 : Début";
		
		this.plateau  = this.ctrl.getPlateau();
		
		this.lstCouleurTransport = new ArrayList<Color>();
		
		
		
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		//Information sur la gauche
		this.panelInfo = new JPanel(new GridLayout(5,1));
		
		// sous-panel de panelInfo 
		JPanel panelPoles        = new JPanel();
		JPanel panelTabDep       = new JPanel( new GridLayout(2,1) );
		JPanel panelDep          = new JPanel();
		JPanel panelTabTransport = new JPanel( new GridLayout(2,1) ); 
		JPanel panelTransport    = new JPanel();
		JPanel panelDepScrollbar = new JPanel();
		
		
		// Panel droit contenant le plateau
		this.panelVisuel = new JPanel();
		
		// Plateau du jeux dans panelVisuel
		this.panelPlateau = new JPanel(new GridLayout(3,1));
		
		// sous-panel de panelPlateau
		JPanel panelBouton   = new JPanel();
		
		
		this.jcbPoles = new JComboBox<TypePole>();
		
		
		this.jcbDep   = new JComboBox<TypeDepartement>();
		
		this.rafraichirSelectionDep();
		
		
		this.btnRefreshDep       = new JButton("Rafraichir"            );
		this.btnValiderDep       = new JButton("Valider le département");
		this.btnValiderTransport = new JButton("Valider le transport"  );
		this.btnAnnuler          = new JButton("Annuler"               );
		this.btnEffacer          = new JButton("Effacer"               );
		this.btnApercu           = new JButton("Aperçu"                );
		this.btnValider          = new JButton("Valider"               );
		
		
		this.scrollbarRGB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);
		
		
		// Image Pole pas plateau
		this.type = TypePole.values()[0];
		this.lblPole = new JLabel(TypePole.values()[0].getImage());
		this.lblPole.setTransferHandler(new TransferHandler("icon"));
		
		
		this.labelCouleur = new JLabel();
		
		// Couleur en temps réel à partir de la scrollbar (RGB : on balaie le rouge)
		this.labelCouleur.setBackground(Color.BLACK);
		
		
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		// panelPoles
		panelPoles.add(new JLabel("Sélectionnez les pôles à ajouter au plateau : "));
		panelPoles.add(jcbPoles);
		panelPoles.add(lblPole);
		
		// panelDep
		panelDep.add(new JLabel("Départements : "));
		panelDep.add(jcbDep);
		panelDep.add(btnRefreshDep);
		
		panelTabDep.add(panelDep);
		panelTabDep.add( btnValiderDep );
		
		//panelDepScrollbar
		panelDepScrollbar.setLayout(new GridLayout(1,2));
		panelDepScrollbar.add(scrollbarRGB);
		panelDepScrollbar.add(this.labelCouleur);

		// panelTransport
		panelTransport.add(new JLabel("Transports : "));
		
		panelTabTransport.add( panelTransport );
		panelTabTransport.add( btnValiderTransport );
		
		this.panelInfo.add(panelPoles);
		this.panelInfo.add(panelTabDep);
		this.panelInfo.add(panelTabTransport);
		this.panelInfo.add(panelDepScrollbar);
		
		panelBouton.add(this.btnAnnuler);
		panelBouton.add(this.btnEffacer);
		panelBouton.add(this.btnApercu );
		panelBouton.add(this.btnValider);
		
		// Le nom du plateau vient de la configuration
		this.panelPlateau.add(new JLabel(this.nomPlateau, SwingConstants.CENTER));
		this.panelPlateau.add(this.panelVisuel);
		this.panelPlateau.add(panelBouton);
		
		
		this.add(this.panelInfo);
		this.add(this.panelPlateau);
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		


		

		
		
		



		// 0..84   : r=255, g décroît, b=0
		// 85..169 : g=0  , b croît  , r décroît
		// 170..255: b=255, r croît  , g=0
		this.scrollbarRGB.addAdjustmentListener(new AdjustmentListener() 
		{
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) 
			{
				int v = scrollbarRGB.getValue();
				int r, g, b;

				if (v <= 84) 
				{
					r = 255;
					g = 255 - (v * 3);
					b = 0;
				} 
				else if (v <= 169) 
				{
					int v2 = v - 85;
					r = 255 - (v2 * 3);
					g = 0;
					b = v2 * 3;
				} 
				else 
				{
					int v3 = v - 170;
					r = v3 * 3;
					g = 0;
					b = 255;
				}

					// clamp
					r = Math.max(0, Math.min(255, r));
					g = Math.max(0, Math.min(255, g));
					b = Math.max(0, Math.min(255, b));

					Color c = new Color(r, g, b);
					PanelCreation.this.labelCouleur.setBackground(c);
					PanelCreation.this.labelCouleur.setOpaque(true);
			}
		});


		// à déf
		ImageIcon iconPole = new ImageIcon("src/IHM/Images/");

		
		ArrayList<String> nomPlateaux = new ArrayList<String>(); // this.ctrl.getNomPlateaux();
		
		// this.panelVisuel
		//this.panelVisuel.add(nomPlateaux.isEmpty() ? new JLabel("Aucun plateau disponible") : new JLabel(new ImageIcon("src/IHM/Images/" + nomPlateaux.get(0) + ".png")));
		
		// panelBouton
		
		btnRefreshDep           .addActionListener(this);
		this.btnValider         .addActionListener(this);
		this.btnAnnuler         .addActionListener(this);
		this.btnEffacer         .addActionListener(this);
		this.btnApercu          .addActionListener(this);
		this.btnValiderDep      .addActionListener(this);
		this.btnValiderTransport.addActionListener(this);
		
		// drag and drop
		this.lblPole.addMouseMotionListener(new GereSouris() );
		
		

		this.jcbPoles.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnValider)
		{
			for ( int lig = 0 ; lig < this.tabLblPlateau.length ; lig++ )
				for ( int col = 0 ; col < this.tabLblPlateau[lig].length ; col++ )
				{
					if ( this.tabLblPlateau[lig][col].getIcon() != null )
						for ( TypePole t : TypePole.values() )
							if ( t.getImage().equals(this.tabLblPlateau[lig][col].getIcon()) )
								this.ctrl.ajouterPole( lig, col, t );
					
					if ( this.tabLblPlateau[lig][col].getBackground() != null )
						this.ctrl.ajouterDep( lig, col, TypeDepartement.valueOf( this.tabLblPlateau[lig][col].getBackground() ) );
				}
			
			this.ctrl.sauvegarder();

			// new FrameMenu( this.ctrl );
			// this.frameCreation.setVisible(false);
		}

		if (e.getSource() == this.btnAnnuler)
		{
			// new FrameMenu( this.ctrl );
		}

		if (e.getSource() == this.btnEffacer)
		{
			this.jcbPoles.setSelectedIndex(0);
			this.jcbDep  .setSelectedIndex(0);

			this.scrollbarRGB.setValue(0);
			
			for ( JLabel[] lig : this.tabLblPlateau )
				for ( JLabel col : lig )
					col.setIcon( null );
			
			this.effacerDepartementTableau();
		}
		
		if ( e.getSource() == btnApercu )
		{
			this.repaint();
		}

		if (e.getSource() == this.btnRefreshDep)
		{
			this.rafraichirSelectionDep();
			this.effacerDepartementTableau();
		}
		
		if ( e.getSource() == this.btnValiderDep )
		{
			this.estDep = ! this.estDep;
			if ( this.estDep )
			{
				this.btnValiderDep.setText("Saisisez les case du département");
				this.estTransport = false;
				this.btnValiderTransport.setText("Valider le transport");
			}
			else
				this.btnValiderDep.setText("Valider le département");
		}
		
		if ( e.getSource() == this.btnValiderTransport )
		{
			this.estTransport = ! this.estTransport;
			if ( this.estTransport )
			{
				this.btnValiderTransport.setText("Saisisez la case du transport");
				this.estDep = false;
				this.btnValiderDep.setText("Valider le département");
			}
			else
				this.btnValiderTransport.setText("Valider le transport");
		}
		
		if (e.getSource() == this.jcbPoles)
		{
			this.lblPole.setIcon(TypePole.values()[this.lstIndexTypePole.get( this.jcbPoles.getSelectedIndex()) ].getImage());
		}
		
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
								case 0, 1, 2 -> lig -- ;
								case 4, 5, 6 -> lig ++ ;
							}
							
							// Colonne :
							switch ( cpt )
							{
								case 0, 6, 7 -> col -- ;
								case 2, 3, 4 -> col ++ ;
							}
						}
						
						if ( lig >= 0 && lig < this.tabLblPlateau        .length &&
							 col >= 0 && col < this.tabLblPlateau[ligLbl].length &&
							 tabLblPlateau[lig][col].getIcon() != null )
						{
						

							int longueurLabel = PanelCreation.this.tabLblPlateau[lig][col].getWidth ();
							int hauteurLabel  = PanelCreation.this.tabLblPlateau[lig][col].getHeight();
						
							int x1 = col    * longueurLabel + longueurLabel/2 + panelVisuel.getX();
							int y1 = lig    * hauteurLabel  + hauteurLabel /2 + panelVisuel.getY();
							
							int x2 = colLbl * longueurLabel + longueurLabel /2 + panelVisuel.getX();
							int y2 = ligLbl * hauteurLabel  + hauteurLabel/2   + panelVisuel.getY();

							System.out.println(  panelVisuel.getX() + " " + panelVisuel.getY());

							g.setColor( Color.BLACK );
							g.drawLine( x1, y1, x2, y2 );
						}
					}
				}
	}
	
	public void init()
	{
		
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		// Nom du plateau
		this.nomPlateau = this.ctrl.getNom();
		
		// Récupération de la taille du plateau
		this.panelVisuel.setLayout(new GridLayout(this.ctrl.getTailleXPlateau(), this.ctrl.getTailleYPlateau()));
		
		// Ajout des pôles sélectionné à la combo box
		this.lstIndexTypePole = this.ctrl.getLstPoleSelect();
		
		for ( int index : this.lstIndexTypePole )
		{
			this.jcbPoles.addItem(TypePole.values()[index]);
		}
		
		
		// Création des labels du plateau
		this.tabLblPlateau = new JLabel[this.ctrl.getTailleXPlateau()][this.ctrl.getTailleYPlateau()];

		for(int lig = 0; lig < this.tabLblPlateau.length; lig++)
		{
			for(int col = 0; col < this.tabLblPlateau[lig].length; col++)
			{
				this.tabLblPlateau[lig][col] = new JLabel("", SwingConstants.CENTER);
				
				this.tabLblPlateau[lig][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				this.tabLblPlateau[lig][col].setOpaque(true);
				
				this.tabLblPlateau[lig][col].setTransferHandler(new TransferHandler("icon"));
			}
			
		}
		
		
		/*--------------------------------*/
		/* Positionnements des composants */
		/*--------------------------------*/
		
		// Ajout des labels du plateau dans le panel Plateau
		for (int lig = 0; lig < this.tabLblPlateau.length; lig++)
			for (int col = 0; col < this.tabLblPlateau[lig].length; col++)
				this.panelVisuel.add( this.tabLblPlateau[lig][col] );
		
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		// Couleur Dep
		for (int lig = 0; lig < this.tabLblPlateau.length; lig++)
		{
			for (int col = 0; col < this.tabLblPlateau[lig].length; col++)
			{
				GereSouris gs = new GereSouris();
				
				this.tabLblPlateau[lig][col].addMouseMotionListener( gs );
				this.tabLblPlateau[lig][col].addMouseListener      ( gs );
			}
			
		}
		
	}
	
	public String getNom() { return this.nomPanel; }
	
	private void effacerDepartementTableau()
	{
		for ( JLabel[] tabLab : this.tabLblPlateau )
			for ( JLabel lab : tabLab )
				lab.setBackground(null);
	}
	
	private void rafraichirSelectionDep()
	{
		this.jcbDep.removeAllItems();
		
		int nbRandom = 0;
		
		for (int i = 0; i < NOMBRE_DEPARTEMENT_COMBOBOX; i++)
		{
			nbRandom = (int) (Math.random() * TypeDepartement.values().length);
			this.jcbDep.addItem( TypeDepartement.values()[nbRandom] );
		}
		
	}
	
	
	private class GereSouris extends MouseAdapter
	{
		// Poles Source
		public void mouseDragged(MouseEvent e)
		{
			JComponent c = (JComponent) ( e.getSource() );
			c.getTransferHandler().exportAsDrag(c, e, TransferHandler.COPY);
		}
		
		public void mouseClicked(MouseEvent e)
		{

			for(int lig=0; lig<tabLblPlateau.length; lig++)
				for(int col=0; col<tabLblPlateau[lig].length; col++)
					if ( e.getSource() == tabLblPlateau[lig][col] )
					{
						if ( estDep )
						{
							int[][] tabVoisin = new int[][] { {0,1}, {1,0}, {0,-1}, {-1, 0} };
							for ( int ligVoisin = 0 ; ligVoisin < tabVoisin.length ; ligVoisin++ )
							{
								Color coul = ( ( ( TypeDepartement ) PanelCreation.this.jcbDep.getSelectedItem() ).getCouleur() );
								if ( ! this.couleurExiste(coul)                                                                ||
								     lig + tabVoisin[ligVoisin][0] >= 0 && lig + tabVoisin[ligVoisin][0] < tabLblPlateau     .length &&
								     col + tabVoisin[ligVoisin][1] >= 0 && col + tabVoisin[ligVoisin][1] < tabLblPlateau[lig].length &&
								     tabLblPlateau[ lig + tabVoisin[ligVoisin][0] ][ col + tabVoisin[ligVoisin][1] ].getBackground().equals( coul )                                )
								{
									tabLblPlateau[lig][col].setBackground( coul );
								}
							}
						}
						
						if ( estTransport )
						{
							if ( tabLblPlateau[lig][col].getIcon() != null && ! borderExiste( PanelCreation.this.labelCouleur.getBackground() ) )
							{
								tabLblPlateau[lig][col].setBorder ( BorderFactory.createLineBorder( PanelCreation.this.labelCouleur.getBackground(), 3 ) );
								PanelCreation.this.lstCouleurTransport.add(PanelCreation.this.labelCouleur.getBackground());
								PanelCreation.this.ctrl.ajouterCaseDepart( lig, col );
							}
						}
					}	
		}
		
		private boolean couleurExiste ( Color c )
		{
			for(int lig=0; lig < PanelCreation.this.tabLblPlateau.length; lig++)
				for(int col=0; col< PanelCreation.this.tabLblPlateau[lig].length; col++)
					if ( c.equals( PanelCreation.this.tabLblPlateau[lig][col].getBackground() ) )
						return true;
			return false;
		}
		
		private boolean borderExiste ( Color c )
		{
			for( Color col : PanelCreation.this.lstCouleurTransport )
				if ( c.equals(col) )
					return true;
			return false;
		}
	}
}
