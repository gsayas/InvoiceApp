import com.akendos.CSVReader.CSVReader;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class CSVReaderTest {

    private CSVReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new CSVReader("/home/giovanny/IdeaProjects/InvoiceApp/example.csv");

    }

    @Test
    public void testLoadFile() {
        reader.loadFile();

        assertEquals(2, reader.getNumRows());
    }

    @Test
    public void testParseCells() {
        reader.loadFile();
        reader.parseCells();

        assertEquals(3, reader.getNumCols());
    }

    @Test
    public void testGetCell() {
        reader.loadAndParseFile();

        assertEquals("val0.0", reader.getField(0,0));
        assertEquals("val0.1", reader.getField(0,1));
        assertEquals("val1.1", reader.getField(1,1));
    }

    @Test
    public void testPrintColumnTitles() {
        reader.loadAndParseFile();

        assertEquals("col1,col2,col3", reader.printColumnTitles());
    }

    @Test
    public void testDeleteColumnByTitle() {
        reader.loadAndParseFile();

        assertEquals("col1,col2,col3", reader.printColumnTitles());
        assertEquals("val1.1", reader.getField(1,1));
        reader.deleteColumnByTitle("col2");
        assertEquals("col1,col3", reader.printColumnTitles());
        assertEquals("val1.2", reader.getField(1,1));
    }

}
