public class NQueen {
    private static int N;
    private static int[][] board;

    public NQueen(int size) {
        N = size;
        board = new int[N][N];
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    private static boolean solveNQueens(int row) {
        if (row >= N) {
            return true; // All queens are placed successfully
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                // Place queen at board[row][col]
                board[row][col] = 1;

                // Recur to place the remaining queens
                if (solveNQueens(row + 1)) {
                    return true;
                }

                // Backtrack if placing queen at board[row][col] doesn't lead to a solution
                board[row][col] = 0;
            }
        }

        return false; // No solution exists for this configuration
    }

    public static void initializeAndSolve() {
        // board[0][0] = 1; // Place the first queen at the top-left corner
        if (solveNQueens(0)) { // Start backtracking from the second row
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }

    public static void main(String[] args) {
        int n = 4; // Set the size of the board
        NQueen nQueens = new NQueen(n);
        initializeAndSolve();
    }
}
