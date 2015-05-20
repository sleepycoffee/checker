package com.sleepycoffee.checker.listener;
import java.util.EventListener;

import com.sleepycoffee.checker.beans.MoveEvent;

/**
 * Defines an object which listens for MoveEvents. This class is used primarily
 * by ui.HumanPlayer.
 * @author David He
 */
public interface MoveListener extends EventListener
{
    /**
     * Invoked when the target of the listener has selected a move.
     * @param e         a MoveEvent object
     */
    void moveSelected(MoveEvent e);
}

