package Conception.IHM;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import IHM.PanelConfig;
import TP.s2.TD5.Exo4.Departement;
import TP.s2.TD6.TD6.chess.metier.Plateau;
import Conception.Metier.TypeDepartement;
// import controleur;

public class PanelCreation extends JPanel implements ActionListener
{
	
	private Plateau plateau;
	FrameCreation frameCreation;
	// panel Principal
	private JPanel panelConfig;
	private JPanel panelGrille;
	// private Controleur ctrl;

	private JLabel[][] tabLabel;
	private JButton buttonAnnuler = new JButton("Annuler");
	private JButton buttonEffacer = new JButton("Effacer");
	private JButton buttonValider = new JButton("Valider");

	TypeDepartement dep;
	private TypeDepartement[] lstDep;
	TypePole type = TypePole.values()[jcbPoles.getSelectedIndex()];
	ImageIcon icon = type.getImage();
	private	JComboBox<TypePole> jcbPoles = new JComboBox<TypePole>(); //this.ctrl.getNomPoles());
	private	JComboBox<String> jcbDep   = new JComboBox<String>(); //this.ctrl.getNomDeps()); couleur prédéfinie
	private	JComboBox<String> jcbTrans = new JComboBox<String>(); //this.ctrl.getNomTransports()); remplacer String par MoyenTransport

	private JScrollBar scrollbarRGB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);

	private JButton buttonRefreshDep   = new JButton(new ImageIcon("/IHM/Images/refresh.png"));

	public PanelCreation(FrameCreation frameCreation)
	{
		
		this.plateau = ctrl.getPlateau();
		this.frameCreation = frameCreation;

		this.setLayout(new GridLayout(1, 2));
		this.panelConfig = new JPanel();
		this.panelGrille = new JPanel();
		this.panelConfig.setLayout(new GridLayout(5,1));
		this.panelGrille.setLayout(new GridLayout(3,1));

		this.lstDep = TypeDepartement.values();
		
		// sous-panel de panelConfig 
		JPanel panelPoles        = new JPanel();
		JPanel panelDep          = new JPanel();
		JPanel panelTransport    = new JPanel();
		JPanel panelDepScrollbar = new JPanel();

		// sous-panel de panelGrille
		JPanel panelVisual     = new JPanel();
		JPanel panelBouton     = new JPanel();

		JLabel lblPole = new JLabel(type.getImage());

		// drag and drop
		lblPole.setTransferHandler(new TransferHandler("icon"));
		lblPole.addMouseMotionListener(new MouseMotionAdapter()
			{
				public void mouseDragged(MouseEvent e)
				{
					JComponent c = (JComponent)e.getSource();

					c.getTransferHandler().exportAsDrag(c, e, TransferHandler.COPY);
				}
			}
		);
		label.setTransferHandler( new TransferHandler("icon"));

		// maj metier
		plateau.getCase(lig,col).setPole(new Pole(type));

		label.setBackground(dep.getCouleur());
		Departement d = plateau.getDep(indice);
		d.ajouterCase(plateau.getCase(lig,col));

		this.panelVisual.setLayout(new GridLayout(plateau.getTailleX(), plateau.getTailleY()));

		tabLabel = new JLabel[plateau.getTailleX()][plateau.getTailleY()];

		for(int lig=0; lig<plateau.getTailleX(); lig++)
		{
			for(int col=0; col<plateau.getTailleY(); col++)
			{
				JLabel lbl = new JLabel();

				lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				lbl.setOpaque(true);

				tabLabel[lig][col] = lbl;

				panelVisual.add(lbl);
			}
		}

		////
		for (int i = 0; i < this.lstDep.length; i++)
		{
			int nbRandom = 0;
			nbRandom = (int) (Math.random() * 100 + 1);
			this.jcbPoles .addItem(this.lstDep.values()[nbRandom].getNom());
		}
		
		this.jcbPoles .addItem("test");
		this.jcbDep   .addItem("Departement");
		this.jcbDep   .addItem("test");
		this.jcbDep   .addItem("test2");
		this.jcbDep   .addItem("test3");
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
		panelPoles.add(new JLabel(iconPole));

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
		panelVisual.add(nomPlateaux.isEmpty() ? new JLabel("Aucun plateau disponible") : new JLabel(new ImageIcon("src/IHM/Images/" + nomPlateaux.get(0) + ".png")));

		// panelBouton
		
		buttonRefreshDep  .addActionListener(this);
		this.buttonValider.addActionListener(this);
		this.buttonAnnuler.addActionListener(this);
		this.buttonEffacer.addActionListener(this);

		panelBouton.add(this.buttonAnnuler);
		panelBouton.add(this.buttonEffacer);
		panelBouton.add(this.buttonValider);

		// Le nom du plateau vient de la configuration
		this.panelGrille.add(new JLabel("nomPlateau", SwingConstants.CENTER));
		this.panelGrille.add(panelVisual);
		this.panelGrille.add(panelBouton);

		this.add(this.panelConfig);
		this.add(this.panelGrille);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonValider)
		{
			this.frameCreation.getFrameMenu().setVisible(true);
			this.frameCreation.setVisible(false);
		}

		if (e.getSource() == this.buttonAnnuler)
		{
			this.frameCreation.getFrameMenu().setVisible(true);
			this.frameCreation.setVisible(false);
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
				nbRandom = (int) (Math.random() * 102 + 1);
				//this.jcbDep.addItem(/* un dep aléatoire de la liste grâce à l'indice */)
			}
		}
	}
}
