package jeu.ihm;

import jeu.ControleurJeu;

import javax.swing.*;
import java.awt.*;

public class FrameMenuJeu extends JFrame
{
	private ControleurJeu ctrl;

	public FrameMenuJeu(ControleurJeu ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Menu Les aventuriers de la France");
		this.setSize(800, 800);
		this.setLocation(0, 0);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new PanelMenuJeu(this.ctrl), BorderLayout.CENTER);
		this.setVisible(true);
	}
}