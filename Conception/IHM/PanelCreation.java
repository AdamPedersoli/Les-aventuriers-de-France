package Conception.IHM;
import javax.swing.*;

import java.io.File;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Conception.Metier.Departement;
import Conception.Metier.Plateau;
import Conception.Metier.TypeDepartement;
import Conception.Metier.TypePole;
import Conception.ControleurConception;

public class PanelCreation extends JPanel implements ActionListener
{
	
	private Plateau plateau;
	private FrameCreation frameCreation;
	private JPanel panelConfig;
	private JPanel panelGrille;
	private JPanel panelVisual;
	private ControleurConception ctrl;

	private JLabel[][] tabLabel;
	private JLabel lblPole;
	
	private JButton buttonAnnuler = new JButton("Annuler");
	private JButton buttonEffacer = new JButton("Effacer");
	private JButton buttonValider = new JButton("Valider");
	private JButton btnApercu     = new JButton("Aperçu" );

	TypeDepartement dep;
	private TypeDepartement[] tabDep;


	private	JComboBox<TypePole> jcbPoles = new JComboBox<TypePole>(); //this.ctrl.getNomPoles());
	private	JComboBox<TypeDepartement> jcbDep   = new JComboBox<TypeDepartement>(); //this.ctrl.getNomDeps()); couleur prédéfinie

	private TypePole type;

