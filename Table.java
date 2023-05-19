public class Table {
    private GameArena arena;
    private int arenaWidth;
    private int arenaHeight;
    private int tableWidth;
    private int tableHeight;
    private int tableCenterX;
    private int tableCenterY;
    private Rectangle goal1;
    private Rectangle goal2;

    public Table(int arenaWidth, int arenaHeight, int tableWidth, int tableHeight) {
        this.arenaWidth = arenaWidth;
        this.arenaHeight = arenaHeight;
        this.tableWidth = tableWidth;
        this.tableHeight = tableHeight;
        this.tableCenterX = arenaWidth / 2;
        this.tableCenterY = arenaHeight / 2;
        this.arena = new GameArena(arenaWidth, arenaHeight);
    }
        public void tableCreate() {
            // Create the outer frame
        Rectangle outerFrame = new Rectangle(tableCenterX - (tableWidth + 30) / 2, tableCenterY - (tableHeight + 30) / 2, tableWidth + 30, tableHeight + 30, "GREEN");
        arena.addRectangle(outerFrame);
 
        // create the table rectangle
        Rectangle table = new Rectangle(tableCenterX - tableWidth / 2, tableCenterY - tableHeight / 2, tableWidth, tableHeight, "WHITE");
        arena.addRectangle(table);
        
        // Create the goals
        int goalWidth = 10;
        int goalHeight = 200;
        this.goal1 = new Rectangle((tableCenterX - tableWidth / 2) + goalWidth / 2 - goalWidth / 2, (tableCenterY - goalHeight / 2), goalWidth, goalHeight, "BLACK");
        this.goal2 = new Rectangle((tableCenterX + tableWidth / 2) - goalWidth / 2 - goalWidth / 2, (tableCenterY - goalHeight / 2), goalWidth, goalHeight, "BLACK");
        arena.addRectangle(goal1);
        arena.addRectangle(goal2);
 
        // Create the center line
        Line centreLine = new Line(tableCenterX, tableCenterY - tableHeight / 2, tableCenterX, tableCenterY + tableHeight / 2, 2, "BLACK");
        arena.addLine(centreLine);

        // Create the center circle
        Ball centerCircleOuter = new Ball(tableCenterX, tableCenterY, 70, "BLACK");
        Ball centerCircleInner = new Ball(tableCenterX, tableCenterY, 65, "WHITE");
        arena.addBall(centerCircleOuter);
        arena.addBall(centerCircleInner);
            
    }

    public GameArena getArena() {
        return arena;
    }

    public int getTableCenterX() {
        return tableCenterX;
    }

    public int getTableCenterY() {
        return tableCenterY;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public Rectangle getGoal1(){
        return goal1;
    }

    public Rectangle getGoal2(){
        return goal2;
    }
    
}
 
 
 