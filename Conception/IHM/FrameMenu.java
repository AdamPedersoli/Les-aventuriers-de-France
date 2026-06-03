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
