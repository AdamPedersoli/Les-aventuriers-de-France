package IHM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelConfig extends JPanel implements ActionListener
{
	JButton buttonValider;
	public PanelConfig()
	{
		this.setLayout(new GridLayout(5, 1));
		
		JPanel panelDim        = new JPanel();
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();

		panelDim.add(new JLabel("Dimensions :   "));
		panelDim.add(new JLabel("Ligne : "));
		panelDim.add(new JTextField( 10));
		panelDim.add(new JLabel("Colonne : "));
		panelDim.add(new JTextField( 10));

		panelPoles.add(new JLabel("Nombre de Pôles : "));
		panelPoles.add(new JTextField( 10));
		panelDep.add(new JLabel("Nombre de Départements : "));
		panelDep.add(new JTextField( 10));
		panelTransport.add(new JLabel("Nombre de Transports : "));
		panelTransport.add(new JTextField( 10));
		
		this.add(panelDim);
		this.add(panelPoles);
		this.add(panelDep);
		this.add(panelTransport);
		this.buttonValider = new JButton("Valider");
		this.buttonValider.addActionListener(this);
		this.add(this.buttonValider);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonValider)
		{
			new FrameCreation();
			this.setVisible(false);
			this.setEnabled(false);
		}
	}
}