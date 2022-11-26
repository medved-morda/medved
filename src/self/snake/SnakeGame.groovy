package self.snake

import org.apache.tools.ant.taskdefs.optional.ejb.JbossDeploymentTool

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.logging.Logger

class SnakeGame extends JFrame implements KeyListener{
    Logger LOG = new Logger(this.getClass().getName())

    public static  boolean isFail = false
    static SnakeConstructor sn

    static JPanel snPanel = new JPanel() {
        @Override
        void paintComponent(Graphics g) {
            // paint initial field
            for (int x = 0; x < Const.CELL_AMT * Const.CELL_SIZE; x += Const.CELL_SIZE) {
                for (int y = 0; y < Const.CELL_AMT * Const.CELL_SIZE; y += Const.CELL_SIZE) {
                    g.drawRect(x, y, Const.CELL_SIZE, Const.CELL_SIZE)
                }
            }
            // paint initial snake
            g.setColor(Color.RED)
            for (Point p : sn.snake) {
                g.fillArc((int) p.getX(), (int) p.getY(), Const.CELL_SIZE, Const.CELL_SIZE, 0, 360)
                g.setColor(Color.BLUE)

            }
        }
    }

    public static void main(String[] args){
        SnakeGame game = new SnakeGame()
        while ( !isFail ){
            sn.move()
            sleep(500)
            snPanel.revalidate()
            snPanel.repaint()
        }

    }

    public SnakeGame() {
        super("Snake")

        int x0 = (new Random().nextInt(Const.CELL_AMT * Const.CELL_SIZE - Const.CELL_SIZE * Const.SNAKE_INIT_LENGTH)).intdiv(Const.CELL_SIZE) * Const.CELL_SIZE
        int y0 = (new Random().nextInt(Const.CELL_AMT * Const.CELL_SIZE - Const.CELL_SIZE * Const.SNAKE_INIT_LENGTH)).intdiv(Const.CELL_SIZE) * Const.CELL_SIZE

        sn = new SnakeConstructor(x0, y0, Const.SNAKE_INIT_LENGTH)

        snPanel.setBackground(Color.LIGHT_GRAY)
        snPanel.add(sn)

        setDefaultCloseOperation(EXIT_ON_CLOSE)

        Dimension d = new Dimension(Const.CELL_AMT * Const.CELL_SIZE + 20, Const.CELL_AMT * Const.CELL_SIZE + 50)
        setMinimumSize(d)
        setMaximumSize(d)
        addKeyListener(this)
        println(getSize())
        getContentPane().add(snPanel);
        pack()
        setVisible(true)
    }

    @Override
    void keyTyped(KeyEvent e) {
        int key = e.getKeyCode()
        switch(key){
            case KeyEvent.VK_LEFT:
                sn.direction = (sn.direction == "right" ? "right" : "left")
                break
            case KeyEvent.VK_RIGHT:
                sn.direction = (sn.direction == "left" ? "left" : "right")
                break
            case KeyEvent.VK_UP:
                sn.direction = (sn.direction == "down" ? "down" : "up")
                break
            case KeyEvent.VK_DOWN:
                sn.direction = (sn.direction == "up" ? "up" : "down")
                break
        }
    }

    @Override
    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode()
        switch(key){
            case KeyEvent.VK_LEFT:
                sn.direction = (sn.direction == "right" ? "right" : "left")
                break
            case KeyEvent.VK_RIGHT:
                sn.direction = (sn.direction == "left" ? "left" : "right")
                break
            case KeyEvent.VK_UP:
                sn.direction = (sn.direction == "down" ? "down" : "up")
                break
            case KeyEvent.VK_DOWN:
                sn.direction = (sn.direction == "up" ? "up" : "down")
                break
        }

    }

    @Override
    void keyReleased(KeyEvent e) {

    }

}
