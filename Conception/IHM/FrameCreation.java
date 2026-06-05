package IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameCreation extends JFrame
{
	private FrameConfig frameConfig;
	private FrameModification frameModification;

	private FrameMenu frameMenu;
	private String nomPlateauConfig = "";

	public FrameCreation(FrameMenu frameMenu)
	{
		this.setVisible(false);
		this.frameMenu = frameMenu;
		this.setTitle("Creation");
		// à changer en fonction de la taille du plateau
		this.setSize(800, 600);
		this.setLocation(350,150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* 
		this.frameModification.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
		this.add(new PanelCreation(this));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				frameMenu.fermerToutesLesFenetres();
			}
		});
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

