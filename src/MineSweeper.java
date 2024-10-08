import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    //Variables
    private String[][] gameBoard, mineBoard;
    private int boardRow, boardColumn;
    private int mineNum;
    private final Scanner input = new Scanner(System.in);

    // Adjusts the size of the game board
    private void setBoard() {
        while (true) {
            System.out.print("Enter the board's row: ");    // Evaluation Form 7
            boardRow = input.nextInt();
            System.out.print("Enter the board's columns: ");
            boardColumn = input.nextInt();
            if (boardRow<2 || boardColumn<2) {
                System.out.println("Rows or columns can't be less than 2!");
            } else {
                break;
            }
        }
        gameBoard = new String[boardRow][boardColumn];
        for (String[] strings : gameBoard) {
            Arrays.fill(strings, "-");
        }
    }

    // Shows the game board
    private void showBoard() {
        for (String[] row:gameBoard) {
            for(String column:row) {
                System.out.print(column+" ");
            }
            System.out.println();
        }
    }

    // Sets random mines on the mine board
    private void setMine() {
        mineBoard = new String[boardRow][boardColumn];
        Random random = new Random();
        mineNum = (boardRow*boardColumn)/4;
        for (String[] strings : mineBoard) {
            Arrays.fill(strings, "-");
        }
        int mine=0;
        while (mine<mineNum) {    // Evaluation Form 8
            int randomRow = random.nextInt(boardRow);
            int randomColumn = random.nextInt(boardColumn);
            if (mineBoard[randomRow][randomColumn].equals("-")) {
                mineBoard[randomRow][randomColumn] = "*";
                mine++;
            }
        }
        showMine();
    }

    // Shows the mine board
    private void showMine() {
        for (String[] row: mineBoard) {
            for (String column:row) {
                System.out.print(column+" ");
            }
            System.out.println();
        }
    }

    // Starts the game!
    void run() {
        setBoard();
        System.out.println("Mine's Location");
        setMine();
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Welcome The Mine Sweeper Game!");

        int userRow, userColumn, blank=boardRow*boardColumn;
        boolean isWin = false;
        while (true) {
            showBoard();    // Evaluation Form 11
            System.out.print("Enter the row: ");    // Evaluation Form 9
            userRow = input.nextInt();
            System.out.print("Enter the column: ");
            userColumn = input.nextInt();
            System.out.println("----------------------");
            System.out.println("----------------------");
            if (userRow>=boardRow || userColumn>=boardColumn) {    // Evaluation Form 10
                System.out.println("Coordinate can't be found, please enter another coordinate!");
            } else if (mineBoard[userRow][userColumn].equals("*")) {    // Evaluation Form 13
                System.out.println("Game Over!");    // Evaluation Form 15
                break;
            } else if (!Objects.equals(gameBoard[userRow][userColumn], "-")) {
                System.out.println("This coordinate selected before, enter another coordinate!");
            } else if (blank-1==mineNum){    // Evaluation Form 14
                isWin = true;
                break;
            } else {
                gameBoard[userRow][userColumn] = mineDistance(userRow,userColumn);    // Evaluation Form 12
                blank--;
            }
        }
        if (isWin) {    // Evaluation Form 14
            gameBoard[userRow][userColumn] = mineDistance(userRow,userColumn);
            showBoard();
            System.out.println("You Win!");    // Evaluation Form 15
        }
    }

    // Mine counting algorithm
    private String mineDistance(int userRow, int userColumn) {
        int count=0;
        if (userRow==0&&userColumn==0) {
            if (mineBoard[0][1].equals("*")) {
                count++;
            }
            if (mineBoard[1][0].equals("*")) {
                count++;
            }
            if (mineBoard[1][1].equals("*")) {
                count++;
            }
        }
        if (userRow== gameBoard.length-1&&userColumn==0) {
            if (mineBoard[userRow-1][0].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][1].equals("*")) {
                count++;
            }
        }
        if (userRow== gameBoard.length-1&&userColumn== gameBoard[userRow].length-1) {
            if (mineBoard[userRow][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn].equals("*")) {
                count++;
            }
        }
        if (userRow== 0&&userColumn== gameBoard[userRow].length-1) {
            if (mineBoard[userRow][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn].equals("*")) {
                count++;
            }
        }
        if (userColumn==0&&userRow!=0&&userRow!= gameBoard.length-1) {
            if (mineBoard[userRow-1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn+1].equals("*")) {
                count++;
            }
        }
        if (userRow==0&&userColumn!=0&&userColumn!= gameBoard[userRow].length-1) {
            if (mineBoard[userRow][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn+1].equals("*")) {
                count++;
            }
        }
        if (userColumn==gameBoard[userRow].length-1&&userRow!=0&&userRow!= gameBoard.length-1) {
            if (mineBoard[userRow-1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn].equals("*")) {
                count++;
            }
        }
        if (userRow==gameBoard.length-1&&userColumn!=0&&userColumn!= gameBoard[userRow].length-1) {
            if (mineBoard[userRow][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][userColumn+1].equals("*")) {
                count++;
            }
        }
        if (userRow!=0&&userRow!= gameBoard.length-1&&userColumn!=0&&userColumn!=gameBoard[userRow].length-1) {
            if (mineBoard[userRow-1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow-1][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn+1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn].equals("*")) {
                count++;
            }
            if (mineBoard[userRow+1][userColumn-1].equals("*")) {
                count++;
            }
            if (mineBoard[userRow][userColumn-1].equals("*")) {
                count++;
            }
        }
        return Integer.toString(count);
    }

}