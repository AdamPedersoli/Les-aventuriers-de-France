package jeu.ihm;

import jeu.ControleurJeu;
import jeu.metier.Carte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelDebug extends JPanel implements ActionListener
{
	private ControleurJeu ctrl;
	private ArrayList<JButton> lstBtnCartes;
	private ArrayList<Carte> lstCartes;

	public PanelDebug(ControleurJeu ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.lstBtnCartes = new ArrayList<JButton>();
		this.lstCartes = this.ctrl.getCartesDispo();

		for (Carte c : this.lstCartes)
		{
			JButton btn = new JButton(c.getImage());
			this.lstBtnCartes.add(btn);
			this.add(btn);
			btn.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (JButton b : this.lstBtnCartes)
		{
			if (e.getSource() == b)
			{
				int indice = this.lstBtnCartes.indexOf(b);
				Carte c = this.lstCartes.get(indice);

				this.ctrl.forcerProchaineCarte(c.getType(), c.getTeinte());
				return;
			}
		}
	}
}
