
package Conception.IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conception.ControleurConception;

public class FrameModification extends JFrame
{
	private ControleurConception ctrl;
	private FrameConfig frameConfig;
	private FrameCreation frameCreation;
	
	public FrameModification(ControleurConception ctrl)
	{
		this.setVisible(false);
		this.setTitle("Modification");
		this.setSize(800, 600);
		this.setLocation(350,150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*/
		this.frameCreation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
		this.add(new PanelModification(this.ctrl));

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
}
