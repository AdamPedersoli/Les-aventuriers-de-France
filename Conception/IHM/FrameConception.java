package Conception.IHM;

import Conception.inter.IPanelConception;
import Conception.ControleurConception;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Dimension;


public class FrameConception extends JFrame
{
	public static final int     PANEL_MENU                 = 0;
	public static final int     PANEL_CONFIG               = 1;
	public static final int     PANEL_CREATION_DEPARTEMENT = 2;
	public static final int     PANEL_CREATION_POLE        = 3;
	public static final int     PANEL_CREATION_DEBUT       = 4;
	
	private ControleurConception ctrl;
	
	private CardLayout           cdLyt;
	
	private PanelMenu                panelMenu;
	private PanelConfig              panelConfig;
	private PanelCreationDepartement panelCreationDepartement;
	private PanelCreationPole        panelCreationPole;
	private PanelCreationDebut       panelCreationDebut;
	
	
	public FrameConception( ControleurConception ctrl )
	{
		this.ctrl = ctrl;
		
		this.setLocation( 400, 100 );
		this.setMinimumSize(new Dimension(500, 300));
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		this.cdLyt = new CardLayout();
		
		this.setLayout( this.cdLyt );
		
		
		this.panelMenu                = new PanelMenu                ( this.ctrl, this );
		this.panelConfig              = new PanelConfig              ( this.ctrl, this );
		this.panelCreationDepartement = new PanelCreationDepartement ( this.ctrl, this );
		this.panelCreationPole        = new PanelCreationPole        ( this.ctrl, this );
		this.panelCreationDebut       = new PanelCreationDebut       ( this.ctrl, this );
		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.add( this.panelMenu               , this.panelMenu               .getNom() );
		this.add( this.panelConfig             , this.panelConfig             .getNom() );
		this.add( this.panelCreationDepartement, this.panelCreationDepartement.getNom() );
		this.add( this.panelCreationPole       , this.panelCreationPole       .getNom() );
		this.add( this.panelCreationDebut      , this.panelCreationDebut      .getNom() );
		
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void changerPanel( int selectPanel, boolean estRetour )
	{
		IPanelConception panel;
		
		switch ( selectPanel )
		{
			case 0  -> panel = this.panelMenu;
			case 1  -> panel = this.panelConfig;
			case 2  -> panel = this.panelCreationDepartement;
			case 3  -> panel = this.panelCreationPole;
			case 4  -> panel = this.panelCreationDebut;
			default -> { return; }
		}
		
		if ( false == estRetour)
			panel.init();
		
		this.cdLyt.show( this.getContentPane(), panel.getNom() );
		
		this.setTitle( "Conception - " + panel.getNom() );
		
		this.pack();
	}
}