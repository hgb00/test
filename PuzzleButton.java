package SlideButtonExtended;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class PuzzleButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    public PuzzleButton(SlidePuzzleBoard b, PuzzleFrame f) {
        board = b;
        frame = f;
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (board.gameOn()) {
            String command = getText();
            if (command != "" && board.move(Integer.parseInt(command))) {
                frame.update();
                if (board.gameOver()) {
                    frame.finish();
                }
            }
        }
    }
}