	private JScrollBar scrollbarRGB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);

	private JButton buttonRefreshDep   = new JButton( "Rafraichir" );
	private JButton btnValiderDep;
	private JButton btnValiderTransport;
	
	private ArrayList<Integer> lstIndexTypePole;
	
	private JLabel labelCouleur;
	
	private boolean estDep;
	private boolean estTransport;
	private ArrayList<Color> lstCouleurTransport;

	public PanelCreation(FrameCreation frameCreation, ControleurConception ctrl, ArrayList<Integer> lstIndexTypePole)
	{
		this.ctrl = ctrl;
		this.plateau = this.ctrl.getPlateau();
		this.lstIndexTypePole = lstIndexTypePole;
		this.frameCreation = frameCreation;
		
		this.lstCouleurTransport = new ArrayList<Color>();

		this.setLayout(new GridLayout(1, 2, 50,50));
		this.panelConfig = new JPanel();
		this.panelGrille = new JPanel();
		this.panelConfig.setLayout(new GridLayout(5,1));
		this.panelGrille.setLayout(new GridLayout(3,1));

		this.tabDep = TypeDepartement.values();
		this.btnValiderDep       = new JButton ( "Valider le département" );
		this.btnValiderTransport = new JButton ( "Valider le transport"   );
		
		// sous-panel de panelConfig 
		JPanel panelPoles        = new JPanel();
		JPanel panelTabDep       = new JPanel( new GridLayout(2,1) );
		JPanel panelDep          = new JPanel();
		JPanel panelTabTransport = new JPanel( new GridLayout(2,1) ); 
		JPanel panelTransport    = new JPanel();
		JPanel panelDepScrollbar = new JPanel();

		// sous-panel de panelGrille
		this.panelVisual     = new JPanel();
		JPanel panelBouton     = new JPanel();

		

		this.panelVisual.setLayout(new GridLayout(this.ctrl.getTailleXPlateau(), this.ctrl.getTailleYPlateau()));

		this.tabLabel = new JLabel[this.ctrl.getTailleXPlateau()][this.ctrl.getTailleYPlateau()];

		for(int lig=0; lig<this.tabLabel.length; lig++)
		{
			for(int col=0; col<this.tabLabel[lig].length; col++)
			{
				JLabel lbl = new JLabel("", SwingConstants.CENTER);

				lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				lbl.setOpaque(true);

				lbl.setTransferHandler(new TransferHandler("icon"));

				tabLabel[lig][col] = lbl;

				this.panelVisual.add(lbl);
			}
		}

		for (int i = 0; i < 6; i++)
		{
			int nbRandom = 0;
			nbRandom = (int) (Math.random() * TypeDepartement.values().length);
			this.jcbDep.addItem(this.tabDep[nbRandom]);
		}
		
		for ( int index : lstIndexTypePole )
		{
			this.jcbPoles.addItem(type.values()[index]);
		}
		
		// Image Pole pas plateau
		this.type = TypePole.values()[0];
		this.jcbPoles.setSelectedIndex(0);
		this.lblPole = new JLabel(TypePole.values()[lstIndexTypePole.get(0)].getImage());
		this.lblPole.setTransferHandler(new TransferHandler("icon"));
		
		this.labelCouleur        = new JLabel();

		// Couleur en temps réel à partir de la scrollbar (RGB : on balaie le rouge)
		this.labelCouleur.setBackground(Color.BLACK);

		this.scrollbarRGB.setValue(0);
		this.scrollbarRGB.setMinimum(0);
		this.scrollbarRGB.setMaximum(255);

		// 0..84   : r=255, g décroît, b=0
		// 85..169 : g=0, b croît, r décroît
		// 170..255: b=255, r croît, g=0
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

		// panelPoles
		panelPoles.add(new JLabel("Pôles : "));
		panelPoles.add(jcbPoles);
		panelPoles.add(lblPole);

		// panelDep
		panelDep.add(new JLabel("Départements : "));
		panelDep.add(jcbDep);
		panelDep.add(buttonRefreshDep);
		
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
		
		this.panelConfig.add(panelPoles);
		this.panelConfig.add(panelTabDep);
		this.panelConfig.add(panelTabTransport);
		this.panelConfig.add(panelDepScrollbar);

		ArrayList<String> nomPlateaux = new ArrayList<String>(); // this.ctrl.getNomPlateaux();

		// this.panelVisual
		//this.panelVisual.add(nomPlateaux.isEmpty() ? new JLabel("Aucun plateau disponible") : new JLabel(new ImageIcon("src/IHM/Images/" + nomPlateaux.get(0) + ".png")));

		// panelBouton
		
		buttonRefreshDep  .addActionListener(this);
		this.buttonValider.addActionListener(this);
		this.buttonAnnuler.addActionListener(this);
		this.buttonEffacer.addActionListener(this);
		this.btnApercu    .addActionListener(this);
		this.btnValiderDep.addActionListener(this);
		this.btnValiderTransport.addActionListener(this);
		
		// drag and drop
		this.lblPole.addMouseMotionListener(new GereSouris() );
		
		// Couleur Dep
		for ( JLabel[] lblLig : this.tabLabel )
			for ( JLabel lblCol : lblLig )
			{
				GereSouris gs = new GereSouris();
				
				lblCol.addMouseMotionListener( gs );
				lblCol.addMouseListener      ( gs );
			}

		panelBouton.add(this.buttonAnnuler);
		panelBouton.add(this.buttonEffacer);
		panelBouton.add(this.btnApercu    );
		panelBouton.add(this.buttonValider);

		// Le nom du plateau vient de la configuration
		this.panelGrille.add(new JLabel(ctrl.getNom(), SwingConstants.CENTER));
		this.panelGrille.add(this.panelVisual);
		this.panelGrille.add(panelBouton);

		this.jcbPoles.addActionListener(this);

		this.add(this.panelConfig);
		this.add(this.panelGrille);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonValider)
		{
			for ( int lig = 0 ; lig < this.tabLabel.length ; lig++ )
				for ( int col = 0 ; col < this.tabLabel[lig].length ; col++ )
				{
					if ( this.tabLabel[lig][col].getIcon() != null )
						for ( TypePole t : TypePole.values() )
							if ( t.getImage().equals(this.tabLabel[lig][col].getIcon()) )
								this.ctrl.ajouterPole( lig, col, t );
					
					if ( this.tabLabel[lig][col].getBackground() != null )
						this.ctrl.ajouterDep( lig, col, TypeDepartement.valueOf( this.tabLabel[lig][col].getBackground() ) );

					if ( this.tabLabel[lig][col].getBorder() != null )
						this.ctrl.ajouterCaseDepart( lig, col );
				}
			
			this.ctrl.sauvegarder();

			new FrameMenu( this.ctrl );
			this.frameCreation.setVisible(false);
		}

		if (e.getSource() == this.buttonAnnuler)
		{
			new FrameMenu( this.ctrl );
		}

		if (e.getSource() == this.buttonEffacer)
		{
			this.jcbPoles.setSelectedIndex(0);
			this.jcbDep  .setSelectedIndex(0);

			this.scrollbarRGB.setValue(0);
			
			for ( JLabel[] lig : this.tabLabel )
				for ( JLabel col : lig )
					col.setIcon( null );
			
			this.effacerDepartementTableau();
		}
		
		if ( e.getSource() == btnApercu )
		{
			this.repaint();
		}

		if (e.getSource() == this.buttonRefreshDep)
		{
			this.jcbDep.removeAllItems();
			int nbRandom = 0;
			for (int i = 0; i < 5; i++)
			{
				nbRandom = (int) (Math.random() * TypeDepartement.values().length);
				this.jcbDep.addItem(this.tabDep[nbRandom]);
			}
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
		for ( int ligLbl = 0 ; ligLbl < this.tabLabel.length ; ligLbl++ )
			for ( int colLbl = 0 ; colLbl < this.tabLabel[ligLbl].length ; colLbl++ )
				if ( this.tabLabel[ligLbl][colLbl].getIcon() != null )
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
						
						
						while ( lig >= 0 && lig < this.tabLabel        .length &&
							    col >= 0 && col < this.tabLabel[ligLbl].length &&
							    tabLabel[lig][col].getIcon() == null              )
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
						
						if ( lig >= 0 && lig < this.tabLabel        .length &&
							 col >= 0 && col < this.tabLabel[ligLbl].length &&
							 tabLabel[lig][col].getIcon() != null )
						{
						

							int longueurLabel = PanelCreation.this.tabLabel[lig][col].getWidth ();
							int hauteurLabel  = PanelCreation.this.tabLabel[lig][col].getHeight();
						
							int x1 = col    * longueurLabel + longueurLabel/2 + panelVisual.getX();
							int y1 = lig    * hauteurLabel  + hauteurLabel /2 + panelVisual.getY();
							
							int x2 = colLbl * longueurLabel + longueurLabel /2 + panelVisual.getX();
							int y2 = ligLbl * hauteurLabel  + hauteurLabel/2   + panelVisual.getY();

							System.out.println(  panelVisual.getX() + " " + panelVisual.getY());

							g.setColor( Color.BLACK );
							g.drawLine( x1, y1, x2, y2 );
						}
					}
				}
	}
	
	private void effacerDepartementTableau()
	{
		for ( JLabel[] tabLab : this.tabLabel )
			for ( JLabel lab : tabLab )
				lab.setBackground(null);
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

			for(int lig=0; lig<tabLabel.length; lig++)
				for(int col=0; col<tabLabel[lig].length; col++)
					if ( e.getSource() == tabLabel[lig][col] )
					{
						if ( estDep )
						{
							int[][] tabVoisin = new int[][] { {0,1}, {1,0}, {0,-1}, {-1, 0} };
							for ( int ligVoisin = 0 ; ligVoisin < tabVoisin.length ; ligVoisin++ )
							{
								Color coul = ( ( ( TypeDepartement ) PanelCreation.this.jcbDep.getSelectedItem() ).getCouleur() );
								if ( ! this.couleurExiste(coul)                                                                ||
								     lig + tabVoisin[ligVoisin][0] >= 0 && lig + tabVoisin[ligVoisin][0] < tabLabel     .length &&
								     col + tabVoisin[ligVoisin][1] >= 0 && col + tabVoisin[ligVoisin][1] < tabLabel[lig].length &&
								     tabLabel[ lig + tabVoisin[ligVoisin][0] ][ col + tabVoisin[ligVoisin][1] ].getBackground().equals( coul )                                )
								{
									tabLabel[lig][col].setBackground( coul );
								}
							}
						}
						
						if ( estTransport )
						{
							if ( tabLabel[lig][col].getIcon() != null && ! borderExiste( PanelCreation.this.labelCouleur.getBackground() ) )
							{
								tabLabel[lig][col].setBorder ( BorderFactory.createLineBorder( PanelCreation.this.labelCouleur.getBackground(), 3 ) );
								PanelCreation.this.lstCouleurTransport.add(PanelCreation.this.labelCouleur.getBackground());
							}
						}
					}	
		}
		
		private boolean couleurExiste ( Color c )
		{
			for(int lig=0; lig < PanelCreation.this.tabLabel.length; lig++)
				for(int col=0; col< PanelCreation.this.tabLabel[lig].length; col++)
					if ( c.equals( PanelCreation.this.tabLabel[lig][col].getBackground() ) )
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
