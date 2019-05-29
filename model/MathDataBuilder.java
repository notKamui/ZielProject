package model;

import javafx.collections.ObservableList;
import model.TileType.Ground;
import model.TileType.Sky;
import model.TileType.Void;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Tile;
public class MathDataBuilder {
  
	public static final int TILESIZE = 80;
	public static final int LINELENGTH = getLineLength();
	private static World world;
	
	public static boolean isNextToSolid(int id) {
		boolean isNextToSolid=false;
		if(id-1>0) {
			if (world.getMap().getTileAt(id-1).getHitbox().getBounds()!=null) {
				isNextToSolid=true;
			}
		}
		if(id-LINELENGTH>0) {
			if (world.getMap().getTileAt(id-LINELENGTH).getHitbox().getBounds()!=null) {
				isNextToSolid=true;
			}
		}
		if(id+1<=world.getMap().getTileMap().size()) {
			if (world.getMap().getTileAt(id+1).getHitbox().getBounds()!=null) {
				isNextToSolid=true;
			}
		}
		if(id+LINELENGTH<=world.getMap().getTileMap().size()) {
			if (world.getMap().getTileAt(id+LINELENGTH).getHitbox().getBounds()!=null) {
				isNextToSolid=true;
			}
		}
		return isNextToSolid;
	}
	

    public static int coordsToIndex(int x, int y) {
        return (int) (Math.floor(y / TILESIZE) * LINELENGTH + Math.floor(x / TILESIZE));
    }

    public static int[] indexToCoord(int i) {
        int y = (i / LINELENGTH) * TILESIZE;
        int x = (i % LINELENGTH) * TILESIZE;
        return new int[]{x, y};
    }

    private static int getLineLength() {
        String content = readFile("src/view/map.txt");
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
            case 'g':
                tile = new Ground(i);
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
}

