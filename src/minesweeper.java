import java.util.*;

public class minesweeper {
    private int height;
    private int width;
    private int bomb_proc;
    private int bomb_val = -1;
    private int bomb_num;
    /*private int blankBoard[][] = {

    }*/
    private int[][] newBoard = new int[height][width];
    private int[][] resultBoard = new int[height][width];
    private int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };
    public minesweeper(int height, int width, int mines_perc) {
        if(height > 7){
            this.height = height;
        } else this.height = 9;
        if(width > 7){
            this.width = width;
        } else this.height = 9;
        if(mines_perc < (this.height*this.width)/10 && (this.height*this.width)/mines_perc > 1){
            this.bomb_proc = (int)Math.floor((double) (this.height * this.width) /mines_perc) ;
        } else this.bomb_proc = (int)Math.floor((double) (this.height * this.width) /10);

        int bomb_num = (int)Math.floor((double) (this.height * this.width)/mines_perc);
        int[][] board;
        board = populateMatrix(width, height, bomb_num);







        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (board[r][c] == -1) {
                    resultBoard[r][c] = -1; // Keep bombs as they are
                } else {
                    int bombCount = 0;
                    // Check neighbours
                    for (int[] dir : directions) {
                        int newRow = r + dir[0];
                        int newCol = c + dir[1];


                        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                            if (board[newRow][newCol] == -1) {
                                bombCount++;
                            }
                        }
                    }
                    resultBoard[r][c] = bombCount;
                }
            }
    }
       printBoard(resultBoard);
        //printBoard();
    }
    public void checkTile(int row, int col, int[][] board, boolean show) {


        if (board[row][col] == -1 && show) {
            System.out.println("GAME OVER");
        } else if(board[row][col] > 0 && show) {
            newBoard[row][col] = board[row][col];
        }else {

            HashMap<Integer, Integer> neighbours = new HashMap<Integer, Integer>();

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];


            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                if (board[newRow][newCol] == 0) {
                    newBoard[newRow][newCol] = 0;
                    checkTile(newRow, newCol, board, false);
                }
            }
        }

    }


    }
    public void checkTileUsable(int row, int col){
        checkTile(row, col, resultBoard, true);
        printBoard(newBoard);
    }
    public void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {

            System.out.println(" ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(fillToThree(board[i][j]));
            }
        }
    }
    public String fillToThree(int x){
        String value = String.valueOf(x);
        if(x<0) value = value + " ";
        else value = " " + value + " ";
        return value;

    }
    public int[][] populateMatrix(int m, int n, int x) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(matrix[i], 0);
        }

        int[][] positions = getRandomPositions(m, n, x);

        for (int[] pos : positions) {
            matrix[pos[0]][pos[1]] = bomb_val;
        }

        return matrix;
    }


    public static int[][] getRandomPositions(int m, int n, int x) {
            if (x > m * n) {
                throw new IllegalArgumentException("Cannot select more positions than available in the matrix.");
            }

            Set<Integer> chosen = new HashSet<>();
            Random rand = new Random();
            int[][] positions = new int[x][2];

            int index = 0;
            while (chosen.size() < x) {
                int pos = rand.nextInt(m * n);
                if (chosen.add(pos)) {
                    positions[index][0] = pos / n;
                    positions[index][1] = pos % n;
                    index++;
                }
            }

            return positions;
        }
    }

