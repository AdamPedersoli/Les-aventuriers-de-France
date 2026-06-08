
package Conception.IHM;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Conception.ControleurConception;
import Conception.Metier.TypePole;

import java.util.ArrayList;

public class PanelConfig extends JPanel implements ActionListener
{
	private ControleurConception ctrl;
	private JButton buttonValider;
	private JTextField textFieldNom       = new JTextField("NouveauPlateau", 20);
	private JTextField textFieldLigne     = new JTextField(10);
	private JTextField textFieldColonne   = new JTextField(10);
	private JCheckBox[] tabCbPoles;
	private JTextField textFieldDep       = new JTextField(10);
	private JTextField textFieldTransport = new JTextField(10);
	private ArrayList<Integer> lstPoleUtilise;

	private String nomPlateau;
	private int    lig, col, nbPoleDiff, dep, transport;
	
	public PanelConfig( ControleurConception ctrl )
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(6, 1));
		
		this.tabCbPoles = new JCheckBox[7];
		this.lstPoleUtilise = new ArrayList<Integer>();
		
		for ( int cpt = 0 ; cpt < this.tabCbPoles.length ; cpt++ )
			this.tabCbPoles[cpt] = new JCheckBox();
		
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

		for ( int cpt = 0 ; cpt < this.tabCbPoles.length ; cpt++ )
		{
			panelPoles.add( new JLabel( TypePole.values()[cpt].getNom() ) );
			panelPoles.add( this.tabCbPoles[cpt] );
		}
		
		
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

			this.nbPoleDiff = 0;
			for ( int cpt = 0 ; cpt < this.tabCbPoles.length ; cpt++ )
			{
				if ( this.tabCbPoles[cpt].isSelected() )
				{
					this.nbPoleDiff++;
					this.lstPoleUtilise.add( TypePole.values()[cpt].ordinal() );
				}
			}
			if ( this.nbPoleDiff == 0 )
				this.tabCbPoles[this.tabCbPoles.length + 1] = this.tabCbPoles[0];

			dep = Integer.parseInt(textFieldDep.getText());
			transport = Integer.parseInt(textFieldTransport.getText());
		} 

		catch (NumberFormatException ex) 
		{
			if (!textFieldLigne.getText().isEmpty() && 
			!textFieldColonne.getText().  isEmpty() && 
			 this.nbPoleDiff == 0 && 
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
		this.ctrl.setPlateau(nomPlateau, lig, col, dep, nbPoleDiff, transport);
		( new FrameCreation( this.ctrl, this.lstPoleUtilise ) ).setVisible(true);


		textFieldLigne.     setText("");
		textFieldColonne.   setText("");
		
		for ( JCheckBox cb : tabCbPoles )
			cb.setSelected(false);
		
		textFieldDep.       setText("");
		textFieldTransport. setText("");
	}
}

