package Conception.IHM;

import Conception.ControleurConception;
import Conception.Metier.TypePole;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class LabelPole extends JLabel
{
	private TypePole pole;
	
	private int lig;
	private int col;
	
	private Runnable repeindre;
	
	public LabelPole ( int lig, int col, Runnable repeindre )
	{
		super ( "", SwingConstants.CENTER);
		
		this.lig = lig;
		this.col = col;
		
		this.repeindre = repeindre;
		
		this.pole = null;
		
		this.setTransferHandler(new PoleCibleHandler(this));
		this.setPreferredSize  (new Dimension(50, 50));
		this.setBorder         (BorderFactory.createLineBorder(Color.BLACK));
		this.setOpaque         (false);
	}
	
	
	public TypePole getPole() { return this.pole; }
	public int      getLig () { return this.lig;  }
	public int      getCol () { return this.col;  }
	
	public void setPole( TypePole pole )
	{
		this.pole = pole;
		
		
		if ( this.pole == null )
			this.setIcon(null);
		else
			this.setIcon( this.pole.getImage() );
		
		repeindre.run();
	}
	
	
}