package SlideButtonExtended;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

public class StartButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    private int sec;
    private int min;

    public StartButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Start");
        board = b;
        frame = f;
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        frame.started();
        board.createPuzzlePiece();
        frame.update();
        sec = LocalTime.now().getSecond();
        min = LocalTime.now().getMinute();
    }
    public int getSec(){
        return sec;
    }
    public int getMin(){
        return min;
    }
}
