package Test;

import model.MathDataBuilder;
import model.Tile;
import model.TileType.Dirt;
import model.TileType.Wood;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathDataBuilderTest {
    @Test
    public void coordToIndexTest() {
        assertEquals("regular case", 800, MathDataBuilder.coordsToIndex(3968, 384));
    }

    @Test
    public void indexToCoordTest() {
        assertEquals("regular case X", 3968, MathDataBuilder.indexToCoord(800)[0]);
        assertEquals("regular case Y", 384,  MathDataBuilder.indexToCoord(800)[1]);
    }

    @Test
    public void coordToIndexTileTest() {
        assertEquals("regular case",2480, MathDataBuilder.coordToIndexTile(20, 20));
    }

    @Test
    public void indexToCoordTileTest() {
        assertEquals("regular case", 20, MathDataBuilder.indexToCoordTile(2480)[0]);
        assertEquals("regular case", 20, MathDataBuilder.indexToCoordTile(2480)[1]);
    }

    @Test
    public void getLineLengthTest() {
        assertEquals("regular case", 123, MathDataBuilder.getLineLength());
    }

    @Test
    public void makeTileTest() {
        Tile tile = MathDataBuilder.makeTile(5, 'D');
        assertTrue("regular good case", tile instanceof Dirt);
        assertFalse("regular false case", tile instanceof Wood);
    }

    @Test
    public void readFileTest() {
        // assertEquals("regular case", "this is\r\na test\r\nfile\r\n", MathDataBuilder.readFile("src/Test/test.txt"));
        // ^-- this one is for Windows, as this shitty OS puts an \r before each \n .-.
        assertEquals("regular case", "this is\na test\nfile\n", MathDataBuilder.readFile("src/Test/test.txt"));
    }

    @Test
    public void countLinesInFileTest() {
        assertEquals("regular case", 3, MathDataBuilder.countLinesInFiles("src/Test/test.txt"));
    }
}