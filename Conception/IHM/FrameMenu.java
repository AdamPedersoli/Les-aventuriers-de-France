
package IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameMenu extends JFrame
{
	private FrameConfig frameConfig;
	private FrameCreation frameCreation;
	private FrameModification frameModification;

	private JPanel panelCentral;
	public FrameMenu()
	{
		
		this.frameConfig = new FrameConfig(this);
		this.frameCreation = new FrameCreation(this);
		this.frameModification = new FrameModification(this);

		this.frameConfig.setVisible(false);
		this.frameCreation.setVisible(false);
		this.frameModification.setVisible(false);
		this.setTitle("Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameCreation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameModification.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Si on ferme la frame Menu, on ferme aussi toutes les autres frames ouvertes
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(450, 200);
		
		this.panelCentral = new JPanel();
		this.panelCentral.setLayout(new BorderLayout());
		this.panelCentral.add(new PanelMenu(this), BorderLayout.CENTER);
		this.add(this.panelCentral);
		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				fermerToutesLesFenetres();
			}
		});

	}

	public void fermerToutesLesFenetres()
	{
		this.frameConfig.dispose();
		this.frameCreation.dispose();
		this.frameModification.dispose();
		this.dispose();

		System.exit(0);
	}

	public JPanel getPanelCentral()
	{
		return this.panelCentral;
	}

	public JFrame getFrameConfig()
	{
		return this.frameConfig;
	}

	public JFrame getFrameCreation()
	{
		return this.frameCreation;
	}

	public JFrame getFrameModification()
	{
		return this.frameModification;
	}
}
