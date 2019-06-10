package model;

import javafx.collections.ObservableList;
import model.TileType.Void;
import model.TileType.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MathDataBuilder {
    public static final int TILESIZE = 64;
    public static final int ITEMSIZE = 20;
    public static final int[] PLAYERDIM = {64, 89}; // {width, height}
    public static final int LINELENGTH = getLineLength();
    private static World world;

    public static boolean isNextToSolid(int id) {
        if (id - 1 > 0 && world.getMap().getTileAt(id - 1).getHitbox().getBounds() != null
                || id - LINELENGTH > 0 && world.getMap().getTileAt(id - LINELENGTH).getHitbox().getBounds() != null
                || id + 1 <= world.getMap().getTileMap().size() && world.getMap().getTileAt(id + 1).getHitbox().getBounds() != null
                || id + LINELENGTH <= world.getMap().getTileMap().size() && world.getMap().getTileAt(id + LINELENGTH).getHitbox().getBounds() != null)
            return true;
        else
            return false;
    }

    public static int coordsToIndex(int x, int y) {
        return (int) (Math.floor(y / TILESIZE) * LINELENGTH + Math.floor(x / TILESIZE));
    }

    public static int[] indexToCoord(int i) {
        return new int[]{(i % LINELENGTH) * TILESIZE, (i / LINELENGTH) * TILESIZE};
    }

    public static int coordToIndexTile(int x, int y) {
        return y * LINELENGTH + x;
    }

    public static int[] indexToCoordTile(int i) {
        return new int[]{i % LINELENGTH, i / LINELENGTH};
    }

    private static int getLineLength() {
        String content = readFile("src/resources/other/map.txt");
        if (content.contains("\r"))
            return content.indexOf('\r');
        else
            return content.indexOf('\n');
    }

    static String readFile(String fname) {
        String content = null;
        File file = new File(fname);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    static void saveMap(ObservableList<Tile> map) {
        try {
            File file = new File("src/view/map.txt");
            FileWriter fileWriter = new FileWriter(file, false);
            String newContent = "";
            int i = 1;
            for (Tile t : map) {
                newContent = newContent + t.getCharCode();
                if (i % MathDataBuilder.LINELENGTH == 0)
                    newContent = newContent + "\r\n";
                i++;
            }
            fileWriter.write(newContent);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Tile makeTile(int i, char c) {
        Tile tile;
        switch (c) {
            case 'D':
                tile = new Dirt(i);
                break;
            case 'd':
                tile = new DirtBG(i);
                break;
            case 'B':
                tile = new Brick(i);
                break;
            case 's':
                tile = new Sky(i);
                break;
            default:
                tile = new Void(i);
                break;
        }
        return tile;
    }

    public static void setWorld(World w) {
        world = w;
    }

    public static World world() {
        return world;
    }
}

