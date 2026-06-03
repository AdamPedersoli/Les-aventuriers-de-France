<<<<<<< HEAD:Conception/IHM/FrameMenu.java
package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameMenu extends JFrame
{
	private JPanel panelCentral;
	public FrameMenu()
	{
		this.setTitle("Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500, 200);
		
		this.panelCentral = new JPanel();
		this.panelCentral.setLayout(new BorderLayout());
		this.panelCentral.add(new PanelMenu(), BorderLayout.CENTER);
		this.add(this.panelCentral);
		this.setVisible(true);
	}
}
=======
package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameMenu extends JFrame
{
	private FrameConfig frameConfig;
	private FrameCreation frameCreation;
	private FrameModification frameModification;
	private FrameSolo frameSolo;

	private JPanel panelCentral;
	public FrameMenu()
	{
		this.frameConfig = new FrameConfig(this);
		this.frameCreation = new FrameCreation(this);
		this.frameModification = new FrameModification(this);
		this.frameSolo = new FrameSolo(this);

		this.frameConfig.setVisible(false);
		this.frameCreation.setVisible(false);
		this.frameModification.setVisible(false);
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

	public JFrame getFrameSolo()
	{
		return this.frameSolo;
	}
}
>>>>>>> 8d895fd (maj des ActionPerformed et autres):IHM/FrameMenu.java
