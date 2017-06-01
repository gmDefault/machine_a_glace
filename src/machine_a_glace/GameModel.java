package machine_a_glace;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class GameModel {

  Game m_game;
  LinkedList<Square> m_squares;

  GameModel(Game game) {
    m_game = game;
    Random rand = new Random();
    m_squares = new LinkedList<Square>();
    for (int i = 0; i < 3; i++)
      m_squares.add(new Square(rand.nextInt(100), rand.nextInt(100)));
  }
  
  Iterator<Square> squares() {
    return m_squares.iterator();
  }
  
  long count=0;
  long sum = 0;
  private long op(long i) {
    return i + i*i;
  }
  private void overhead() {
    // *** WARNING *** WARNING *** WARNING *** WARNING  
    // long callbacks will kill your frame-per-second performance
    // the game will get sluggish...
    // avoid as much as possible.
    for (long i=0;i<count;i++) 
      sum += op(i);
  }
  /**
   * Simulation step.
   * @param now is the current time in milliseconds.
   */
  void step(long now) {
    overhead();
    Iterator<Square> iter = m_squares.iterator();
    while (iter.hasNext()) {
      Square s = iter.next();
      s.step(now);
    }

  }
}
