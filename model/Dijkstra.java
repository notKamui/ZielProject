/**
 * Dijkstra
 * Class which contains a function to apply and set a distance field arround the player
 *
 * @author notKamui
 */

package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dijkstra {
    private Tile start;
    private Tile current;
    private LinkedList<Tile> frontier; // FIFO queue
    private ArrayList<Tile> distanceField;

    public Dijkstra() {
        initVariables();
    }

    /**
     * Main Dijkstra function
     * Applies a Dijkstra based distance field around the player on the tiles, with a limited range (default : 250 units)
     * Cardinal cost : 10 units | Diagonal cost : 14 units (Pythagorean theorem)
     */
    public void applyDistanceField() {
        this.resetDistanceField();

        double range = 250; // 250 = A screen and a half horizontaly, on both ways
        double reachedDistance = 0;
        while (!this.frontier.isEmpty() && (reachedDistance <= range && reachedDistance != Double.MAX_VALUE)) {
            this.current = this.removeFromFrontier();
            reachedDistance = this.current.getDistance();
            int i = 0;
            for (Tile next : this.getNeighbors()) {
                // if next is a tile AND is not solid AND is accessible AND is not in distanceField THEN DO
                if (next != null && next.getHitbox().getBounds() == null && !distanceField.contains(next)) {
                    this.addToFrontier(next);
                    double cost;
                    if (i <= 3)     // CARDINALS
                        cost = 10;
                    else            // DIAGONALS
                        cost = 14;
                    this.addToDistanceFixed(next, cost + reachedDistance);
                }
                i++;
            }
        }
    }

    //---------------------------------------------------------

    /**
     * Resets all the distances from the tiles, and sets the start node
     */
    private void resetDistanceField() {
        this.initVariables();

        // reseting all distances to "infinity" (actually Double.MAX_VALUE)
        for (Tile tile : MathDataBuilder.world().getMap().getTileMap())
            tile.resetDistance();

        // setting the start node
        this.setStartNode();
        this.addToFrontier(this.start);
        this.addToDistanceFixed(this.start, 0);
    }

    /**
     * Gets neighbour tiles of the current tile (ordered by cost of movement) (null if OutOfBounds or blocked or a solid)
     * CARDINALS (cost = 10)
     * 0 up
     * 1 down
     * 2 left
     * 3 right
     * DIAGONALS (cost = 14)
     * 4 up-left
     * 5 up-right
     * 6 down-left
     * 7 down-right
     *
     * @return neighbours
     */
    private Tile[] getNeighbors() {
        Map map = MathDataBuilder.world().getMap();
        int index = this.current.getIndex();
        Tile[] neighbors = new Tile[] {
                map.getTileAt(index - MathDataBuilder.LINELENGTH),
                map.getTileAt(index + MathDataBuilder.LINELENGTH),
                map.getTileAt(index - 1),
                map.getTileAt(index + 1),
                null,
                null,
                null,
                null
        };

        // checks if walls
        for (int i = 0; i <= 3; i++)
            if (neighbors[i] != null && neighbors[i].getHitbox().getBounds() != null)
                neighbors[i] = null;

        //checks if accessible
        if (neighbors[0] != null || neighbors[2] != null)
            neighbors[4] = map.getTileAt(index - MathDataBuilder.LINELENGTH - 1);
        if (neighbors[0] != null || neighbors[3] != null)
            neighbors[5] = map.getTileAt(index - MathDataBuilder.LINELENGTH + 1);
        if (neighbors[1] != null || neighbors[2] != null)
            neighbors[6] = map.getTileAt(index + MathDataBuilder.LINELENGTH - 1);
        if (neighbors[1] != null || neighbors[3] != null)
            neighbors[7] = map.getTileAt(index + MathDataBuilder.LINELENGTH + 1);

        return neighbors;
    }


    /**
     * adds the specified tile to the frontier queue
     *
     * @param tile
     */
    private void addToFrontier(Tile tile) {
        this.frontier.add(tile);
    }

    /**
     * removes the first tile from the frontier queue
     */
    private Tile removeFromFrontier() {
        return this.frontier.removeFirst();
    }

    /**
     * adds the specified tile to the distance field with a specified distance
     *
     * @param tile
     * @param distance
     */
    private void addToDistanceFixed(Tile tile, double distance) {
        tile.setDistance(distance);
        this.distanceField.add(tile);
    }

    /**
     * finds and set the start node (which tile the player is standing on)
     */
    private void setStartNode() {
        int x = MathDataBuilder.world().getPlayer().coordXProperty().get();
        int y = MathDataBuilder.world().getPlayer().coordYProperty().get();
        int index = MathDataBuilder.coordsToIndex(x + MathDataBuilder.TILESIZE / 2, y + MathDataBuilder.TILESIZE / 2);
        this.start = MathDataBuilder.world().getMap().getTileAt(index);
    }


    /**
     * initializes attribute variables
     */
    private void initVariables() {
        this.frontier = new LinkedList<>();
        this.distanceField = new ArrayList<>();
        this.start = null;
        this.current = null;
    }
}
