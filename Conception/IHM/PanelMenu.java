package Conception.IHM;

import Conception.inter.IPanelConception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Conception.ControleurConception;

public class PanelMenu extends JPanel implements IPanelConception, ActionListener
{
	private String               nomPanel;
	private ControleurConception ctrl;
	private FrameConception      frameCpt;
	
	private JPanel               panelAction;
	
	private JComboBox<String>    jcbPlateaux;
	
	private JButton              btnCreation;
	private JButton              btnModif;

	public PanelMenu( ControleurConception ctrl, FrameConception frameCpt )
	{
		this.ctrl     = ctrl;
		this.frameCpt = frameCpt;
		this.nomPanel = "Menu";
		
		this.setLayout(new GridLayout(4, 1));
		this.setOpaque(false);
		
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		this.panelAction = new JPanel();
		
		this.jcbPlateaux = new JComboBox<String>();
		
		this.jcbPlateaux.addItem(null);
		this.jcbPlateaux.addItem("Test");
		
		
		this.btnCreation = new JButton("Creation");
		this.btnModif    = new JButton("Modification");
		
		
		JLabel labelInfo = new JLabel("Choisissez un plateau pour le modifier ou créez-en un nouveau", SwingConstants.CENTER);
		labelInfo.setOpaque(true);
		labelInfo.setForeground(Color.GRAY);
		
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.panelAction.add(this.btnModif);
		this.panelAction.add(this.btnCreation);
		
		
		this.add(     labelInfo  );
		this.add(this.jcbPlateaux);
		this.add(new  JLabel()   );
		this.add(this.panelAction);
		
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		this.jcbPlateaux.addActionListener(this);
		this.btnCreation.addActionListener(this);
		this.btnModif   .addActionListener(this);
		
		
		
	}

	public void actionPerformed(ActionEvent e) 
	{

		if (e.getSource() == this.btnModif) 
		{
			String nomPlateau = (String) this.jcbPlateaux.getSelectedItem();
			
			if ( !nomPlateau.equals(null) || !nomPlateau.equals("") ) 
			{
				// Changement de panel pour le panelModification
			}
			
		}
		
		if (e.getSource() == this.btnCreation) 
		{
			this.frameCpt.changerPanel(FrameConception.PANEL_CONFIG, false);
		}
	}
	
	public void   init  () {}
	public String getNom() { return this.nomPanel; }
}
