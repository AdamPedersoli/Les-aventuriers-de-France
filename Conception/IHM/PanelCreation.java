package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import IHM.PanelConfig;
// import controleur;

public class PanelCreation extends JPanel implements ActionListener
{
	FrameCreation frameCreation;
	// panel Principal
	private JPanel panelConfig;
	private JPanel panelGrille;
	// private Controleur ctrl;

	private JButton buttonAnnuler = new JButton("Annuler");
	private JButton buttonEffacer = new JButton("Effacer");
	private JButton buttonValider = new JButton("Valider");

	private JScrollBar scrollbarRGB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);


	public PanelCreation(FrameCreation frameCreation)
	{
		this.frameCreation = frameCreation;

		this.setLayout(new GridLayout(1, 2));
		this.panelConfig = new JPanel();
		this.panelGrille = new JPanel();
		this.panelConfig.setLayout(new GridLayout(4,1));
		this.panelGrille.setLayout(new GridLayout(3,1));
		
		// sous-panel de panelConfig 
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();

		// sous-panel de panelGrille
		JPanel panelVisual     = new JPanel();
		JPanel panelBouton     = new JPanel();

		JComboBox<String> jcbPoles = new JComboBox<String>(); //this.ctrl.getNomPoles());
		JComboBox<String> jcbDep   = new JComboBox<String>(); //this.ctrl.getNomDeps()); couleur prédéfinie
		JComboBox<String> jcbTrans = new JComboBox<String>(); //this.ctrl.getNomTransports()); remplacer String par MoyenTransport

		jcbPoles.addItem("Poles");
		jcbDep.addItem("Departement");
		jcbTrans.addItem("transport");

		JButton buttonRefreshDep   = new JButton(new ImageIcon("/IHM/Images/refresh.png"));
		
		JLabel labelCouleur        = new JLabel("#000000");

		// Couleur en temps réel à partir de la scrollbar (RGB : on balaie le rouge)
		labelCouleur.setBackground(Color.BLACK);

		scrollbarRGB.setValue(0);
		scrollbarRGB.setMinimum(0);
		scrollbarRGB.setMaximum(255);

		// 0..84   : r=255, g décroît, b=0
		// 85..169 : g=0, b croît, r décroît
		// 170..255: b=255, r croît, g=0
		scrollbarRGB.addAdjustmentListener(new AdjustmentListener() 
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
					//labelCouleur.setText("#" + String.format("%02X%02X%02X", r, g, b));
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
		panelDep.add(labelCouleur);

		// panelTransport
		panelTransport.add(new JLabel("Transports : "));
		panelTransport.add(jcbTrans);
		

		this.panelConfig.add(panelPoles);
		this.panelConfig.add(panelDep);
		this.panelConfig.add(panelTransport);
		this.panelConfig.add(scrollbarRGB);

		ArrayList<String> nomPlateaux = new ArrayList<String>(); // this.ctrl.getNomPlateaux();

		// panelVisual
		panelVisual.add(nomPlateaux.isEmpty() ? new JLabel("Aucun plateau disponible") : new JLabel(new ImageIcon("src/IHM/Images/" + nomPlateaux.get(0) + ".png")));

		// panelBouton
		
		this.buttonValider.addActionListener(this);
		this.buttonAnnuler.addActionListener(this);
		this.buttonEffacer.addActionListener(this);

		panelBouton.add(this.buttonAnnuler);
		panelBouton.add(this.buttonEffacer);
		panelBouton.add(this.buttonValider);

		// Le nom du plateau vient de la configuration
		this.panelGrille.add(new JLabel("nomPlateau"));
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
		// à implémenter
	}
}
