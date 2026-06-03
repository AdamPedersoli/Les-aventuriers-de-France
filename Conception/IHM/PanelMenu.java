<<<<<<< HEAD:Conception/IHM/PanelMenu.java
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
=======
package IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import Controleur;

public class PanelMenu extends JPanel implements ActionListener
{
	private FrameMenu frameMenu;
	private JButton buttonSolo;
	private JButton buttonCreation;
	private JButton buttonModification;
	private JComboBox<String> jcbPlateaux;
	// private Controleur ctrl;

	public PanelMenu(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
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

		this.jcbPlateaux.addItem(null);
		this.jcbPlateaux.addItem("Test");
		 // this.ctrl.getNomPlateaux());
		this.jcbPlateaux.addActionListener(this);
		
		JLabel labelInfo = new JLabel("Choisir un plateau pour jouer ou le modifier", SwingConstants.CENTER);
		JLabel labelVide = new JLabel("");
		this.setOpaque(false);
		this.add(this.buttonSolo);
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
			if (e.getSource() == this.buttonSolo) 
			{
				String plateauSelectionne = (String) this.jcbPlateaux.getSelectedItem();
				new FrameSolo(this.frameMenu);//(Plateau.valueOf(plateauSelectionne));
			}

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
				this.frameMenu.getFrameModification().setVisible(true);
				this.frameMenu.setVisible(false);
			}
		}

		if (e.getSource() == this.buttonCreation) 
		{
			this.frameMenu.getFrameConfig().setVisible(true);
			this.frameMenu.setVisible(false);
		}
	}
}
>>>>>>> 1175df4 (fonction de fermeture des frames):IHM/PanelMenu.java
