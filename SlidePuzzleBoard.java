package SlideButtonExtended;

import java.util.*;

public class SlidePuzzleBoard {
    private PuzzlePiece[][] board;
    private int empty_col = 3;
    private int empty_row = 3;
    private boolean game_on = false;
    public SlidePuzzleBoard() {
        int num = 1;
        board = new PuzzlePiece[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (empty_col == j && empty_row == i){
                    board[i][j] = null;
                }else {
                    board[i][j] = new PuzzlePiece(num);
                    num++;
                }
            }
        }
    }
    private int[] generateRandomPermutation(int n) {
        Random random = new Random();
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            int d = random.nextInt(i+1);
            permutation[i] = permutation[d];
            permutation[d] = i;
        }
        return permutation;
    }
    public PuzzlePiece getPuzzlePiece(int row, int col) {
        return board[row][col];
    }
    public void createPuzzlePiece() {
        int[] numbers = generateRandomPermutation(15);
        int i = 0;
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++) {
                if(row != 3 || col != 3) {
                    board[row][col] = new PuzzlePiece(numbers[i] + 1);
                    i += 1;
                }
                else {
                    board[row][col] = null;
                    empty_row = 3;
                    empty_col = 3;
                }

            }
        game_on = true;
    }
    boolean gameOn(){
        return game_on;
    }
    boolean gameOver(){
        if (empty_row != 3 || empty_col != 3) {
            return false;
        }
        else {
            int number = 1;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    // 마지막 영역 4행 4열에 이르면, 게임종료 | for문으로 규칙을 배정해둠.
                    if (row != 3 || col != 3) {
                        if (board[row][col].faceValue() != number) {
                            return false;
                        } else {
                            number += 1;
                        }
                    }
                }

            }
            game_on = false;
            return true;
        }
    }
    public boolean move(int w){
        PuzzlePiece piece = board[empty_row][empty_col];
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        for (int i = 0; i < 4; i++){
            int row = empty_row + dx[i];
            int col = empty_col + dy[i];
            if (!(0<=row && row<4) || !(0<=col && col<4)){continue;}
            if (board[row][col].faceValue() == w){
                board[empty_row][empty_col] = board[row][col];
                board[row][col] = piece;
                empty_row += dx[i];
                empty_col += dy[i];
                return true;
            }
        }
        return false;
    }
}
