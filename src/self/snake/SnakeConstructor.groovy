package self.snake

import javax.swing.JComponent
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class SnakeConstructor extends JComponent{
    public static ArrayList<Point> snake = new ArrayList<Point>()
    public static String direction
    public static int appleEaten

    private int bodySize


    SnakeConstructor(int x0, int y0, int size) {
        snake = []
        this.bodySize = size
        Point h = new Point()
        h.setLocation(x0, y0)
        snake.add(h)
        println(String.format("snake head = %s", snake[0]))
        direction = ["up", "down", "left", "right"].get(new Random().nextInt(4))
        println(String.format("snake direction = %s", direction))
        for (int i = 1 ; i < size; i++){
            int x = (int)snake[0].getX() + i*Const.CELL_SIZE  * ((direction.equals("up") || direction.equals("down")) ? 0 : (direction.equals("left") ? 1 : -1))
            int y = (int)snake[0].getY() + i*Const.CELL_SIZE * ((direction.equals("left") || direction.equals("right")) ? 0 : (direction.equals("down") ? -1 : 1))
            snake.add(new Point(x,y))
        }
        println(snake)
    }

    static void setDirection(String direction) {
        this.direction = direction
    }

    public void move(){
        int dx, dy
        switch( direction ) {
            case "up":
                dx = 0
                dy = -1
                break
            case "down":
                dx = 0
                dy = 1
                break
            case "left":
                dx = -1
                dy = 0
                break
            case "right":
                dx = 1
                dy = 0
                break
        }
        dx *= Const.CELL_SIZE
        dy *= Const.CELL_SIZE
        for (int i = snake.size()-1; i > 0 ; i--){
            snake[i] = snake[i-1]
        }
        snake[0].setLocation((int) snake[0].getX()+dx, (int)snake[0].getY()+dy)
        println(snake)
        println(snake.size())


    }

}
