package ChaseLogic;

import java.util.ArrayList;

public final class Wave {
    private final Position currentPosition;
    private final Position purposePosition;
    private final MonsterCave cave;
    private ArrayList<ArrayList<Integer>> wave;

    public Wave(Position currentPosition, Position purposePosition, MonsterCave cave) {
        this.currentPosition = currentPosition;
        this.purposePosition = purposePosition;
        this.cave = cave;
        createEmptyWave();
    }

    private void createEmptyWave() {
        int width = this.cave.getGameBoardCave().size(), length = this.cave.getGameBoardCave().get(0).size();
        ArrayList<ArrayList<Integer>> emptyWave = new ArrayList<>(width);
        for (int i = 0; i < width; ++i) {
            emptyWave.set(i, new ArrayList<Integer>(length));
            for(int j = 0; j < length; ++j)
                emptyWave.get(i).set(j, 0);
        }
        this.wave = emptyWave;
    }
}
