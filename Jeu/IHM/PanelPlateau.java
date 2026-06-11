package Jeu.IHM;

import Jeu.ControleurJeu;
import Jeu.Metier.*;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelPlateau extends JPanel implements MouseListener
{
	private ControleurJeu ctrl;
	private Case selection = null;
	public boolean aJoue;

	public PanelPlateau(ControleurJeu ctrl)
	{
		this.ctrl = ctrl;
		this.setPreferredSize(new Dimension(1500, 1000));
		this.addMouseListener(this);
		this.aJoue = false;
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		/*====================*/
		/* DEPARTEMENTS       */
		/*====================*/
		for (Departement dep : ctrl.getLstDep())
		{
			g2.setColor(dep.getTypeDepartement().getCouleur());
			for (Case c : dep.getLstCase())
			{
				g2.fillRect(c.getX() * 50, c.getY() * 50, 50, 50);
			}
		}
		
		/*====================*/
		/* CASES DEPART       */
		/*====================*/
		ArrayList<Case> lstCaseDepart = ctrl.getLstCaseDepart();
		ArrayList<MoyenTransport> lstMoyenTransport = ctrl.getLstMoyenTransport();

		g2.setStroke(new BasicStroke(3));
		for (int i = 0; i < lstCaseDepart.size(); i++)
		{
			Case c = lstCaseDepart.get(i);
			g2.setColor(lstMoyenTransport.get(i).getCouleur());
			g2.drawRect(c.getY() * 50, c.getX() * 50, 50, 50);
		}
		g2.setStroke(new BasicStroke(1)); 

		/*====================*/
		/* SEGMENTS (METIER)  */
		/*====================*/

		g2.setStroke(new BasicStroke(3)); 
		for (Trajet t : ctrl.getLstTrajet())
		{
			for (Segment s : t.getLstSegment())
			{
				int idx = this.ctrl.getLstTrajet().indexOf(t);
				g2.setColor(lstMoyenTransport.get(idx).getCouleur());
				g2.drawLine(
					s.getCaseA().getY() * 50 + 25,
					s.getCaseA().getX() * 50 + 25,
					s.getCaseB().getY() * 50 + 25,
					s.getCaseB().getX() * 50 + 25
				);
			}
		}
		g2.setStroke(new BasicStroke(1));

		/*====================*/
		/* POLES              */
		/*====================*/
		for (Case c : ctrl.getLstCasePole())
		{
			g2.drawImage(
				c.getPole().getTypePole().getImage(),
				c.getY() * 50,
				c.getX() * 50,
				50,
				50,
				this
			);
		}

		/*====================*/
		/* SELECTION VISUEL   */
		/*====================*/
		if (selection != null)
		{
			g2.setColor(new Color(255, 0, 0, 120));
			g2.fillOval(selection.getY() * 50, selection.getX() * 50, 50, 50);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		int x = e.getY() / 50;
		int y = e.getX() / 50;

		for (Case c : ctrl.getLstCasePole())
		{
			if ( (c.getX() == x && c.getY() == y) && !this.aJoue )
			{
				if (selection == null)
				{
					selection = c;
				}
				else
				{
					this.aJoue = this.ctrl.ajouterSegment(selection, c);
					selection = null;
				}
				repaint();
				return; 
			}
		}
	}

	public void mousePressed (MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited  (MouseEvent e) {}
}
