package com.sleepycoffee.checker.beans;

import java.util.EventObject;

import com.sleepycoffee.checker.Move;

/**
 * MoveEvent is used to notify interested parties that a move has been selected.
 * @author David He
 */
public class MoveEvent extends EventObject
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Move move;

    /**
     * Constructs a MoveEvent object with the selected move.
     * @param move the selected move
     */
    public MoveEvent(Object source, Move move)
    {
        super(source);
        this.move = move;
    }

    /**
     * Gets the selected move.
     * @return the selected move
     */
    public Move getMove()
    {
        return move;
    }
}
