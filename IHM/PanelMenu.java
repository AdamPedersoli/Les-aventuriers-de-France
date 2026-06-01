package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelMenu extends JPanel implements ActionListener
{
	private JButton buttonSolo;
	private JButton buttonCreation;
	private JComboBox<Plateau> comboBoxPlateaux;

	public PanelMenu()
	{
		this.setLayout(new GridLayout(3, 1));

		this.comboBoxPlateaux = new JComboBox<Plateau>();
		for (Plateau plateau : Plateau.values()) 
		{
			this.comboBoxPlateaux.addItem(plateau);
	    }
		
		this.buttonSolo = new JButton("Solo");
		this.buttonSolo.addActionListener(this);
		
		this.buttonCreation = new JButton("Creation");
		this.buttonCreation.addActionListener(this);
		
		this.add(this.buttonSolo);
		this.add(new JLabel(""));
		this.add(this.comboBoxPlateaux);
		this.add(new JLabel(""));
		this.add(this.buttonCreation);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonSolo) 
		{
			Plateau plateauSelectionne = (Plateau) this.comboBoxPlateaux.getSelectedItem();
			new FrameSolo(plateauSelectionne);
		} 
		else if (e.getSource() == this.buttonCreation) 
		{
			new FrameCreation();
		}
	}
	
}
