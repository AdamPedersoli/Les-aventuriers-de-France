
package Conception.IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conception.ControleurConception;

public class FrameMenu extends JFrame
{
	private ControleurConception ctrl;

	private PanelMenu panelMenu;
	public FrameMenu( ControleurConception ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle("Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Si on ferme la frame Menu, on ferme aussi toutes les autres frames ouvertes
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(450, 200);
		
		this.panelMenu = new PanelMenu(this, this.ctrl);
		this.add(this.panelMenu);
		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
}
