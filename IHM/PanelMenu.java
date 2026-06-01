package IHM;
import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel 
{
	private JButton buttonSolo;
	private JButton buttonCreation;

	public PanelMenu()
	{
		this.setLayout(new GridLayout(2, 1));
		
		this.buttonSolo = new JButton("Solo");
		this.buttonCreation = new JButton("Creation");
		
		this.add(this.buttonSolo);
		this.add(this.buttonCreation);
	}
	
}
