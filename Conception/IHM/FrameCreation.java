package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameCreation extends JFrame
{
	private FrameMenu frameMenu;
	public FrameCreation(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
		this.setTitle("Creation");
		// à changer en fonction de la taille du plateau
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelCreation(this));
		this.setVisible(true);
	}

	public FrameMenu getFrameMenu()
	{
		return this.frameMenu;
	}
}