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

        assertEquals(3, reader.getNumRows());

    }

    @Test
    public void testParseColumns() {
        reader.loadFile();
        reader.parseColumns();

        assertEquals(2, reader.getNumCols());

    }

    @Test
    public void testGetField() {
        reader.loadFile();
        reader.parseColumns();

        assertEquals("val0,0", reader.getField(0,0));

    }

}
