package machine_a_glace;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Square {

  Color m_color;
  int m_x, m_y;
  int m_size;
  int m_red, m_green, m_blue;
  int m_dsize;
  long m_lastResize;
  long m_lastMove;

  Square(int x, int y) {
    m_x = x;
    m_y = y;
    m_dsize = -20;
    Random rand = new Random();
    m_red = rand.nextInt(255);
    m_green = rand.nextInt(255);
    m_blue = rand.nextInt(255);
    m_color = new Color(m_red, m_green, m_blue);
  }

  /**
   * Simulation step.
   * @param now is the current time in milliseconds.
   */
  void step(long now) {
    long elapsed = now - m_lastMove;
    if (elapsed > 50L) {
      m_lastMove = now;
      if (m_dsize>0) {
        m_x += 1;
        m_y += 1;
      } else {
        m_x -= 1;
        m_y -= 1;
      }
    }
    elapsed = now - m_lastResize;
    if (elapsed > 2000L) {
      m_lastResize = now;
      if (m_size == 0 || m_size == 200) {
        m_dsize = -m_dsize;
      }
      m_size += m_dsize;
    }
  }

  /**
   * paints this square on the screen.
   * @param g
   */
  void paint(Graphics g) {
    if (true) {
      int dc = 2;
      m_red = (m_red + dc) % 255;
      m_green = (m_green + dc) % 255;
      m_blue = (m_blue + dc) % 255;
      m_color = new Color(m_red, m_green, m_blue);
    }
    g.setColor(m_color);
    g.drawRect(m_x, m_y, m_size, m_size);
  }

}
