package Conception.IHM;
import javax.swing.*;

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
	private ControleurConception ctrl;

	private JLabel[][] tabLabel;
	private JLabel lblPole;
	
	private JButton buttonAnnuler = new JButton("Annuler");
	private JButton buttonEffacer = new JButton("Effacer");
	private JButton buttonValider = new JButton("Valider");

	TypeDepartement dep;
	private TypeDepartement[] tabDep;


	private	JComboBox<TypePole> jcbPoles = new JComboBox<TypePole>(); //this.ctrl.getNomPoles());
	private	JComboBox<TypeDepartement> jcbDep   = new JComboBox<TypeDepartement>(); //this.ctrl.getNomDeps()); couleur prédéfinie
	private	JComboBox<String> jcbTrans = new JComboBox<String>(); //this.ctrl.getNomTransports()); remplacer String par MoyenTransport

	private TypePole type;

	private JScrollBar scrollbarRGB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);

	private JButton buttonRefreshDep   = new JButton(new ImageIcon("/IHM/Images/refresh.png"));

	public PanelCreation(FrameCreation frameCreation, ControleurConception ctrl)
	{
		this.ctrl = ctrl;
		this.plateau = this.ctrl.getPlateau();
		this.frameCreation = frameCreation;

		this.setLayout(new GridLayout(1, 2));
		this.panelConfig = new JPanel();
		this.panelGrille = new JPanel();
		this.panelConfig.setLayout(new GridLayout(5,1));
		this.panelGrille.setLayout(new GridLayout(3,1));

		this.tabDep = TypeDepartement.values();
		
		// sous-panel de panelConfig 
		JPanel panelPoles        = new JPanel();
		JPanel panelDep          = new JPanel();
		JPanel panelTransport    = new JPanel();
		JPanel panelDepScrollbar = new JPanel();

		// sous-panel de panelGrille
		JPanel panelVisual     = new JPanel();
		JPanel panelBouton     = new JPanel();

		

		panelVisual.setLayout(new GridLayout(this.ctrl.getTailleXPlateau(), this.ctrl.getTailleYPlateau()));

		this.tabLabel = new JLabel[this.ctrl.getTailleXPlateau()][this.ctrl.getTailleYPlateau()];

		for(int lig=0; lig<this.tabLabel.length; lig++)
		{
			for(int col=0; col<this.tabLabel.length; col++)
			{
				JLabel lbl = new JLabel();

				lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				lbl.setOpaque(true);

				lbl.setTransferHandler(new TransferHandler("icon"));

				tabLabel[lig][col] = lbl;

				panelVisual.add(lbl);
			}
		}

		for (int i = 0; i < 5; i++)
		{
			int nbRandom = 0;
			nbRandom = (int) (Math.random() * TypeDepartement.values().length);
			this.jcbDep.addItem(this.tabDep[nbRandom]);
		}
		
		for ( TypePole typePole : TypePole.values() )
			this.jcbPoles .addItem(typePole);

		// Image Pole pas plateau
		this.type = TypePole.values()[0];
		this.jcbPoles.setSelectedIndex(0);
		this.lblPole = new JLabel(TypePole.values()[0].getImage());
		this.lblPole.setTransferHandler(new TransferHandler("icon"));
		
		this.jcbTrans .addItem("transport");
		
		JLabel labelCouleur        = new JLabel("               ");

		// Couleur en temps réel à partir de la scrollbar (RGB : on balaie le rouge)
		labelCouleur.setBackground(Color.BLACK);

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
					labelCouleur.setBackground(c);
					labelCouleur.setOpaque(true);
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
		
		//panelDepScrollbar
		panelDepScrollbar.setLayout(new GridLayout(2,2));
		panelDepScrollbar.add(scrollbarRGB);
		panelDepScrollbar.add(labelCouleur);
		panelDepScrollbar.add(new JLabel(""));

		// panelTransport
		panelTransport.add(new JLabel("Transports : "));
		panelTransport.add(jcbTrans);
		
		this.panelConfig.add(panelPoles);
		this.panelConfig.add(new JLabel("Le bouton flèche donne de nouveau départements aléatoires", SwingConstants.CENTER));
		this.panelConfig.add(panelDep);
		this.panelConfig.add(panelDepScrollbar);
		this.panelConfig.add(panelTransport);

		ArrayList<String> nomPlateaux = new ArrayList<String>(); // this.ctrl.getNomPlateaux();

		// panelVisual
		//panelVisual.add(nomPlateaux.isEmpty() ? new JLabel("Aucun plateau disponible") : new JLabel(new ImageIcon("src/IHM/Images/" + nomPlateaux.get(0) + ".png")));

		// panelBouton
		
		buttonRefreshDep  .addActionListener(this);
		this.buttonValider.addActionListener(this);
		this.buttonAnnuler.addActionListener(this);
		this.buttonEffacer.addActionListener(this);
		
		// drag and drop
		this.lblPole.addMouseMotionListener(new GereSouris() );
		
		// Couleur Dep
		for ( JLabel[] lblLig : this.tabLabel )
			for ( JLabel lblCol : lblLig )
				lblCol.addMouseMotionListener(new GereSouris() );

		panelBouton.add(this.buttonAnnuler);
		panelBouton.add(this.buttonEffacer);
		panelBouton.add(this.buttonValider);

		// Le nom du plateau vient de la configuration
		this.panelGrille.add(new JLabel("nomPlateau", SwingConstants.CENTER));
		this.panelGrille.add(panelVisual);
		this.panelGrille.add(panelBouton);

		this.jcbPoles.addActionListener(this);

		this.add(this.panelConfig);
		this.add(this.panelGrille);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonValider)
		{
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
			this.jcbTrans.setSelectedIndex(0);

			this.scrollbarRGB.setValue(0);
		}

		if (e.getSource() == this.buttonRefreshDep)
		{
			this.jcbDep.removeAllItems();
			int nbRandom = 0;
			for (int i = 0; i < 4; i++)
			{
				nbRandom = (int) (Math.random() * TypeDepartement.values().length);
				this.jcbDep.addItem(this.tabDep[nbRandom]);
			}
		}
		
		if ( e.getSource() == this.jcbPoles )
		{
			ImageIcon icon = TypePole.PORT.getImage();
			
			this.lblPole.setIcon( icon );
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
			for(int lig=0; lig<tabLabel.length; lig++)
				for(int col=0; col<tabLabel[lig].length; col++)
					if ( e.getSource() == tabLabel[lig][col] )
					{
						tabLabel[lig][col].setBackground( ( ( TypeDepartement ) PanelCreation.this.jcbDep.getSelectedItem() ).getCouleur() );
						ctrl.setCaseAtDep( ( ( TypeDepartement ) PanelCreation.this.jcbDep.getSelectedItem() ).ordinal(), lig, col );
					}
		}
	}
}
