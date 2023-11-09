package edu.project2;

import edu.project2.generators.Generator;
import edu.project2.generators.GrowingTreeGenerator;
import edu.project2.generators.RecursiveBacktrackerGenerator;
import edu.project2.solvers.BfsSolver;
import edu.project2.solvers.DfsSolver;
import edu.project2.solvers.Solver;
import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import edu.project2.types.Maze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Main} class for the Maze Generator program.
 * This class provides a text-based interface for generating and solving mazes using different algorithms.
 */
public class Main {
    private static final int HEIGHT = 19;
    private static final int WIDTH = 79;
    private static final String WALL = "\u001B[34m█\u001B[0m";
    private static final String PATH = "\u001B[38;5;196m*\u001B[0m";
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Private constructor to prevent instantiation of the {@code Main} class.
     */
    private Main() {
    }

    /**
     * The main method that serves as the entry point for the Maze Generator program.
     *
     * @param args The command line arguments (unused).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maze maze = null;
        List<Coordinate> solution = new ArrayList<>();

        LOGGER.info("Welcome to the Maze Generator!\n\n");

        Generator generator;
        Solver solver;

        boolean exitFlag = false;

        // CHECKSTYLE:OFF: Disable MagicNumber check
        while (!exitFlag) {
            displayMenu(maze);
            int choice = getChoice(scanner);

            if (choice == 5) {
                LOGGER.info("Exiting the program. Goodbye!\n");
                scanner.close();
                exitFlag = true;

                continue;
            }

            if (choice <= 2) {
                generator = getGenerator(choice);
                assert generator != null;
                maze = generator.generate(HEIGHT, WIDTH);
                assert solution != null;
                solution.clear();
            } else if (maze != null && choice <= 4) {
                solver = getSolver(choice);
                assert solver != null;
                solution = solver.solve(
                    maze,
                    new Coordinate(0, 0),
                    new Coordinate(HEIGHT - 1, WIDTH - 1)
                );
            }

            printMaze(maze, solution);
        }
        // CHECKSTYLE:ON: Enable MagicNumber check
    }

    /**
     * Displays the menu options based on the current state of the maze.
     *
     * @param maze The current maze instance.
     */
    private static void displayMenu(Maze maze) {
        LOGGER.info("1. Generate a maze using the Recursive Backtracker algorithm\n");
        LOGGER.info("2. Generate a maze using the Growing Tree algorithm\n");

        if (maze != null) {
            LOGGER.info("3. Solve maze using DFS algorithm\n");
            LOGGER.info("4. Solve maze using BFS algorithm\n");
        }

        LOGGER.info("5. Exit\n\n");
        LOGGER.info("Enter your choice: ");
    }

    /**
     * Gets the user's choice from the menu.
     *
     * @param scanner The {@link Scanner} object for user input.
     * @return The user's choice.
     */
    private static int getChoice(Scanner scanner) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        // CHECKSTYLE:OFF: Disable MagicNumber check
        if (choice < 1 || choice > 5) {
            LOGGER.info("Invalid choice. Please select a valid option.\n\n");
        }
        // CHECKSTYLE:ON: Enable MagicNumber check

        return choice;
    }

    /**
     * Retrieves the appropriate {@link Generator} based on the user's choice.
     *
     * @param choice The user's choice for maze generation.
     * @return The selected {@link Generator} instance.
     */
    private static Generator getGenerator(int choice) {
        return switch (choice) {
            case 1 -> new RecursiveBacktrackerGenerator();
            case 2 -> new GrowingTreeGenerator();
            default -> null;
        };
    }

    /**
     * Retrieves the appropriate {@link Solver} based on the user's choice.
     *
     * @param choice The user's choice for maze solving.
     * @return The selected {@link Solver} instance.
     */
    private static Solver getSolver(int choice) {
        // CHECKSTYLE:OFF: Disable MagicNumber check
        return switch (choice) {
            case 3 -> new DfsSolver();
            case 4 -> new BfsSolver();
            default -> null;
        };
        // CHECKSTYLE:ON: Enable MagicNumber check
    }

    /**
     * Prints the current state of the maze, including walls, passages, and solution path.
     *
     * @param maze     The current maze instance.
     * @param solution The solution path to be highlighted in the maze.
     */
    private static void printMaze(Maze maze, List<Coordinate> solution) {
        LOGGER.info("\n");
        printHorizontalBoundary();
        printMazeRows(maze, solution);
        printHorizontalBoundary();
        LOGGER.info("\n\n");
    }

    /**
     * Prints a horizontal boundary line for the maze.
     */
    private static void printHorizontalBoundary() {
        for (int col = 0; col < WIDTH + 2; col++) {
            LOGGER.info(WALL);
        }
        LOGGER.info("\n");
    }

    /**
     * Prints each row of the maze, including walls and passages.
     *
     * @param maze     The current maze instance.
     * @param solution The solution path to be highlighted in the maze.
     */
    private static void printMazeRows(Maze maze, List<Coordinate> solution) {
        for (int row = 0; row < HEIGHT; row++) {
            printMazeRow(maze, row, solution);
        }
    }

    /**
     * Prints a single row of the maze, including walls and passages.
     *
     * @param maze     The current maze instance.
     * @param row      The row index.
     * @param solution The solution path to be highlighted in the maze.
     */
    private static void printMazeRow(Maze maze, int row, List<Coordinate> solution) {
        for (int col = 0; col < WIDTH + 2; col++) {
            printMazeCell(maze, row, col, solution);
        }
        LOGGER.info("\n");
    }

    /**
     * Prints a single cell of the maze, either a wall or passage.
     *
     * @param maze     The current maze instance.
     * @param row      The row index.
     * @param col      The column index.
     * @param solution The solution path to be highlighted in the maze.
     */
    private static void printMazeCell(Maze maze, int row, int col, List<Coordinate> solution) {
        if (col == 0 || col == WIDTH + 1) {
            LOGGER.info(WALL);
        } else {
            printMazeInterior(maze, row, col, solution);
        }
    }

    /**
     * Prints the interior of a maze cell, distinguishing between walls and passages.
     *
     * @param maze     The current maze instance.
     * @param row      The row index.
     * @param col      The column index.
     * @param solution The solution path to be highlighted in the maze.
     */
    private static void printMazeInterior(Maze maze, int row, int col, List<Coordinate> solution) {
        Cell cell = maze.grid()[row][col - 1];
        if (cell.getType() == Cell.Type.WALL) {
            LOGGER.info(WALL);
        } else {
            printMazePassage(row, col, solution);
        }
    }

    /**
     * Prints a passage in the maze, highlighting the solution path.
     *
     * @param row      The row index.
     * @param col      The column index.
     * @param solution The solution path to be highlighted in the maze.
     */
    private static void printMazePassage(int row, int col, List<Coordinate> solution) {
        if (solution.contains(new Coordinate(row, col - 1))) {
            LOGGER.info(PATH);
        } else {
            LOGGER.info(" "); // Passage
        }
    }
}
