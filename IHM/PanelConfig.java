package IHM;
import java.awt.*;
import javax.swing.*;

public class PanelConfig extends JPanel
{
	public PanelConfig()
	{
		this.setLayout(new GridLayout(5, 1));
		
		JPanel panelDim        = new JPanel();
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();

		panelDim.add(new JLabel("Dimensions :"));
		panelDim.add(new JTextField("Largeur", 10));
		panelDim.add(new JTextField("Hauteur", 10));

		panelPoles.add(new JTextField("Nombre de Pôles :", 10));
		panelDep.add(new JTextField("Nombre de Départements :", 10));
		panelTransport.add(new JTextField("Nombre de Transports :", 10));
		
		this.add(panelDim);
		this.add(panelPoles);
		this.add(panelDep);
		this.add(panelTransport);
		this.add(new JButton("Valider"));
	}
}