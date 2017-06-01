package machine_a_glace;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameController implements MouseListener, MouseMotionListener, KeyListener {

  static final boolean echo = false;
  Game m_game;
  GameModel m_model;

  GameController(Game game, GameModel model) {
    m_game = game;
    m_model = model;
  }

  /**
   * Simulation step. Warning: the model has already executed its step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  void step(long now) {
  }

  private static final long NOPS=1000000L;
  @Override
  public void keyTyped(KeyEvent e) {
    if (echo)
      System.out.println("KeyTyped: " + e);
    if (e.getKeyChar() == ' ') {
      try {
        /*
         * NEVER, EVER, DO THIS!
         * NEVER BLOCK OR SLEEP,
         * YOU WILL BLOCK EVERYTHING.
         */
        System.out.println("ZZzzz....");
        Thread.sleep(5000);
        System.out.println("Hey! I am back");
      } catch (InterruptedException ex) {
      }
    } else if (e.getKeyChar() == '+') {
      m_model.count +=NOPS; 
      System.out.println(" count="+m_model.count);
    } else if (e.getKeyChar() == '-') {
      m_model.count -=NOPS; 
      m_model.count = Math.abs(m_model.count);
      System.out.println(" count="+m_model.count);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (echo)
      System.out.println("KeyPressed: " + e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (echo)
      System.out.println("KeyReleased: " + e);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (echo)
      System.out.println("MouseClicked: " + e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (echo)
      System.out.println("MousePressed: " + e);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (echo)
      System.out.println("MouseReleasedt: " + e);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (echo)
      System.out.println("MouseEntered: " + e);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (echo)
      System.out.println("MouseEvent: " + e);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (echo)
      System.out.println("MouseDragged: " + e);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (echo)
      System.out.println("MouseMoved: " + e);
  }

}
