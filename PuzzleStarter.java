package SlideButtonExtended;

import hgball.Threading;

public class PuzzleStarter {
    public static void main(String[] args) {
        SlidePuzzleBoard b = new SlidePuzzleBoard();
        PuzzleFrame f = new PuzzleFrame(b);
        Threading t = new Threading(f);
        t.start();
    }
}
