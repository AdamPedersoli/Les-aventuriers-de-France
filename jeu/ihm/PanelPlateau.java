package jeu.ihm;
import jeu.ControleurJeu;
import jeu.metier.*;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PanelPlateau extends JPanel implements MouseListener
{
    private ControleurJeu ctrl;
    private Case selection = null;
    public boolean aJoue;

    private int nbCols;
    private int nbLigs;  

    public PanelPlateau(ControleurJeu ctrl)
    {
        this.ctrl = ctrl;
        this.nbCols = this.ctrl.getTailleX();
        this.nbLigs = this.ctrl.getTailleY();
        this.setPreferredSize(new Dimension(1500, 1000));
        this.addMouseListener(this);
        this.aJoue = false;
        this.majBordure();
    }

    // Taille d'une case en pixels (calculée dynamiquement)
    private int cellW() { return this.getWidth()  / this.nbCols;   }
    private int cellH() { return this.getHeight() / this.nbLigs;   }

    public void majBordure()
    {
        Color couleur = this.ctrl.getLstMoyenTransport()
                                 .get(this.ctrl.getManche())
                                 .getCouleur();
        this.setBorder(BorderFactory.createLineBorder(couleur, 3));
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int cw = cellW();
        int ch = cellH();

        /*====================*/
        /* FOND : CARTE       */   
        /*====================*/
        ImageIcon icon = new ImageIcon(getClass().getResource("../images/France.png"));
        g2.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);

        /*====================*/
        /* DEPARTEMENTS       */
        /*====================*/
        for (Departement dep : ctrl.getLstDep())
        {
            g2.setColor(dep.getTypeDepartement().getCouleur());
            for (Case c : dep.getLstCase())
            {
                g2.fillRect(c.getX() * cw, c.getY() * ch, cw, ch);
            }
        }

        /*====================*/
        /* CASES DEPART       */
        /*====================*/
        ArrayList<Case> lstCaseDepart     = ctrl.getLstCaseDepart();
        ArrayList<MoyenTransport> lstMT   = ctrl.getLstMoyenTransport();
        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i < lstCaseDepart.size(); i++)
        {
            Case c = lstCaseDepart.get(i);
            g2.setColor(lstMT.get(i).getCouleur());
            g2.drawRect(c.getY() * cw, c.getX() * ch, cw, ch); // cohérent avec fillRect
        }
        g2.setStroke(new BasicStroke(1));

        /*====================*/
        /* LIAISONS DE BASE   */
        /*====================*/
        g2.setColor(Color.DARK_GRAY);
        for (Case c : this.ctrl.getLstCasePole())
        {
            for (Case v : c.getPole().getLstVoisin())
            {
                g2.drawLine(
                    c.getY() * cw + cw / 2,  // centre X de la case
                    c.getX() * ch + ch / 2,  // centre Y de la case
                    v.getY() * cw + cw / 2,
                    v.getX() * ch + ch / 2
                );
            }
        }

        /*====================*/
        /* SEGMENTS (METIER)  */
        /*====================*/
        g2.setStroke(new BasicStroke(3));
        for (Trajet t : ctrl.getLstTrajet())
        {
            g2.setColor(lstMT.get(ctrl.getLstTrajet().indexOf(t)).getCouleur());
            for (Segment s : t.getLstSegment())
            {
                g2.drawLine(
                    s.getCaseA().getY() * cw + cw / 2,
                    s.getCaseA().getX() * ch + ch / 2,
                    s.getCaseB().getY() * cw + cw / 2,
                    s.getCaseB().getX() * ch + ch / 2
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
                c.getY() * cw,
                c.getX() * ch,
                cw, ch,   // l'image remplit toute la case
                this
            );
        }

        /*====================*/
        /* SELECTION VISUEL   */
        /*====================*/
        if (selection != null)
        {
            g2.setColor(new Color(255, 0, 0, 120));
            g2.fillOval(selection.getY() * cw, selection.getX() * ch, cw, ch);
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        int cw = cellW();
        int ch = cellH();

        int x = e.getY() / ch;  // ligne  (axe X métier)
        int y = e.getX() / cw;  // colonne (axe Y métier)

        for (Case c : ctrl.getLstCasePole())
        {
            if ((c.getX() == x && c.getY() == y) && !this.aJoue)
            {
                if (selection == null)
                    selection = c;
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
