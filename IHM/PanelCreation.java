package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controleur;

public class PanelCreation extends JPanel
{
	// panel Principal
	private JPanel panelConfig;
	private JPanel panelGrille;
	private Controleur ctrl;

	public PanelCreation()
	{
		this.setLayout(new GridLayout(1, 2));
		this.panelConfig = new JPanel();
		this.panelGrille = new JPanel();
		this.panelConfig.setLayout(new GridLayout(3,1));
		this.panelGrille.setLayout(new GridLayout(3,1));
		
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();
		JPanel panelNomPlateau = new JPanel();
		JPanel panelVisual     = new JPanel();
		JPanel panelBouton     = new JPanel();

		JComboBox jcbPoles = new JComboBox(this.ctrl.getNomPoles());
		JComboBox jcbDep   = new JComboBox(this.ctrl.getNomDeps());
		JComboBox jcbTrans = new JComboBox(this.ctrl.getNomTransports());

		panelPoles.add(jcbPoles);
		panelDep.add(jcbDep);
		panelTransport.add(jcbTrans);

		this.panelConfig.add(panelPoles);
		this.panelConfig.add(panelDep);
		this.panelConfig.add(panelTransport);

		JTextField tfNomPlateau = new JTextField("Nom du plateau", 20);
		JButton buttonVisualiser = new JButton("Visualiser");

		panelNomPlateau.add(tfNomPlateau);
		panelVisual.add(buttonVisualiser);

		this.panelGrille.add(panelNomPlateau);
		this.panelGrille.add(panelVisual);
		this.panelGrille.add(panelBouton);

		this.add(this.panelConfig);
		this.add(this.panelGrille);
	}
}
