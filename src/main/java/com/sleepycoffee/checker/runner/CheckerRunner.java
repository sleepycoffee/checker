package com.sleepycoffee.checker.runner;

import org.apache.log4j.Logger;

import com.sleepycoffee.checker.Checker;
import com.sleepycoffee.checker.CheckerImpl;

public class CheckerRunner {

	/** log4j instance. */
	protected static final Logger LOGGER = Logger.getLogger(CheckerRunner.class);	
	
	private static String help_str = 
            "Usage: java -jar Checkers.jar <red_player> <blk_player> [OPTION]...\n" +
            "Starts a checker game with <red_player> and <blk_player> specifying the\n" +
            "abbreviated class names of the red and black checkers players, respectively.\n"+

            "Optional parameters include:\n" +
            "--turntime <turnLimit>      Sets how long computer players are allowed to think. (milliseconds)\n" + 
            "--step                      Require a mouse click before the start of each turn.\n" +
            "--verbose                   Print output loquaciously.\n" +
            "--disable-turn-delay        Disable the READY -> WAITING turn delay.\n" +
            "--initbs <filename>         Read the initial board state from <filename>.\n" +
            "--initside <side>           Sets side to be the first to move.\n" +
            "--depthlimit <redDepthLimit> <blkDephLimit>\n" +
            "                            Sets the maximum iterative depth of iterative deepening for each player\n" +
            "--nogui                     Do not launch a GUI\n" +
            "--logfile                   Append to the specified log file\n" +
            //"--help: Show this message.\n" +
            "";
	
	 /**
     * This is the main method where the program will begin execution.
     */
    public static void main(String[] args) {
    	
        /* Get default options */
        Checker checker = new CheckerImpl();

        /* Override default options by parsing command line arguments */
        try {
        	
        	checker.parseOptions(args);
            
        } catch (IllegalArgumentException e) {
        	LOGGER.error(help_str);
        } finally {
        	
        }

        /* Go! */
        checker.init();
    }
    
}
