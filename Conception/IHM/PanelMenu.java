
package Conception.IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Conception.ControleurConception;

public class PanelMenu extends JPanel implements ActionListener
{
	private FrameMenu frameMenu;
	private JButton buttonCreation;
	private JButton buttonModification;
	private JComboBox<String> jcbPlateaux;
	private ControleurConception ctrl;

	public PanelMenu(FrameMenu frameMenu, ControleurConception ctrl )
	{
		this.frameMenu = frameMenu;
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(5, 1));

		this.jcbPlateaux = new JComboBox<String>();

		/*for (Plateau plateau : Plateau.values()) 
		{
			this.jcbPlateaux.addItem(plateau.toString());
	    }*/
		
		this.buttonCreation = new JButton("Creation");
		this.buttonCreation.addActionListener(this);
		
		this.buttonModification = new JButton("Modification");
		this.buttonModification.addActionListener(this);

		this.jcbPlateaux.addItem(null);
		this.jcbPlateaux.addItem("Test");
		 // this.ctrl.getNomPlateaux());
		this.jcbPlateaux.addActionListener(this);
		
		JLabel labelInfo = new JLabel("Choisir un plateau le modifier", SwingConstants.CENTER);
		JLabel labelVide = new JLabel("");
		this.setOpaque(false);
		labelInfo.setOpaque(true);
		labelInfo.setForeground(Color.WHITE);
		this.add(labelInfo).setBackground(new Color(0,0, 255));
		this.add(this.jcbPlateaux);
		labelVide.setOpaque(true);
		this.add(labelVide).setBackground(new Color(255, 0, 0));
		this.add(this.buttonCreation);
		this.add(this.buttonModification);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String nomPlateau = (String) this.jcbPlateaux.getSelectedItem();
		if (nomPlateau != null) 
		{
			/* 
			String plateauSelectionne = (String) this.jcbPlateaux.getSelectedItem();
			// FrameSolo frameSolo = new FrameSolo(plateauSelectionne);
			// frameSolo.setVisible(true);
			FrameMenu frameMenu = (FrameMenu) SwingUtilities.getWindowAncestor(this);
			frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(false);
			this.setEnabled(false);*/

			if (e.getSource() == this.buttonModification) 
			{
				new FrameModification( this.ctrl );
			}
		}

		if (e.getSource() == this.buttonCreation) 
		{
			(new FrameConfig( this.ctrl )).setVisible(true);
		}
	}
}
