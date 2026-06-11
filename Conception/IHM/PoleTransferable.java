package Conception.IHM;

import Conception.Metier.TypePole;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

public class PoleTransferable implements Transferable
{

    public static final DataFlavor POLE_FLAVOR = new DataFlavor(TypePole.class, "TypePole");

    private final TypePole pole;

    public PoleTransferable(TypePole pole)
	{
       this.pole = pole;
    }

    public DataFlavor[] getTransferDataFlavors()
	{
        return new DataFlavor[]{ POLE_FLAVOR };
    }

    
    public boolean isDataFlavorSupported(DataFlavor flavor)
	{
        return flavor.equals(POLE_FLAVOR);
    }

    
    public Object getTransferData(DataFlavor flavor)
	{
        return this.pole;
    }
}