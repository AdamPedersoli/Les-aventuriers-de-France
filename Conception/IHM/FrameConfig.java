package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameConfig extends JFrame
{
	private FrameMenu frameMenu;
	public FrameConfig(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;

		this.setVisible(false);
		this.setTitle("Configuration");
		this.setSize(600, 600);
		// ferme le frame et toutes les autres frames ouvertes
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelConfig(this.frameMenu));
	}
}