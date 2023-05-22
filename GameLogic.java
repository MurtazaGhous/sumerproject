import java.awt.event.KeyEvent;

public class GameLogic {

    private GameArena arena;
    private Ball paddle1;
    private Ball paddle2;
    private Ball puck;
    private Table table;
    double friction = 1;
    double changepuckX = 0;
    double changepuckY = 0;
    double puckspeed = 15;
    private int score1 = 0;
    private int score2 = 0;
    private Text score1Display;
    private Text score2Display;
    private double initialPaddle1X;
    private double initialPaddle1Y;
    private double initialPaddle2X;
    private double initialPaddle2Y;

    // Text
    private Text gameResult;
    private Text restartText;
    private Text mutetText;

    private boolean gameRestart = false;
    private boolean isMuted = false;

    // boolean variables for paddle movement
    private static boolean paddle1Up = false;
    private static boolean paddle1Down = false;
    private static boolean paddle1Left = false;
    private static boolean paddle1Right = false;
    private static boolean paddle2Up = false;
    private static boolean paddle2Down = false;
    private static boolean paddle2Left = false;
    private static boolean paddle2Right = false;

    // sound
    private SoundManager SoundManager = new SoundManager();

    public GameLogic(GameArena arena, Table table) {
        // sound
        initializeSound();
        this.arena = arena;
        this.table = table;
        initializePaddlesandPuck();
        // score
        initializeScores();

    }

    private void initializeScores() {
        score1Display = new Text("Player 1 Score: " + score1, 20, 100, 70, "WHITE");
        score2Display = new Text("Player 2 Score: " + score2, 20, 700, 70, "WHITE");

        gameResult = new Text("", 35, 370, 330, "BLUE");
        restartText = new Text("", 25, 350, 360, "RED");
        mutetText = new Text("To mute/unmute sound press M", 20, 350, 650, "RED");
        arena.addText(gameResult);
        arena.addText(restartText);
        arena.addText(score1Display);
        arena.addText(score2Display);
        arena.addText(mutetText);
    }

    private void initializeSound() {
        SoundManager.preloadSound("hit.wav");
        SoundManager.preloadSound("bounce.wav");
        SoundManager.preloadSound("applause.wav");
        SoundManager.preloadSound("drumroll.wav");
        SoundManager.preloadSound("fanfare.wav");
    }

    private void initializePaddlesandPuck() {

        int paddleRadius = 50;
        int paddleDistanceFromCenter = (arena.getArenaWidth() / 2) - paddleRadius;
        // Calculate the positions of the paddles
        int paddle1X = arena.getArenaWidth() / 2 - paddleDistanceFromCenter / 2;
        int paddle2X = arena.getArenaWidth() / 2 + paddleDistanceFromCenter / 2;
        // paddles
        paddle1 = new Ball(paddle1X, arena.getArenaHeight() / 2, paddleRadius, "GREEN");
        paddle2 = new Ball(paddle2X, arena.getArenaHeight() / 2, paddleRadius, "GREEN");
        arena.addBall(paddle1);
        arena.addBall(paddle2);

        initialPaddle1X = paddle1.getXPosition();
        initialPaddle1Y = paddle1.getYPosition();
        initialPaddle2X = paddle2.getXPosition();
        initialPaddle2Y = paddle2.getYPosition();

        // puck
        puck = new Ball(table.getTableCenterX(), table.getTableCenterY(), 20, "RED");
        arena.addBall(puck);

    }

