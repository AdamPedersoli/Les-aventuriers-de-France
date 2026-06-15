package Jeu.IHM;

import javax.swing.*;
import java.awt.*;
import Jeu.ControleurJeu;

public class FrameDebug extends JFrame
{
	private ControleurJeu ctrl;
	
	public FrameDebug ( ControleurJeu ctrl )
	{
		this.ctrl = ctrl;
		
		this.setTitle("Cartes DEBUG");
		this.setLayout(new BorderLayout());
		this.setLocation(0, 0);
		
		this.add(new PanelDebug(this.ctrl));
		
		this.setVisible(true);
		this.pack();
	}
}
