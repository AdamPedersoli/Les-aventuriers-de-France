package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import controleur;

public class PanelModification extends JPanel
{
	// panel Principal
	private JPanel panelConfig;
	private JPanel panelGrille;
	private Controleur ctrl;

	public PanelModification()
	{
		this.setLayout(new GridLayout(1, 2));
		this.panelConfig = new JPanel();
		this.panelGrille = new JPanel();
		this.panelConfig.setLayout(new GridLayout(3,1));
		this.panelGrille.setLayout(new GridLayout(3,1));
		
		// sous-panel de panelConfig 
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();

		// sous-panel de panelGrille
		JPanel panelNomPlateau = new JPanel();
		JPanel panelVisual     = new JPanel();
		JPanel panelBouton     = new JPanel();

		JComboBox jcbPoles = new JComboBox(this.ctrl.getNomPoles());
		JComboBox jcbDep   = new JComboBox(this.ctrl.getNomDeps());
		JComboBox jcbTrans = new JComboBox(this.ctrl.getNomTransports());

		JButton buttonRefreshDep   = new JButton(new ImageIcon("/IHM/Images/refresh.png"));
		JButton buttonRefreshTrans = new JButton(new ImageIcon("/IHM/Images/refresh.png"));
		JLabel labelCouleur        = new JLabel("");

		// à déf
		ImageIcon iconPole = new ImageIcon("src/IHM/Images/");

		// panelPoles
		panelPoles.add(jcbPoles);
		panelPoles.add(new JLabel(iconPole));

		// panelDep
		panelDep.add(jcbDep);
		panelDep.add(buttonRefreshDep);
		panelDep.add(labelCouleur);

		// panelTransport
		panelTransport.add(jcbTrans);
		panelTransport.add(buttonRefreshTrans);

		this.panelConfig.add(panelPoles);
		this.panelConfig.add(panelDep);
		this.panelConfig.add(panelTransport);

		JTextField tfNomPlateau = new JTextField("Nom du plateau", 20);

		ArrayList<String> nomPlateaux = this.ctrl.getNomPlateaux();

		// panelNomPlateau
		panelNomPlateau.add(tfNomPlateau);

		// panelVisual
		panelVisual.add(/* à définir*/ );

		// panelBouton
		JButton buttonAnnuler = new JButton("Annuler");
		JButton buttonEffacer = new JButton("Effacer");
		JButton buttonValider = new JButton("Valider");

		panelBouton.add(buttonAnnuler);
		panelBouton.add(buttonEffacer);
		panelBouton.add(buttonValider);

		this.panelGrille.add(panelNomPlateau);
		this.panelGrille.add(panelVisual);
		this.panelGrille.add(panelBouton);

		this.add(this.panelConfig);
		this.add(this.panelGrille);
	}
}
