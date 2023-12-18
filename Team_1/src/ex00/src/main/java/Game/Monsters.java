package Game;

import java.util.ArrayList;

import static Game.ObjectsData.*;

public class Monsters extends GameElements {
    private final ArrayList<Position> monsters;

    public Monsters(ArrayList<ArrayList<Character>> matrix) {
        super(matrix);
        this.monsters = new ArrayList<>();
        createMonstersPositionList();
    }

    public ArrayList<Position> getMonstersList() {
        return monsters;
    }

    @Override
    protected void createElement() {
        int count = 0;
        while (count < ENEMIES_COUNT) {
            int x = (int) (Math.random() * ObjectsData.SIZE);
            int y = (int) (Math.random() * ObjectsData.SIZE);
            if (checkMonsterCreation(x, y)) {
                matrix.get(x).set(y, MONSTER_SIMBL);
                count++;
            }
        }
    }

    private void createMonstersPositionList() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j)
                if (matrix.get(i).get(j).equals(MONSTER_SIMBL))
                    this.monsters.add(new Position(i, j));
        }
    }

    private boolean checkMonsterCreation(final int x, final int y) {
        return matrix.get(x).get(y).equals(EMPTY_SIMBL)
                && ((x + 1 < SIZE && !matrix.get(x + 1).get(y).equals(PLAYER_SIMBL))
                || (x - 1 > 0 && !matrix.get(x - 1).get(y).equals(PLAYER_SIMBL))
                || (y + 1 < SIZE && !matrix.get(x).get(y + 1).equals(PLAYER_SIMBL))
                || (y - 1 > 0 && !matrix.get(x).get(y - 1).equals(PLAYER_SIMBL)));
    }
}
