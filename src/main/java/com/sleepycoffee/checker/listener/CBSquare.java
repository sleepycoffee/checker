package com.sleepycoffee.checker.listener;
import static com.sleepycoffee.checker.beans.CheckersConsts.BLK_KING;
import static com.sleepycoffee.checker.beans.CheckersConsts.BLK_PAWN;
import static com.sleepycoffee.checker.beans.CheckersConsts.NEITHER;
import static com.sleepycoffee.checker.beans.CheckersConsts.PIECES_MAX;
import static com.sleepycoffee.checker.beans.CheckersConsts.RED_KING;
import static com.sleepycoffee.checker.beans.CheckersConsts.RED_PAWN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sleepycoffee.checker.ui.CheckersBoardModel;

/**
 * This class implements the view/controller of one of the
 * {@link CheckersBoard CheckersBoard} widget's sixty-four squares.
 * @see CheckersBoard CheckersBoard
 * @author David He
 */
public class CBSquare extends JComponent implements ChangeListener, ActionListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int SQUARE_WIDTH = 50;
    public final static int SQUARE_HEIGHT = 50;

    protected int index;
    protected CheckersBoardModel cbm;
    protected ButtonModel model;

    public CBSquare(CheckersBoardModel cbm, int index)
    {
        this.model = new DefaultButtonModel();

        this.index = index;
        this.cbm = cbm;

        setPreferredSize(new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT));

        loadImages();

        if ( (index + index/8) % 2 == 0 )
            setBackground(Color.WHITE);
        else
            setBackground(Color.LIGHT_GRAY);

        cbm.addChangeListener(this);
        model.addActionListener(this);

        Listener listener = new Listener();
        this.addMouseListener(listener);
        this.addFocusListener(listener);
        //this.addChangeListener(listener);
    }

    protected static int focusBorderLength = 12;
    protected static int focusBorderWidth = 2;
    protected static Polygon focusPoly;
    static
    {
        int a = focusBorderLength;
        int b = focusBorderWidth;
        focusPoly = new Polygon();
        focusPoly.addPoint(0, 0);
        focusPoly.addPoint(0, a);
        focusPoly.addPoint(b, a);
        focusPoly.addPoint(b, b);
        focusPoly.addPoint(a, b);
        focusPoly.addPoint(a, 0);
    }

    public void paintBorder(Graphics _g)
    {
        Graphics2D g = (Graphics2D)_g;

        /* If has focus, paint focus border */
        if (hasFocus())
        {
            g.setColor(Color.BLACK);
            for (int i = 0; i < 4; i++)
            {
                g.draw(focusPoly);
                g.rotate(Math.PI / 2, (getWidth() - 1.0) / 2.0, (getHeight() - 1.0) / 2.0);
            }
        }
    }

    protected final static Color hintValidColor = new Color(0, 255, 0, 128);
    protected final static Color hintInvalidColor = new Color(255, 0, 0, 128);

    public void paintComponent(Graphics _g)
    {
        Graphics2D g = (Graphics2D)_g;

        Rectangle rectAll = new Rectangle(0, 0, getWidth(), getHeight());

        g.setColor(getBackground());
        g.fill(rectAll);

        if (cbm.getHint(index) == CheckersBoardModel.HINT_VALID)
        {
            g.setColor(hintValidColor);
            g.fill(rectAll);
        }

        if (cbm.getHint(index) == CheckersBoardModel.HINT_INVALID)
        {
            g.setColor(hintInvalidColor);
            g.fill(rectAll);
        }

        g.drawImage(images[cbm.getPiece(index)], 0, 0, null);
    }

    public ButtonModel getModel() { return model; }

    public void stateChanged(ChangeEvent e)
    {
        repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (cbm.getEnabled() != NEITHER)
            cbm.squarePressed(index);
    }

    protected static Image[] images;

    protected static boolean imagesLoaded = false;

    /** 
     * Blocks until all images are loaded. 
     * Should be static, but mediatracker doesn't like it.
     */
    public void loadImages()
    {
        synchronized (CBSquare.class)
        {
            if (imagesLoaded)
                return;

            Toolkit tk = Toolkit.getDefaultToolkit();

            images = new Image[PIECES_MAX];
            images[RED_PAWN] = tk.getImage( this.getClass().getResource("images/r_pawn.png") );
            images[BLK_PAWN] = tk.getImage( this.getClass().getResource("images/b_pawn.png") );
            images[RED_KING] = tk.getImage( this.getClass().getResource("images/r_king.png") );
            images[BLK_KING] = tk.getImage( this.getClass().getResource("images/b_king.png") );

            MediaTracker mediaTracker = new MediaTracker(this);
            for (int i : new int[] {RED_PAWN, BLK_PAWN, RED_KING, BLK_KING} )
                mediaTracker.addImage(images[i], 0);

            try {
                mediaTracker.waitForAll();
            } catch ( InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    class Listener implements MouseListener, FocusListener, ChangeListener
    {
        public void stateChanged(ChangeEvent e)
        {
            repaint();
        }

        public void focusGained(FocusEvent e)
        {
            repaint();
        }

        public void focusLost(FocusEvent e)
        {
            model.setArmed(false);
            model.setPressed(false);

            repaint();
        }

        public void mouseClicked(MouseEvent e)
        {
        }

        public void mousePressed(MouseEvent e)
        {
            if ( !contains(e.getX(), e.getY()) )
                return;

            if (!model.isEnabled())
                return;

            if ( SwingUtilities.isLeftMouseButton(e) )
            {
                model.setArmed(true);
                model.setPressed(true);
            }
        }

        public void mouseReleased(MouseEvent e)
        {
            if ( SwingUtilities.isLeftMouseButton(e) )
            {
                model.setPressed(false);
                model.setArmed(false);
            }
        }

        public void mouseEntered(MouseEvent e)
        {
            if (!hasFocus())
                requestFocusInWindow();

            //model.setRollover(true);
            if (model.isPressed())
                model.setArmed(true);
        }

        public void mouseExited(MouseEvent e)
        {
            //model.setRollover(false);
            model.setArmed(false);
        }
    }
}
