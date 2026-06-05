package IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameConfig extends JFrame
{
	private FrameCreation frameCreation;
	private FrameModification frameModification;

	private FrameMenu frameMenu;
	public FrameConfig(FrameMenu frameMenu)
	{
		
		this.frameMenu = frameMenu;

		this.setVisible(false);
		this.setTitle("Configuration");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* 
		this.frameCreation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameModification.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
		this.setLocation(425, 200);
		this.add(new PanelConfig(this.frameMenu));

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
}