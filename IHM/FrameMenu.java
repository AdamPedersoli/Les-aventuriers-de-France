package IHM;
import javax.swing.*;

public class FrameMenu extends JFrame
{
	public FrameMenu()
	{
		this.setTitle("Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500, 200);
		
		this.add(new PanelMenu());
		this.setVisible(true);
	}
}
