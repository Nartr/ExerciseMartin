package ch.oester.robin.eth;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RollingDice extends JPanel {

  private static final int SPACE = 10;

  private final Dice d1;
  private final Dice d2;

  public RollingDice() {
    Random random = new Random();
    this.d1 = new Dice(random, SPACE, 4 * SPACE);
    this.d2 = new Dice(random,2 * SPACE + Dice.LENGTH, 4 * SPACE);
    JButton button = new JButton("Roll");
    button.addActionListener(e -> {
      d1.setRandomNumber();
      d2.setRandomNumber();
      repaint();
    });
    add(button);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Rolling Dices");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    RollingDice dice = new RollingDice();
    frame.setContentPane(dice);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(3 * SPACE + 2 * Dice.LENGTH, 5 * SPACE + Dice.LENGTH);
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.BLACK);
    d1.paint(g);
    d2.paint(g);
  }

  private static class Dice {

    private static final int LENGTH = 100;
    private static final int QUARTER = LENGTH / 4;
    private static final int POINT_DIAMETER = 20;

    private final Random random;
    private final int x;
    private final int y;
    private int number;

    public Dice(Random random, int x, int y) {
      this.random = random;
      this.x = x;
      this.y = y;
      this.number = 1;
    }

    private void setRandomNumber() {
      number = random.nextInt(6) + 1;
    }

    public void paint(Graphics g) {
      g.drawRect(x, y, LENGTH, LENGTH);
      if(number > 1) {
        drawPoint(x + 3 * QUARTER, y + QUARTER, g);
        drawPoint(x + QUARTER, y + 3 * QUARTER, g);
        if(number > 3) {
          drawPoint(x + QUARTER, y + QUARTER, g);
          drawPoint(x + 3 * QUARTER, y + 3 * QUARTER, g);
        }
      }
      if(number == 6) {
        drawPoint(x + QUARTER, y + 2 * QUARTER, g);
        drawPoint(x + 3 * QUARTER, y + 2 * QUARTER, g);
      }
      if(number % 2 == 1) {
        drawPoint(x + 2 * QUARTER, y + 2 * QUARTER, g);
      }
    }

    private void drawPoint(int x, int y, Graphics g) {
      g.fillOval(x - POINT_DIAMETER / 2, y - POINT_DIAMETER / 2, POINT_DIAMETER, POINT_DIAMETER);
    }
  }
}
