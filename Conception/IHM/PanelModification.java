package Conception.IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Conception.ControleurConception;

public class PanelModification extends JPanel implements ActionListener
{
	private FrameModification frameModification;
	// panel Principal
	private JPanel panelConfig;
	private JPanel panelGrille;
	private ControleurConception ctrl;

	private JButton buttonAnnuler = new JButton("Annuler");
	private JButton buttonEffacer = new JButton("Effacer");
	private JButton buttonValider = new JButton("Valider");

	private JScrollBar scrollbarRGB = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 255);


	public PanelModification(ControleurConception ctrl)
	{
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
		
		JLabel labelCouleur        = new JLabel("");

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
			new FrameMenu( this.ctrl );
		}
		// à implémenter
	}
}
