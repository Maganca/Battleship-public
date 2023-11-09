package model;

import java.util.ArrayList;

public class Ship {
    private int health;
    public ArrayList<int[]> locations;

    public Ship(int size) {
        health = size;
        locations = new ArrayList<int[]>();
    }

    public Ship(int[] start, int[] end) {
        locations = new ArrayList<int[]>();
        int lowerX = start[0], higherX = end[0], lowerY = start[1], higherY = end[1];
        if (lowerX > higherX) {
            lowerX = end[0];
            higherX = start[0];
        }
        if (lowerY > higherY) {
            lowerY = end[1];
            higherY = start[1];
        }
        if(lowerX != higherX && lowerY != higherY)
        for (int i = lowerX; i <= higherX; i++) {
            for (int j = lowerY; j <= higherY; j++) {
                locations.add(new int[] { i, j });
            }
        }
        health = locations.size();
    }

    public int getHealth() {
        return health;
    }

    public void damage() {
        health -= 1;
    }
}