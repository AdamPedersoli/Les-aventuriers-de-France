<<<<<<< HEAD:Conception/IHM/FrameConfig.java
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
=======
package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameConfig extends JFrame
{
	private FrameMenu frameMenu;
	public FrameConfig(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;

		this.setTitle("Configuration");
		// à changer en fonction de la taille du plateau
		this.setSize(600, 600);
		// ferme le frame et toutes les autres frames ouvertes
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelConfig(this.frameMenu));
		this.setVisible(true);
	}
>>>>>>> 8d895fd (maj des ActionPerformed et autres):IHM/FrameConfig.java
}