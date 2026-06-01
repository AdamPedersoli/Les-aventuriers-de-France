package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controleur;

public class PanelMenu extends JPanel implements ActionListener
{
	private JButton buttonSolo;
	private JButton buttonCreation;
	private JComboBox jcbPlateaux;
	private Controleur ctrl;

	public PanelMenu()
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(5, 1));

		this.jcbPlateaux = new JComboBox(this.ctrl.getNomPlateaux());

		this.jcbPlateaux = new JComboBox<String>();
		for (Plateau plateau : Plateau.values()) 
		{
			this.jcbPlateaux.addItem(plateau.toString());
	    }
		
		this.buttonSolo = new JButton("Solo");
		this.buttonSolo.addActionListener(this);
		
		this.buttonCreation = new JButton("Creation");
		this.buttonCreation.addActionListener(this);
		
		this.add(this.buttonSolo);
		this.add(new JLabel(""));
		this.add(this.jcbPlateaux);
		this.add(new JLabel(""));
		this.add(this.buttonCreation);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonSolo) 
		{
			String plateauSelectionne = (String) this.jcbPlateaux.getSelectedItem();
			new FrameSolo(Plateau.valueOf(plateauSelectionne));
		} 
		else if (e.getSource() == this.buttonCreation) 
		{
			new FrameCreation();
		}
	}
	
}
