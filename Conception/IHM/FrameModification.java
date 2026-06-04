
package IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameModification extends JFrame
{
	private FrameConfig frameConfig;
	private FrameCreation frameCreation;
	
	private FrameMenu frameMenu;
	public FrameModification(FrameMenu frameMenu)
	{
		this.setVisible(false);
		this.frameMenu = frameMenu;
		this.setTitle("Modification");
		this.setSize(800, 600);
		this.setLocation(350,150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/*/
		this.frameCreation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
		this.add(new PanelModification(this.frameMenu));

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				frameMenu.fermerToutesLesFenetres();
			}
		});
	}

	public FrameMenu getFrameMenu()
	{
		return this.frameMenu;
	}
}