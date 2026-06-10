package Jeu.IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelPlateau extends JPanel implements MouseListener
{
	private Controleur ctrl;
	private Case selection = null;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setPreferredSize(new Dimension(1500, 1000));
		this.addMouseListener(this);
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
			g2.setColor(dep.getCouleur());
			for (Case c : dep.getLstCases())
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
			g2.drawRect(c.getX() * 50, c.getY() * 50, 50, 50);
		}
		g2.setStroke(new BasicStroke(1)); 

		/*====================*/
		/* SEGMENTS (METIER)  */
		/*====================*/
		ArrayList<MoyenTransport> lstMoyenTransport = ctrl.getLstMoyenTransport();

		g2.setStroke(new BasicStroke(3)); 
		for (Trajet t : ctrl.getLstTrajet())
		{
			for (Segment s : t.getLstSegments())
			{
				int idx = this.ctrl.getLstTrajet().getIndexOf(t);
				g2.setColor(lstMoyenTransport.get(idx).getCouleur());
				g2.drawLine(
					s.getCase1().getX() * 50 + 25,
					s.getCase1().getY() * 50 + 25,
					s.getCase2().getX() * 50 + 25,
					s.getCase2().getY() * 50 + 25
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
				c.getX() * 50,
				c.getY() * 50,
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
			g2.fillOval(selection.getX() * 50, selection.getY() * 50, 50, 50);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX() / 50;
		int y = e.getY() / 50;

		for (Case c : ctrl.getLstCasePole())
		{
			if (c.getX() == x && c.getY() == y)
			{
				if (selection == null)
				{
					selection = c;
				}
				else
				{
					ctrl.ajouterSegment(selection, c);
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
