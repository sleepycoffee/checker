package com.sleepycoffee.checker.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs540.checkers.CheckersController;
import cs540.checkers.CheckersModel;

/**
 * This controller extends {@link CheckersController CheckersController} by 
 * allowing a mouse click to override stalls in {@link #loop loop}.
 * @author David He
 */
public class CheckersUIController extends CheckersController implements ActionListener
{
    public CheckersUIController(CheckersModel model)
    {
        super(model);
    }

    public CheckersUIController(CheckersModel model, long[] turnLimit, 
            boolean[] moveOnClick)
    {
        super(model, turnLimit, moveOnClick);
    }

    public void actionPerformed(ActionEvent e)
    {
        /* Mouse clocked */
        if (e.getSource() instanceof CheckersBoardModel)
            loop(true);
    }
}
