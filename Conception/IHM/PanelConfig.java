
package IHM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import ControleurConception;

public class PanelConfig extends JPanel implements ActionListener
{
	//ControleurConception ctrl;
	FrameMenu frameMenu;
	private JButton buttonValider;
	private JTextField textFieldNom = new JTextField("NouveauPlateau", 20);
	private JTextField textFieldLigne = new JTextField( 10);
	private JTextField textFieldColonne = new JTextField( 10);
	private JTextField textFieldPoles = new JTextField( 10);
	private JTextField textFieldDep = new JTextField( 10);
	private JTextField textFieldTransport = new JTextField( 10);

	private String nomPlateau;
	private int    lig, col, poles, dep, transport;
	public PanelConfig(FrameMenu frameMenu)
	{
		this.frameMenu = frameMenu;
		this.setLayout(new GridLayout(6, 1));
		
		JPanel panelNomPla     = new JPanel();
		JPanel panelDim        = new JPanel();
		JPanel panelPoles      = new JPanel();
		JPanel panelDep        = new JPanel();
		JPanel panelTransport  = new JPanel();

		textFieldNom.addActionListener(this);

		panelNomPla.add(new JLabel("Nom du Plateau : "));
		panelNomPla.add(textFieldNom);

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

		this.add(panelNomPla);
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
		nomPlateau = textFieldNom.getText();
		// on vérifie que tous les champs remplis sont des int et que les champs ne sont pas vides
		try 
		{
			lig = Integer.parseInt(textFieldLigne.getText());
			col = Integer.parseInt(textFieldColonne.getText());
			poles = Integer.parseInt(textFieldPoles.getText());
			dep = Integer.parseInt(textFieldDep.getText());
			transport = Integer.parseInt(textFieldTransport.getText());
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
			((FrameCreation) this.frameMenu.getFrameCreation()).setNomPlateauConfig(this.nomPlateau);
			this.frameMenu.getFrameCreation().setVisible(true);
			this.frameMenu.getFrameConfig().setVisible(false);


			textFieldLigne.     setText("");
			textFieldColonne.   setText("");
			textFieldPoles.     setText("");
			textFieldDep.       setText("");
			textFieldTransport. setText("");
		}
	}

	public void setPlateau(String nomPlateau, int lig, int col, int nbPoles, int nbDep, int nbManches)
	{
		//ctrl.setPlateau(nomPlateau, lig, col, nbPoles, nbDep, nbManches);
	}
}

