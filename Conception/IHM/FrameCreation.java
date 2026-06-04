package IHM;
import java.awt.*;
import javax.swing.*;

public class FrameCreation extends JFrame
{
	private FrameMenu frameMenu;
	private String nomPlateauConfig = "";

	public FrameCreation(FrameMenu frameMenu)
	{
		this.setVisible(false);
		this.frameMenu = frameMenu;
		this.setTitle("Creation");
		// à changer en fonction de la taille du plateau
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 200);
		this.add(new PanelCreation(this));

	}

	public FrameMenu getFrameMenu()
	{
		return this.frameMenu;
	}

	public void setNomPlateauConfig(String nomPlateau)
	{
		this.nomPlateauConfig = (nomPlateau == null) ? "" : nomPlateau;
	}

	public String getNomPlateauConfig()
	{
		return this.nomPlateauConfig;
	}
}

