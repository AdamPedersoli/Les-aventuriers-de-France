package Conception.IHM;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Conception.ControleurConception;

public class FrameConfig extends JFrame
{
	private ControleurConception ctrl;
	private FrameCreation frameCreation;
	
	public FrameConfig(ControleurConception ctrl)
	{
		this.ctrl = ctrl;

		this.setVisible(false);
		this.setTitle("Configuration");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* 
		this.frameCreation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frameModification.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
		this.setLocation(425, 200);
		this.add(new PanelConfig(this.ctrl));

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}
}
