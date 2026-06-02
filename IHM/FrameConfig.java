package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameConfig extends JFrame
{
	public FrameConfig()
	{
		this.setTitle("Configuration");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelConfig());
		this.setVisible(true);
	}
}