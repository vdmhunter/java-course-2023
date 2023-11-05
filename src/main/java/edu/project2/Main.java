package edu.project2;

import edu.project2.generators.GrowingTreeGenerator;
import edu.project2.solvers.DfsSolver;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final int HEIGHT = 19;
    private static final int WIDTH = 79;
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {

        var generator = new GrowingTreeGenerator();
        var maze = generator.generate(HEIGHT, WIDTH);

        var solver = new DfsSolver();
        var solution = solver.solve(maze, new Coordinate(0, 0), new Coordinate(HEIGHT - 1, WIDTH - 1));


        LOGGER.info("\n");

        for (int col = 0; col < WIDTH + 2; col++) {
            LOGGER.info("█");
        }

        LOGGER.info("\n");

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH + 2; col++) {
                if (col == 0 || col == WIDTH + 1) {
                    LOGGER.info("█");

                    continue;
                }
                Cell cell = maze.getGrid()[row][col - 1];
                if (cell.getType() == Cell.Type.WALL) {
                    LOGGER.info("█"); // Wall
                } else {
                    if (solution.contains(new Coordinate(row, col - 1))) {
                        LOGGER.info("*");
                    } else {
                        LOGGER.info(" "); // Passage
                    }
                }
            }

            LOGGER.info("\n");
        }

        for (int col = 0; col < WIDTH + 2; col++) {
            LOGGER.info("█");
        }
    }
}
