package machine_a_glace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

public class GameView extends JPanel {

  private static final long serialVersionUID = 1L;

  Game m_game;
  GameModel m_model;
  Color m_background = Color.gray;
  long m_last;
  int m_npaints;
  int m_fps;

  GameView(Game game, GameModel model, GameController ctr) {
    super(true);
    m_game = game;
    m_model = model;
    
    // let's hook our controller, so it gets mouse events
    // and keyboard events.
    addKeyListener(ctr);
    addMouseListener(ctr);
    addMouseMotionListener(ctr);

    // grab the focus on this JPanel, meaning keyboard events
    // are coming to our controller. Indeed, the focus controls
    // which part of the overall GUI receives the keyboard events.
    setFocusable(true);
    requestFocusInWindow();
    grabFocus();
  }
  
  private void computeFPS() {
    long now = System.currentTimeMillis();
    if (now - m_last > 1000L) {
      m_fps = m_npaints;
      m_last = now;
      m_npaints = 0;
    }
    m_game.setFPS(m_fps, "npaints="+m_npaints);
    // System.out.println("npaints="+m_npaints);
    m_npaints++;
  }

  @Override
  public void paint(Graphics g) {
    computeFPS();
    
    // erase background
    g.setColor(m_background);
    g.fillRect(0, 0, getWidth(), getHeight());

    // Paint our model, grabbing the elements,
    // in our case, the squares.
    Iterator<Square> iter = m_model.squares();
    while (iter.hasNext()) {
      Square s = iter.next();
      s.paint(g);
    }
  }
}
