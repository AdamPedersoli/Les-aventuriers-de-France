package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import Controleur;

public class PanelMenu extends JPanel implements ActionListener
{
	private JButton buttonSolo;
	private JButton buttonCreation;
	private JButton buttonModification;
	private JComboBox<String> jcbPlateaux;
	// private Controleur ctrl;

	public PanelMenu()
	{
		// this.ctrl = ctrl;
		this.setLayout(new GridLayout(6, 1));

		this.jcbPlateaux = new JComboBox<String>();

		/*for (Plateau plateau : Plateau.values()) 
		{
			this.jcbPlateaux.addItem(plateau.toString());
	    }*/
		
		this.buttonSolo = new JButton("Solo");
		this.buttonSolo.addActionListener(this);
		
		this.buttonCreation = new JButton("Creation");
		this.buttonCreation.addActionListener(this);
		
		this.buttonModification = new JButton("Modification");
		this.buttonModification.addActionListener(this);
		
		this.add(this.buttonSolo);
		this.add(new JLabel("Choisir un plateau pour jouer ou le modifier", SwingConstants.CENTER));
		this.add(this.jcbPlateaux);
		this.add(new JLabel(""));
		this.add(this.buttonCreation);
		this.add(this.buttonModification);
	}

	public void actionPerformed(ActionEvent e) 
	{
		/*if (e.getSource() == this.buttonSolo) 
		{
			String plateauSelectionne = (String) this.jcbPlateaux.getSelectedItem();
			new FrameSolo(Plateau.valueOf(plateauSelectionne));
		}*/
		if (e.getSource() == this.buttonCreation) 
		{
			new FrameConfig();
			this.setVisible(false);
			this.setEnabled(false);
		}
		if (e.getSource() == this.buttonModification) 
		{
			new FrameModification();
			this.setVisible(false);
			this.setEnabled(false);
		}
	}
	
}
