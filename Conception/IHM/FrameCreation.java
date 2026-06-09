package Conception.IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conception.ControleurConception;
import java.util.ArrayList;


public class FrameCreation extends JFrame
{
	private ControleurConception ctrl;
	private FrameConfig frameConfig;
	private FrameModification frameModification;

	private String nomPlateauConfig = "";

	public FrameCreation(ControleurConception ctrl, ArrayList<Integer> lstTypePole)
	{
		this.ctrl = ctrl;
		this.setVisible(false);
		this.setTitle("Creation");
		this.setSize(800, 600);
		this.setLocation(350,150);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new PanelCreation(this, this.ctrl, lstTypePole));
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

