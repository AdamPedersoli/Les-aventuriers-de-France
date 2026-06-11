package Conception.IHM;

import Conception.ControleurConception;
import Conception.Metier.TypePole;

import javax.swing.*;



public class PoleCibleHandler extends TransferHandler
{
	
    private LabelPole lblPole;
	
    public PoleCibleHandler( LabelPole lblPole)
	{
        this.lblPole = lblPole;
    }

    public boolean canImport(TransferSupport support)
	{
        return support.isDataFlavorSupported(PoleTransferable.POLE_FLAVOR);
    }

    public boolean importData(TransferSupport support)
	{
		try
		{
            TypePole pole = (TypePole) support.getTransferable().getTransferData(PoleTransferable.POLE_FLAVOR);
			
			this.lblPole.setPole(pole);
			
            return true;
        }
		catch (Exception e)
		{
            return false;
        }
    }
	
}