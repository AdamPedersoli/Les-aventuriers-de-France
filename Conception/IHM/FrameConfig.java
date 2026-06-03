package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameConfig extends JFrame
{
	public FrameConfig()
	{
		this.setTitle("Configuration");
		// à changer en fonction de la taille du plateau
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelConfig());
		this.setVisible(true);
	}
}