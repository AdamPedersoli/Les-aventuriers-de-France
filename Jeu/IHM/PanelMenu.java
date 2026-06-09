
package Jeu.IHM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import Controleur;

public class PanelMenu extends JPanel implements ActionListener
{
	private FrameMenu frameMenu;
	private JButton buttonSolo;
	private JButton buttonMultijoueur;
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
		
		this.buttonMultijoueur = new JButton("Multijoueur");
		this.buttonMultijoueur.addActionListener(this);

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
		this.add(this.buttonMultijoueur);
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

		}
	}
}
