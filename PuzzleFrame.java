package SlideButtonExtended;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class PuzzleFrame extends JFrame {
    private SlidePuzzleBoard board;
    private PuzzleButton[][] button_board;
    private JLabel label = new JLabel("time: 0");
    private StartButton sb;
    private boolean finished = false;
    private boolean started = false;
    private int dt = 10000000;
    public PuzzleFrame(SlidePuzzleBoard b) {
        board = b;
        button_board = new PuzzleButton[4][4];
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new GridLayout(4, 4));
        JPanel p3 = new JPanel(new FlowLayout());

        sb = new StartButton(board,this);

        p1.add(sb);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                button_board[i][j] = new PuzzleButton(board, this);
                p2.add(button_board[i][j]);
            }
        }

        p1.add(label);

        cp.add(p1, BorderLayout.SOUTH);
        cp.add(p2, BorderLayout.CENTER);
        cp.add(p3, BorderLayout.NORTH);
        update();
        setTitle("PuzzleFrame");
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void update(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getPuzzlePiece(i,j) == null){
                    button_board[i][j].setText("");
                }else{
                    button_board[i][j].setText(String.valueOf(board.getPuzzlePiece(i, j).faceValue()));
                }
            }
        }
    }
    public void ut(LocalTime nt){
        if (started && !finished) {
            label.setText("time: " + ((nt.getMinute() - sb.getMin())*60+nt.getSecond() - sb.getSec()) + "s");
        } else if (finished) {
            dt = ((nt.getMinute() - sb.getMin())*60+nt.getSecond() - sb.getSec());
            System.out.println(dt);
            finished = false;
        }
    }
    public void finish() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getPuzzlePiece(i, j) == null) {
                    button_board[i][j].setText("Done");
                }
            }
        }
        finished = true;
        started = false;
    }
    public void started() {
        started = true;
    }
    public void time(){
        while (true) {
            ut(LocalTime.now());
        }
    }
}
