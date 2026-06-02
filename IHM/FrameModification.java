package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameModification extends JFrame
{
	public FrameModification()
	{
		this.setTitle("Modification");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelCreation());
		this.setVisible(true);
	}
}