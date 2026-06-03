<<<<<<< HEAD:Conception/IHM/FrameModification.java
package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameModification extends JFrame
{
	public FrameModification()
	{
		this.setTitle("Modification");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelCreation());
		this.setVisible(true);
	}
=======
package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameModification extends JFrame
{
	private FrameMenu frameMenu;
	public FrameModification(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
		this.setTitle("Modification");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelModification(this.frameMenu));
		this.setVisible(true);
	}
>>>>>>> 8d895fd (maj des ActionPerformed et autres):IHM/FrameModification.java
}