package self.snake


import javax.swing.JFrame
import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class GameFrame extends JFrame {
//    Logger LOG = new Logger(this.getClass().getName())

    static SnakeConstructor sn

    public GameFrame() {
        super("Snake")

        sn = new SnakeConstructor(Const.SNAKE_INIT_LENGTH)

        setDefaultCloseOperation(EXIT_ON_CLOSE)

        getContentPane().add(sn);
        pack()
        setVisible(true)
    }

}
