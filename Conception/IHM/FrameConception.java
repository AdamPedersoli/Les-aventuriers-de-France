package Conception.IHM;

import Conception.inter.IPanelConception;
import Conception.ControleurConception;

import javax.swing.*;
import java.awt.CardLayout;


public class FrameConception extends JFrame
{
	public static final int     PANEL_MENU                 = 0;
	public static final int     PANEL_CONFIG               = 1;
	public static final int     PANEL_CREATION_DEPARTEMENT = 2;
	
	private ControleurConception ctrl;
	
	private CardLayout           cdLyt;
	
	private PanelMenu                panelMenu;
	private PanelConfig              panelConfig;
	private PanelCreationDepartement panelCreationDepartement;
	
	public FrameConception( ControleurConception ctrl )
	{
		this.ctrl = ctrl;
		
		this.setLocation( 400, 100 );
		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		
		this.cdLyt = new CardLayout();
		
		this.setLayout( this.cdLyt );
		
		
		this.panelMenu                = new PanelMenu                ( this.ctrl, this );
		this.panelConfig              = new PanelConfig              ( this.ctrl, this );
		this.panelCreationDepartement = new PanelCreationDepartement ( this.ctrl, this );

		
		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.add( this.panelMenu               , this.panelMenu               .getNom() );
		this.add( this.panelConfig             , this.panelConfig             .getNom() );
		this.add( this.panelCreationDepartement, this.panelCreationDepartement.getNom() );
		
		
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
			default -> { return; }
		}
		
		if ( false == estRetour)
			panel.init();
		
		this.cdLyt.show( this.getContentPane(), panel.getNom() );
		
		this.setTitle( "Conception - " + panel.getNom() );
		
		this.pack();
	}
}