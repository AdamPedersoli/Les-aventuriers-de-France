
package Jeu.IHM;
import java.awt.*;
import javax.swing.*;

public class FrameMenu extends JFrame
{
	private FrameSolo frameSolo;

	private JPanel panelCentral;
	public FrameMenu()
	{
		this.frameSolo = new FrameSolo(this);

		this.frameSolo.setVisible(false);
		this.setTitle("Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500, 200);
		
		this.panelCentral = new JPanel();
		this.panelCentral.setLayout(new BorderLayout());
		this.panelCentral.add(new PanelMenu(this), BorderLayout.CENTER);
		this.add(this.panelCentral);
		this.setVisible(true);
	}

	public JPanel getPanelCentral()
	{
		return this.panelCentral;
	}

	public JFrame getFrameSolo()
	{
		return this.frameSolo;
	}

	public static void main
}
