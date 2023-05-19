public class AirHockey {
    public static void main(String[] args) {
        // Set the table up
        int arenaWidth = 1000;
        int arenaHeight = 800;
        int tableWidth = 700;
        int tableHeight = 400;

        //Initialize Table
        Table table = new Table(arenaWidth, arenaHeight, tableWidth, tableHeight);
        table.tableCreate();

        // Create the GameArena
        GameArena arena = table.getArena();

        // Initialize the GameLogic with the arena and table
        GameLogic gameLogic = new GameLogic(arena, table);
        gameLogic.updatePaddles();
        gameLogic.startGameLoop();
    }
}