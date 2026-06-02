package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameCreation extends JFrame
{
	public FrameCreation()
	{
		this.setTitle("Creation");
		// à changer en fonction de la taille du plateau
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelCreation());
		this.setVisible(true);
	}
}