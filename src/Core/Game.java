package Core;

import java.util.ArrayList;

public class Game {
    private static final int VILLAGER_COUNT = 12;
    private static final int ENEMIES_COUNT = 3;

    private ArrayList<Entity> allEntities = new ArrayList<>();
    private ArrayList<Entity> villagers = new ArrayList<>();
    private ArrayList<Entity> enemies = new ArrayList<>();

    public void newGame() {
        for (int i = 0; i < VILLAGER_COUNT; i++) {
            allEntities.add(new Entity());
        }


    }

    public void nightTime() {

    }
}
