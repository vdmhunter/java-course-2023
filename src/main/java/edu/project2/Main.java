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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final int HEIGHT = 19;
    private static final int WIDTH = 79;
    private static final String WALL = "\u001B[34m█\u001B[0m";
    private static final String PATH = "\u001B[38;5;196m*\u001B[0m";
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

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
                solution = solver.solve(maze,
                    new Coordinate(0, 0),
                    new Coordinate(HEIGHT - 1, WIDTH - 1));
            }

            printMaze(maze, solution);
        }
        // CHECKSTYLE:ON: Enable MagicNumber check
    }

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

    private static Generator getGenerator(int choice) {
        return switch (choice) {
            case 1 -> new RecursiveBacktrackerGenerator();
            case 2 -> new GrowingTreeGenerator();
            default -> null;
        };
    }

    private static Solver getSolver(int choice) {
        // CHECKSTYLE:OFF: Disable MagicNumber check
        return switch (choice) {
            case 3 -> new DfsSolver();
            case 4 -> new BfsSolver();
            default -> null;
        };
        // CHECKSTYLE:ON: Enable MagicNumber check
    }

    private static void printMaze(Maze maze, List<Coordinate> solution) {
        LOGGER.info("\n");
        printHorizontalBoundary();
        printMazeRows(maze, solution);
        printHorizontalBoundary();
        LOGGER.info("\n\n");
    }

    private static void printHorizontalBoundary() {
        for (int col = 0; col < WIDTH + 2; col++) {
            LOGGER.info(WALL);
        }
        LOGGER.info("\n");
    }

    private static void printMazeRows(Maze maze, List<Coordinate> solution) {
        for (int row = 0; row < HEIGHT; row++) {
            printMazeRow(maze, row, solution);
        }
    }

    private static void printMazeRow(Maze maze, int row, List<Coordinate> solution) {
        for (int col = 0; col < WIDTH + 2; col++) {
            printMazeCell(maze, row, col, solution);
        }
        LOGGER.info("\n");
    }

    private static void printMazeCell(Maze maze, int row, int col, List<Coordinate> solution) {
        if (col == 0 || col == WIDTH + 1) {
            LOGGER.info(WALL);
        } else {
            printMazeInterior(maze, row, col, solution);
        }
    }

    private static void printMazeInterior(Maze maze, int row, int col, List<Coordinate> solution) {
        Cell cell = maze.grid()[row][col - 1];
        if (cell.getType() == Cell.Type.WALL) {
            LOGGER.info(WALL);
        } else {
            printMazePassage(row, col, solution);
        }
    }

    private static void printMazePassage(int row, int col, List<Coordinate> solution) {
        if (solution.contains(new Coordinate(row, col - 1))) {
            LOGGER.info(PATH);
        } else {
            LOGGER.info(" "); // Passage
        }
    }
}
