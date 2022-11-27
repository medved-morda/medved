package self.snake


import javax.swing.JPanel
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class SnakeConstructor extends JPanel implements ActionListener{
    public static LinkedList<Point> snake = new LinkedList<Point>()
    public static Point apple = new Point()
    private static String direction
    public static int appleEaten

    private static boolean isFail = false

    private static Timer timer

    static random = new Random()

    SnakeConstructor(int size) {

        this.setPreferredSize(new Dimension(Const.CELL_AMT*Const.CELL_SIZE, Const.CELL_AMT*Const.CELL_SIZE))
        this.addKeyListener(new MyKeyAdapter())

        direction = ["up", "down", "left", "right"].get(random.nextInt(4))

        snake = []
        Point head = new Point(randomPoint())
        snake.add(head)

        println(String.format("snake head = %s", snake[0]))
        println(String.format("snake direction = %s", direction))
        for (int i = 1 ; i < size; i++){
            int x = (int)snake[0].getX() + i*Const.CELL_SIZE  * ((direction.equals("up") || direction.equals("down")) ? 0 : (direction.equals("left") ? 1 : -1))
            int y = (int)snake[0].getY() + i*Const.CELL_SIZE  * ((direction.equals("left") || direction.equals("right")) ? 0 : (direction.equals("down") ? -1 : 1))
            snake.add(new Point(x,y))
        }
        println(snake)

        newApple()
    }

    public static void setDirection(String direction) {
        this.direction = direction
    }

    public static void newApple(){
        apple = randomPoint()
        while ( snake.contains(apple) ){
            apple = randomPoint()
        }
    }

    void checkApple(Point head, Point apple){
        if(head == apple){
            int newX = (int)snake[0].getX() + Const.CELL_SIZE  * ((direction.equals("up") || direction.equals("down")) ? 0 : (direction.equals("left") ? -1 : 1))
            int newY = (int)snake[0].getY() + i*Const.CELL_SIZE  * ((direction.equals("left") || direction.equals("right")) ? 0 : (direction.equals("down") ? 1 : -1))
            snake.addFirst(new Point(newX, newY))
            apple = newApple()
        }

    }

    public static void startGame(){
        isFail = false
        timer = new Timer(Const.DELAY, this)
        move()
        checkApple(snake[0], apple)
        repaint()

    }

    public static void move(){
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
    }

    public void paint(Graphics g) {
        draw(g)
    }

    static Point randomPoint(){
        int x,y
        x = (random.nextInt(Const.CELL_AMT * Const.CELL_SIZE - Const.CELL_SIZE * Const.SNAKE_INIT_LENGTH)).intdiv(Const.CELL_SIZE) * Const.CELL_SIZE
        y = (random.nextInt(Const.CELL_AMT * Const.CELL_SIZE - Const.CELL_SIZE * Const.SNAKE_INIT_LENGTH)).intdiv(Const.CELL_SIZE) * Const.CELL_SIZE

        return new Point(x,y)
    }

    public void draw(Graphics g){
        // paint initial field
        for (int x = 0; x < Const.CELL_AMT * Const.CELL_SIZE; x += Const.CELL_SIZE) {
            for (int y = 0; y < Const.CELL_AMT * Const.CELL_SIZE; y += Const.CELL_SIZE) {
                g.drawRect(x, y, Const.CELL_SIZE, Const.CELL_SIZE)
            }
        }
        // paint initial snake
        g.setColor(Color.RED)
        for (Point p : snake) {
            g.fillArc((int) p.getX(), (int) p.getY(), Const.CELL_SIZE, Const.CELL_SIZE, 0, 360)
            g.setColor(Color.BLUE)
        }
        // paint apple
        g.setColor(Color.GREEN)
        g.fillRect((int)apple.getX(), (int)apple.getY(), Const.CELL_SIZE, Const.CELL_SIZE)
    }



    class MyKeyAdapter extends KeyAdapter {
        @Override
        void keyPressed(KeyEvent e) {
            int key = e.getKeyCode()
            switch(key){
                case KeyEvent.VK_LEFT:
                    direction = (direction == "right" ? "right" : "left")
                    break
                case KeyEvent.VK_RIGHT:
                    direction = (direction == "left" ? "left" : "right")
                    break
                case KeyEvent.VK_UP:
                    direction = (direction == "down" ? "down" : "up")
                    break
                case KeyEvent.VK_DOWN:
                    direction = (direction == "up" ? "up" : "down")
                    break
            }

        }
    }

    @Override
    void actionPerformed(ActionEvent e) {

    }
}
