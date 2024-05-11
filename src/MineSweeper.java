import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    String[][] gameBoard;
    String[][] mineBoard;
    int mineNum;
    int randomRow, randomColumn;
    int boardRow, boardColumn;
    static Scanner input = new Scanner(System.in);

    void setBoard() {
        boolean isTrue = true;
        while (isTrue) {
            System.out.print("Enter the board's row: ");
            boardRow = input.nextInt();
            System.out.print("Enter the board's columns: ");
            boardColumn = input.nextInt();
            if (boardRow<2 || boardColumn<2) {
                System.out.println("Rows or columns can't be less than 2!");
            } else {
                isTrue = false;
            }
        }

        gameBoard = new String[boardRow][boardColumn];
        for (int i=0;i<gameBoard.length;i++) {
            for (int j=0;j<gameBoard[i].length;j++) {
                gameBoard[i][j] = "-";
            }
        }
        showBoard();
    }

    void showBoard() {
        for (String[] row:gameBoard) {
            for(String column:row) {
                System.out.print(column+" ");
            }
            System.out.println();
        }
    }

    void setMine() {
        mineBoard = new String[boardRow][boardColumn];
        Random random = new Random();
        mineNum = (boardRow*boardColumn)/4;
        for (int i = 0; i< mineBoard.length; i++) {
            for (int j = 0; j< mineBoard[i].length; j++) {
                mineBoard[i][j] = "-";
            }
        }
        int mine=0;
        while (mine<mineNum) {
            randomRow = random.nextInt(boardRow);
            randomColumn = random.nextInt(boardColumn);
            if (mineBoard[randomRow][randomColumn].equals("-")) {
                mineBoard[randomRow][randomColumn] = "*";
                mine++;
            }
        }
        showMine();
    }

    void showMine() {
        for (String[] row: mineBoard) {
            for (String column:row) {
                System.out.print(column+" ");
            }
            System.out.println();
        }
    }

    void run() {
        setBoard();
        System.out.println("Mine's Location");
        setMine();
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("Welcome the mine sweeper game!");

        int userRow, userColumn, blank=boardRow*boardColumn;
        boolean isWin = false;
        while (true) {
            System.out.print("Enter the row: ");
            userRow = input.nextInt();
            System.out.print("Enter the column: ");
            userColumn = input.nextInt();
            System.out.println("----------------------");
            System.out.println("----------------------");
            if (userRow>=boardRow || userColumn>=boardColumn) {
                System.out.println("Coordinate can't be found, please enter another coordinate");
            } else if (mineBoard[userRow][userColumn].equals("*")) {
                System.out.println("Game Over!");
                break;
            } else if (!Objects.equals(gameBoard[userRow][userColumn], "-")) {
                System.out.println("This coordinate selected before, enter another coordinate");
            } else if (blank-1==mineNum){
                isWin = true;
                break;
            } else {
                gameBoard[userRow][userColumn] = mineDistance(userRow,userColumn);
                blank--;
            }
            showBoard();
        }
        if (isWin) {
            gameBoard[userRow][userColumn] = mineDistance(userRow,userColumn);
            showBoard();
            System.out.println("You win!");
        }
    }

    String mineDistance(int userRow, int userColumn) {
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
