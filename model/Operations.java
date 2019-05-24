package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Operations {
	public static final int TILESIZE = 80;
	public static final int LINELENGTH = getLineLength();
	public static int coordsToIndex(int x, int y) {
		 return (int) (Math.floor(y/TILESIZE)*LINELENGTH + Math.floor(x/TILESIZE));
	}
	
	public static int[] indexToCoord(int i) {
		 int y = (i/LINELENGTH)*TILESIZE; 
		 int x = (i%LINELENGTH)*TILESIZE; 
		 int[] coords = {x,y};
		 return coords;
	}
	
	private static int getLineLength() {
		String content = readFile("src/view/map.csv");
        return content.indexOf('\n');

		
	}
    public static String readFile(String fname) {
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
}

