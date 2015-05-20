package com.sleepycoffee.checker.evaluator;
import static com.sleepycoffee.checker.beans.CheckersConsts.BLK;
import static com.sleepycoffee.checker.beans.CheckersConsts.BLK_KING;
import static com.sleepycoffee.checker.beans.CheckersConsts.BLK_PAWN;
import static com.sleepycoffee.checker.beans.CheckersConsts.H;
import static com.sleepycoffee.checker.beans.CheckersConsts.RED;
import static com.sleepycoffee.checker.beans.CheckersConsts.RED_KING;
import static com.sleepycoffee.checker.beans.CheckersConsts.RED_PAWN;
import static com.sleepycoffee.checker.beans.CheckersConsts.W;

/**
 * This simplistic static board evaluator assigns points for material.  Each 
 * pawn remaining on the board contributes one point, and each remaining king 
 * remaining on the board contributes two points. 
 */
public class SimpleEvaluator implements Evaluator
{
    public int evaluate(int[] bs)
    {
        int[] pawns = new int[2],
            kings = new int[2] ;

        for (int i = 0; i < H * W; i++)
        {
            int v = bs[i];
            switch(v)
            {
                case RED_PAWN:
                case BLK_PAWN:
                    pawns[v % 4] += 1;
                    break;
                case RED_KING:
                case BLK_KING:
                    kings[v % 4] += 1;
                    break;
            }
        }

        return 1 * (pawns[RED] - pawns[BLK]) + 
               3 * (kings[RED] - kings[BLK]);
    }
}
