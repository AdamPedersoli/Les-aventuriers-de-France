package Conception.IHM;

import Conception.Metier.TypePole;

import javax.swing.*;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;

public class PoleSourceHandler extends TransferHandler
{

    private final TypePole pole;

    public PoleSourceHandler(TypePole pole)
	{
        this.pole = pole;
    }

    public int getSourceActions(JComponent c)
	{
        return TransferHandler.COPY;
    }

    protected Transferable createTransferable(JComponent c)
	{
        return new PoleTransferable(this.pole);
    }
}