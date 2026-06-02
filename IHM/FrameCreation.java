package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameCreation extends JFrame
{
	public FrameCreation()
	{
		this.setTitle("Creation");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelCreation());
		this.setVisible(true);
	}
}