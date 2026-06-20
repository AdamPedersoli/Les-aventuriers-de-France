package Conception.IHM;

import Conception.inter.IPanelConception;
import Conception.ControleurConception;
import Conception.Metier.TypePole;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelConfig extends JPanel implements IPanelConception, ActionListener
{
	private static final int     ERREUR_LIGNE       = 0;
	private static final int     ERREUR_COLONNE     = 1;
	private static final int     ERREUR_DEPARTEMENT = 2;
	private static final int     ERREUR_TRANSPORT   = 3;
	private static final int     ERREUR_POLE        = 4;
	
	private static final int     NOMBRE_POLE_MINI   = 1;
	
	private ControleurConception ctrl;
	private FrameConception      frameCpt;
	private String               nomPanel;
	
	private JTextField           txtFldNom;
	private JTextField           txtFldLig;
	private JTextField           txtFldCol;
	private JTextField           txtFldDep;
	private JTextField           txtFldTransport;
	
	private JCheckBox[]          tabCBPoles;
	
	private JButton              btnAnnuler;
	private JButton              btnValider;
	
	
	public PanelConfig( ControleurConception ctrl, FrameConception frameCpt )
	{
		this.ctrl     = ctrl;
		this.frameCpt = frameCpt;
		
		this.nomPanel = "Configuration Plateau";
		this.setLayout(new GridLayout(6, 1));
		
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		JPanel panelNomPlateau = new JPanel();
		JPanel panelDimensions = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();
		JPanel panelPoles      = new JPanel(new GridLayout(2, 4));
		JPanel panelAction     = new JPanel();
		
		
		
		JLabel lblNomPlateau = new JLabel("Nom du Plateau : ");
		this.txtFldNom       = new JTextField("NouveauPlateau", 20);
		
		JLabel lblDim        = new JLabel("Dimensions :   ");
		
		JLabel lblLig        = new JLabel("Nombres de lignes : ");
		this.txtFldLig       = new JTextField(10);
		
		JLabel lblCol        = new JLabel("Nombres de colonnes : ");
		this.txtFldCol       = new JTextField(10);
		
		JLabel lblNbDep      = new JLabel("Nombre de départements : ");
		this.txtFldDep       = new JTextField(10);
		
		JLabel lblNbTrans    = new JLabel("Nombres de moyen de transports (Manches) : ");
		this.txtFldTransport = new JTextField(10);
		
		
		this.tabCBPoles      = new JCheckBox[7];
		
		for (int cpt = 0; cpt < this.tabCBPoles.length; cpt++)
			this.tabCBPoles[cpt] = new JCheckBox( TypePole.values()[cpt].getNom() );
		
		
		this.btnAnnuler = new JButton("Annuler");
		this.btnValider = new JButton("Valider");
		
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		panelNomPlateau.add(     lblNomPlateau);
		panelNomPlateau.add(this.txtFldNom    );
		
		panelDimensions.add(     lblDim   );
		panelDimensions.add(     lblLig   );
		panelDimensions.add(this.txtFldLig);
		panelDimensions.add(     lblCol   );
		panelDimensions.add(this.txtFldCol);
		
		panelDep.add(     lblNbDep );
		panelDep.add(this.txtFldDep);
		
		panelTransport.add(     lblNbTrans     );
		panelTransport.add(this.txtFldTransport);
		
		for ( int cpt = 0 ; cpt < this.tabCBPoles.length ; cpt++ )
			panelPoles.add( this.tabCBPoles[cpt] );
		
		panelAction.add(this.btnAnnuler);
		panelAction.add(this.btnValider);
		
		
		this.add( panelNomPlateau );
		this.add( panelDimensions );
		this.add( panelDep        );
		this.add( panelTransport  );
		this.add( panelPoles      );
		this.add( panelAction     );
		
		
		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		
		
		this.txtFldNom      .addActionListener(this);
		this.txtFldLig      .addActionListener(this);
		this.txtFldCol      .addActionListener(this);
		this.txtFldDep      .addActionListener(this);
		this.txtFldTransport.addActionListener(this);
		
		for (JCheckBox cb : this.tabCBPoles)
			cb.addActionListener(this);
		
		this.btnAnnuler     .addActionListener(this);
		this.btnValider     .addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnAnnuler )
			this.frameCpt.changerPanel(FrameConception.PANEL_MENU, true);
		
		if ( e.getSource() == this.btnValider )
		{
			this.sauvegarder();
			
			this.frameCpt.changerPanel(FrameConception.PANEL_CREATION_DEPARTEMENT, false);
		}
	}
	
	public void   init  ()
	{
		this.txtFldLig      .setText("");
		this.txtFldCol      .setText("");
		this.txtFldDep      .setText("");
		this.txtFldTransport.setText("");
		
		for ( JCheckBox cb : this.tabCBPoles )
			cb.setSelected(false);
		
		
	}
	
	public String getNom() { return this.nomPanel; }
	
	public void   sauvegarder()
	{
		
		String nomPlateau = this.txtFldNom      .getText();
		String sLig       = this.txtFldLig      .getText();
		String sCol       = this.txtFldCol      .getText();
		String sDep       = this.txtFldDep      .getText();
		String sTransport = this.txtFldTransport.getText();
		
		int nbLig, nbCol, nbDep, nbTransport, nbPoleDiff;
		
		ArrayList<Integer> lstIndexTypePole  = new ArrayList<Integer>();
		
		// on vérifie que tous les champs remplis sont des entiers et que les champs ne sont pas vides
		// sinon on renvoi une erreur
		if ( !sLig.matches("[0-9]{1,9}") )
		{
			this.messageErreur(PanelConfig.ERREUR_LIGNE);
			return;
		}
		nbLig = Integer.parseInt(sLig);
		
		if ( !sCol.matches("[0-9]{1,9}") )
		{
			this.messageErreur(PanelConfig.ERREUR_COLONNE);
			return;
		}
		nbCol = Integer.parseInt(sCol);
		
		if ( !sDep.matches("[0-9]{1,9}") )
		{
			this.messageErreur(PanelConfig.ERREUR_DEPARTEMENT);
			return;
		}
		nbDep = Integer.parseInt(sDep);
		
		if ( !sTransport.matches("[0-9]{1,9}") )
		{
			this.messageErreur(PanelConfig.ERREUR_TRANSPORT);
			return;
		}
		nbTransport = Integer.parseInt(sTransport);
		
		
		// On compte et stock les pôles utilisés et renvoi une erreur si inférieur au nombre minimal
		nbPoleDiff = 0;
		for ( int cpt = 0 ; cpt < tabCBPoles.length ; cpt++ )
		{
			if ( tabCBPoles[cpt].isSelected() )
			{
				nbPoleDiff++;
				lstIndexTypePole.add( cpt );
			}
		}
		
		if ( nbPoleDiff < NOMBRE_POLE_MINI )
		{
			this.messageErreur(PanelConfig.ERREUR_POLE);
			return;
		}
		
		
		this.ctrl.setPlateau(nomPlateau, nbLig, nbCol, nbDep, nbPoleDiff, nbTransport);
		
		this.ctrl.setLstPoleSelect( lstIndexTypePole );
		
		
	}
	
	private void messageErreur(int erreur)
	{
		switch (erreur)
		{
			case 0  -> JOptionPane.showMessageDialog(this, "Le nombre de lignes doit être un entier inférieur à 1 000 000 000"             , "Entrée invalide", JOptionPane.ERROR_MESSAGE);
			case 1  -> JOptionPane.showMessageDialog(this, "Le nombre de colonnes doit être un entier inférieur à 1 000 000 000"           , "Entrée invalide", JOptionPane.ERROR_MESSAGE);
			case 2  -> JOptionPane.showMessageDialog(this, "Le nombre de départements doit être un entier inférieur à 1 000 000 000"       , "Entrée invalide", JOptionPane.ERROR_MESSAGE);
			case 3  -> JOptionPane.showMessageDialog(this, "Le nombre de moyen de transports doit être un entier inférieur à 1 000 000 000", "Entrée invalide", JOptionPane.ERROR_MESSAGE);
			case 4  -> JOptionPane.showMessageDialog(this, "Le nombre minimal de pôles requis est " + NOMBRE_POLE_MINI                     , "Entrée invalide", JOptionPane.ERROR_MESSAGE);
			default -> { return; }
		}
	}
}

