package run;

import raster.BufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/// the most brainless I/O abstraction for a simple single-window app.
public abstract class Applet extends JFrame
{
    /// the image that will be displayed to the screen upon this window's <code>repaint();</code> call.
    protected final raster.BufferedImage surface;

    protected Applet(int width, int height)
    {
        surface = new BufferedImage(width, height);

        setSize(width, height);
        setContentPane(createContentPanel(width, height));

        addKeyListener(createKeyListener());

        pack();
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /// creates the main content panel for this window.
    ///
    /// it is responsible for displaying the <code>this.surface</code>.
    ///
    /// all the mouse input events also get called through it,
    /// so that the mouse pointer is properly positioned relative to the surface.
    private JPanel createContentPanel(int width, int height) {
        return new JPanel() {
            {
                setPreferredSize(new Dimension(width, height));
                addMouseListener(createMouseListener());
                addMouseMotionListener(createMouseMotionListener());
            }

            @Override
            public void paint(Graphics g) {
                g.drawImage(surface, 0, 0, null);
            }
        };
    }

    /// creates the listener which invokes the mousePressed and mouseReleased methods of this Applet.
    private MouseListener createMouseListener() {
        return new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) {
                Applet.this.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Applet.this.mouseReleased(e);
            }
        };
    }

    /// creates the listener which invokes the mouseMoved and mouseDragged methods of this Applet.
    private MouseMotionListener createMouseMotionListener() {
        return new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e) {
                Applet.this.mouseMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Applet.this.mouseDragged(e);
            }
        };
    }

    /// creates the listener which invokes the keyPressed and keyReleased methods of this Applet.
    private KeyListener createKeyListener() {
        return new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e) {
                Applet.this.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Applet.this.keyReleased(e);
            }
        };
    }

    /// the virtual method that gets called each time the mouse is held down inside this window.
    protected void mousePressed(MouseEvent e) { }

    /// the virtual method that gets called each time the mouse is released after being pressed this window.
    protected void mouseReleased(MouseEvent e) { }

    /// the virtual method that gets caleld each time the mouse is moved within this window without being held down.
    protected void mouseMoved(MouseEvent e) { }

    /// the virtual method that gets called each time the mouse is moved while being held down after the press on this window.
    protected void mouseDragged(MouseEvent e) { }

    /// the virtual method that gets called each time a key gets typed.
    protected void keyPressed(KeyEvent e) { }

    /// the virtual method that gets called each time a key is released.
    protected void keyReleased(KeyEvent e) { }
}
