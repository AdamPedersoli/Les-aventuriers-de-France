<<<<<<< HEAD:Conception/IHM/PanelConfig.java
<<<<<<< HEAD:Conception/IHM/PanelConfig.java
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
=======
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
			FrameCreation frameCreation = new FrameCreation();
			frameCreation.setVisible(true);
			FrameMenu frameMenu = (FrameMenu) SwingUtilities.getWindowAncestor(this);
			frameMenu.setVisible(false);
			this.setVisible(false);
			this.setEnabled(false);
		}
	}
>>>>>>> 1175df4 (fonction de fermeture des frames):IHM/PanelConfig.java
=======
package IHM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelConfig extends JPanel implements ActionListener
{
	private FrameMenu frameMenu;
	JButton buttonValider;
	JTextField textFieldLigne = new JTextField( 10);
	JTextField textFieldColonne = new JTextField( 10);
	JTextField textFieldPoles = new JTextField( 10);
	JTextField textFieldDep = new JTextField( 10);
	JTextField textFieldTransport = new JTextField( 10);
	public PanelConfig(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
		this.setLayout(new GridLayout(5, 1));
		
		JPanel panelDim        = new JPanel();
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();

		panelDim.add(new JLabel("Dimensions :   "));
		panelDim.add(new JLabel("Ligne : "));
		panelDim.add(textFieldLigne);
		panelDim.add(new JLabel("Colonne : "));
		panelDim.add(textFieldColonne);

		panelPoles.add(new JLabel("Nombre de Pôles : "));
		panelPoles.add(textFieldPoles);
		panelDep.add(new JLabel("Nombre de Départements : "));
		panelDep.add(textFieldDep);
		panelTransport.add(new JLabel("Nombre de Transports : "));
		panelTransport.add(textFieldTransport);

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
		// on vérifie que tous les champs remplis sont des int et que les champs ne sont pas vides
		try 
		{
			Integer.parseInt(textFieldLigne.getText());
			Integer.parseInt(textFieldColonne.getText());
			Integer.parseInt(textFieldPoles.getText());
			Integer.parseInt(textFieldDep.getText());
			Integer.parseInt(textFieldTransport.getText());
		} 
		catch (NumberFormatException ex) 
		{
			if (!textFieldLigne.getText().isEmpty() && 
			!textFieldColonne.getText().  isEmpty() && 
			!textFieldPoles.getText().    isEmpty() && 
			!textFieldDep.getText().      isEmpty() && 
			!textFieldTransport.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres entiers valides dans tous les champs.", "Entrée invalide", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs pour continuer.", "Champs manquants", JOptionPane.WARNING_MESSAGE);
			}
			return;
		}
		// si toutes les infos ne sont pas remplis on ne peut passer à la suite
		// sinon on ouvre la frame de création de plateau
		if (e.getSource() == this.buttonValider)
		{
			this.frameMenu.getFrameCreation().setVisible(true);
			this.frameMenu.getFrameConfig().setVisible(false);
			textFieldLigne.setText("");
			textFieldColonne.setText("");
			textFieldPoles.setText("");
			textFieldDep.setText("");
			textFieldTransport.setText("");
		}
	}
>>>>>>> 8d895fd (maj des ActionPerformed et autres):IHM/PanelConfig.java
}