import javax.swing.*;

public class PanelMenu extends JPanel 
{
	private JButton buttonSolo;
	private JButton buttonCreation;
	public PanelMenu()
	{
		this.setLayout(null);
		
		this.buttonSolo = new JButton("Solo");
		this.buttonCreation = new JButton("Creation");
		
		this.buttonSolo.setBounds(150, 100, 100, 50);
		this.buttonCreation.setBounds(150, 200, 100, 50);
		
		this.add(this.buttonSolo);
		this.add(this.buttonCreation);
	}
	
}
