package Jeu.IHM;
import java.awt.*;
import javax.swing.*;

public class FrameSolo extends JFrame
{
	private FrameMenu frameMenu;
	public FrameSolo(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
		this.setTitle("Solo");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelSolo(this.frameMenu));
		this.setVisible(true);
	}
}