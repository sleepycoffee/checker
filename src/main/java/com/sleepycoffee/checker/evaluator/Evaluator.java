package com.sleepycoffee.checker.evaluator;

/**
 * An interface for static board evaluators. Users who extend this framework 
 * are encouraged, but not required to use this interface.
 */
public interface Evaluator 
{
    /**
     * Evaluates the specified board state from RED's position.
     * @param bs    the board state to evaluate
     * @return      the value of this board state
     */
    public int evaluate(int[] bs);
}
