import javax.swing.*;

public class FrameMenu extends JFrame
{
	private JButton buttonSolo;
	private JButton buttonCreation;
	public FrameMenu()
	{
		this.setTitle("Menu");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.buttonSolo = new JButton("Solo");
		this.buttonCreation = new JButton("Creation");
		
		this.setLayout(null);
		
		this.buttonSolo.setBounds(150, 100, 100, 50);
		this.buttonCreation.setBounds(150, 200, 100, 50);
		
		this.add(this.buttonSolo);
		this.add(this.buttonCreation);
		
		this.setVisible(true);
	}
}
