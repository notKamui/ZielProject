package model.EnemyType;

import model.Enemy;
import model.Map;
import model.MathDataBuilder;
import model.Tile;

public class Gargoyle extends Enemy {

    public Gargoyle(int x, int y) {
        super(901, x, y, 64, 64, true);
    }

    @Override
    public void act() {
        this.followPlayer();
        this.setPosition();
    }

    @Override
    public void followPlayer() {

        int coordMiddleX = this.coordXProperty().getValue() + this.getWidth() / 2;
        int coordMiddleY = this.coordYProperty().getValue() + this.getHeight() / 2;

        Map map = MathDataBuilder.world().getMap();
        Tile selfTile = MathDataBuilder.world().getMap().getTileAt(MathDataBuilder.coordsToIndex(coordMiddleX, coordMiddleY));
        int index = selfTile.getIndex();

        if (selfTile.getDistance() < 250) {

            Tile[] neighbors = {
                    map.getTileAt(index),
                    map.getTileAt(index - MathDataBuilder.LINELENGTH),
                    map.getTileAt(index + MathDataBuilder.LINELENGTH),
                    map.getTileAt(index - 1),
                    map.getTileAt(index + 1),
                    map.getTileAt(index - MathDataBuilder.LINELENGTH - 1),
                    map.getTileAt(index - MathDataBuilder.LINELENGTH + 1),
                    map.getTileAt(index + MathDataBuilder.LINELENGTH - 1),
                    map.getTileAt(index + MathDataBuilder.LINELENGTH + 1),
            };

            if (neighbors[0] != null) {

                Tile destination = neighbors[0];
                double distanceMin = neighbors[0].getDistance();
                for (int tile = 1; tile < neighbors.length; tile++) {

                    if (neighbors[tile] != null && neighbors[tile].getDistance() < distanceMin) {
                        destination = neighbors[tile];
                        distanceMin = neighbors[tile].getDistance();
                    }
                }

                int destMiddleX = destination.coordXProperty().getValue() + destination.getWidth() / 2;
                int destMiddleY = destination.coordYProperty().getValue() + destination.getHeight() / 2;

                if (destMiddleX < coordMiddleX) {
                    this.setVectX(Math.max(destMiddleX - coordMiddleX, -this.getSpeed()));
                } else if (destMiddleX > coordMiddleX) {
                    this.setVectX(Math.min(destMiddleX - coordMiddleX, this.getSpeed()));
                }

                if (destMiddleY < coordMiddleY) {
                    this.setVectY(Math.max(destMiddleY - coordMiddleY, -this.getSpeed()));
                } else if (destMiddleY > coordMiddleY) {
                    this.setVectY(Math.min(destMiddleY - coordMiddleY, this.getSpeed()));
                }
            }
        }
    }
}