    public void updatePaddles() {
        // control paddle movement
        arena.getPanel().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                // key pressed events
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_W:
                        paddle1Up = true;
                        break;
                    case KeyEvent.VK_S:
                        paddle1Down = true;
                        break;
                    case KeyEvent.VK_A:
                        paddle1Left = true;
                        break;
                    case KeyEvent.VK_D:
                        paddle1Right = true;
                        break;
                    case KeyEvent.VK_UP:
                        paddle2Up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        paddle2Down = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        paddle2Left = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        paddle2Right = true;
                        break;
                    case KeyEvent.VK_Y:
                        gameRestart = true;
                        break;
                    case KeyEvent.VK_M:
                        isMuted = !isMuted;
                        if (isMuted) {
                            SoundManager.mute();
                        } else {
                            SoundManager.unmute();
                        }
                        break;

                }
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                // key released events
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_W:
                        paddle1Up = false;
                        break;
                    case KeyEvent.VK_S:
                        paddle1Down = false;
                        break;
                    case KeyEvent.VK_A:
                        paddle1Left = false;
                        break;
                    case KeyEvent.VK_D:
                        paddle1Right = false;
                        break;
                    case KeyEvent.VK_UP:
                        paddle2Up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        paddle2Down = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        paddle2Left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        paddle2Right = false;
                        break;
                    case KeyEvent.VK_Y:
                        gameRestart = false;
                        break;
                }
            }
        });

        // Set focus to the GameArena panel
        arena.getPanel().setFocusable(true);
        arena.getPanel().requestFocus();
    }

    public void startGameLoop() {
        // Play the start music
        if (!isMuted) {
            SoundManager.playSound("fanfare.wav");
        }
        while (true) {
            friction = friction * 0.60;
            // Update paddle positions based on movement variables
            if (paddle1Up && paddle1.getYPosition() - paddle1.getSize() / 2 > table.getTableCenterY()
                    - table.getTableHeight() / 2) {
                paddle1.setYPosition(paddle1.getYPosition() - 6);
            }
            if (paddle1Down && paddle1.getYPosition() + paddle1.getSize() / 2 < table.getTableCenterY()
                    + table.getTableHeight() / 2) {
                paddle1.setYPosition(paddle1.getYPosition() + 6);
            }
            if (paddle1Left && paddle1.getXPosition() - paddle1.getSize() / 2 > table.getTableCenterX()
                    - table.getTableWidth() / 2) {
                paddle1.setXPosition(paddle1.getXPosition() - 6);
            }
            if (paddle1Right && paddle1.getXPosition() + paddle1.getSize() / 2 < table.getTableCenterX()) {
                paddle1.setXPosition(paddle1.getXPosition() + 6);
            }
            if (paddle2Up && paddle2.getYPosition() - paddle2.getSize() / 2 > table.getTableCenterY()
                    - table.getTableHeight() / 2) {
                paddle2.setYPosition(paddle2.getYPosition() - 6);
            }
            if (paddle2Down && paddle2.getYPosition() + paddle2.getSize() / 2 < table.getTableCenterY()
                    + table.getTableHeight() / 2) {
                paddle2.setYPosition(paddle2.getYPosition() + 6);
            }
            if (paddle2Left && paddle2.getXPosition() - paddle2.getSize() / 2 > table.getTableCenterX()) {
                paddle2.setXPosition(paddle2.getXPosition() - 6);
            }
            if (paddle2Right && paddle2.getXPosition() + paddle2.getSize() / 2 < table.getTableCenterX()
                    + table.getTableWidth() / 2) {
                paddle2.setXPosition(paddle2.getXPosition() + 6);
            }

            // Detect puck collision
            if (puck.collides(paddle1)) {
                if (!isMuted) {
                    SoundManager.playSound("hit.wav");
                }
                double angle = Math.atan2(puck.getYPosition() - paddle1.getYPosition(),
                        puck.getXPosition() - paddle1.getXPosition());
                changepuckX = puckspeed * Math.cos(angle);
                changepuckY = puckspeed * Math.sin(angle);

            }

            if (puck.collides(paddle2)) {
                if (!isMuted) {
                    SoundManager.playSound("hit.wav");
                }
                double angle = Math.atan2(puck.getYPosition() - paddle2.getYPosition(),
                        puck.getXPosition() - paddle2.getXPosition());
                changepuckX = puckspeed * Math.cos(angle);
                changepuckY = puckspeed * Math.sin(angle);

            }

            // Detect collision with the table boundaries
            if (puck.getXPosition() - puck.getSize() / 2 <= table.getTableCenterX() - table.getTableWidth() / 2 ||
                    puck.getXPosition() + puck.getSize() / 2 >= table.getTableCenterX() + table.getTableWidth() / 2) {
                if (!isMuted) {
                    SoundManager.playSound("bounce.wav");
                }
                changepuckX = -changepuckX;
            }
            if (puck.getYPosition() - puck.getSize() / 2 <= table.getTableCenterY() - table.getTableHeight() / 2 ||
                    puck.getYPosition() + puck.getSize() / 2 >= table.getTableCenterY() + table.getTableHeight() / 2) {
                SoundManager.playSound("bounce.wav");
                changepuckY = -changepuckY;
            }

            // Detect goal
            if (isInGoal(puck, table.getGoal1())) {
                score2++; // Player 2 scores
                score2Display.setText("Player 2 Score: " + score2);
                resetPuck(1);
                resetPaddles();
                if (!isMuted && score2 != 6) {
                    SoundManager.playSound("applause.wav");
                }
                if (score2 == 6) {
                    if (!isMuted) {
                        SoundManager.playSound("drumroll.wav");
                    }
                    gameResult.setText("Player 2 Won!");
                    restartText.setText("Press Y to play again!");
                    while (true) {
                        arena.pause(); // pause the game
                        if (gameRestart) {
                            resetGame();
                            break;
                        }
                    }
                }

            } else if (isInGoal(puck, table.getGoal2())) {
                score1++; // Player 1 scores
                score1Display.setText("Player 1 Score: " + score1);
                resetPuck(2);
                resetPaddles();
                if (!isMuted && score1 != 6) {
                    SoundManager.playSound("applause.wav");
                }
                if (score1 == 6) {
                    if (!isMuted) {
                        SoundManager.playSound("drumroll.wav");
                    }
                    gameResult.setText("Player 1 Won!");
                    restartText.setText("Press Y to play again!");
                    while (true) {
                        arena.pause(); // pause the game
                        if (gameRestart) {
                            resetGame();
                            break;
                        }
                    }
                }

            }

            puck.move(changepuckX * friction, changepuckY * friction);
            friction = 1;
            arena.pause();
        }
    }

    private boolean isInGoal(Ball puck, Rectangle goal) {
        // Find edges of the puck goal
        double puckLeft = puck.getXPosition() - puck.getSize() / 2;
        double puckRight = puck.getXPosition() + puck.getSize() / 2;
        double puckTop = puck.getYPosition() - puck.getSize() / 2;
        double puckBottom = puck.getYPosition() + puck.getSize() / 2;
        double goalLeft = goal.getXPosition();
        double goalRight = goal.getXPosition() + goal.getWidth();
        double goalTop = goal.getYPosition();
        double goalBottom = goal.getYPosition() + goal.getHeight();
        // Return true if the puck is inside the goal
        return (puckLeft < goalRight && puckRight > goalLeft) &&
                (puckTop < goalBottom && puckBottom > goalTop);
    }

    private void resetPuck(int scoredOnPlayer) {
        // Reset the puck position and speed
        if (scoredOnPlayer == 1) {
            puck.setXPosition(table.getTableCenterX() - 70);
        } else if (scoredOnPlayer == 2) {
            puck.setXPosition(table.getTableCenterX() + 70);
        } else {
            puck.setXPosition(table.getTableCenterX());
        }
        puck.setYPosition(table.getTableCenterY());
        changepuckX = 0;
        changepuckY = 0;
    }

    // reset paddels
    private void resetPaddles() {
        paddle1.setXPosition(initialPaddle1X);
        paddle1.setYPosition(initialPaddle1Y);
        paddle2.setXPosition(initialPaddle2X);
        paddle2.setYPosition(initialPaddle2Y);
    }

    // restart game
    public void resetGame() {
        score1 = 0;
        score2 = 0;
        score1Display.setText("Player 1 Score: " + score1);
        score2Display.setText("Player 2 Score: " + score2);
        gameResult.setText("");
        restartText.setText("");
        resetPuck(0);
        resetPaddles();
        gameRestart = false;
        if (!isMuted) {
            SoundManager.playSound("fanfare.wav");
        }
    }
}