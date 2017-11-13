import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 12.11.2017.
 */
public class WritableSummTest {
    WritableSumm w = new WritableSumm();

    @Test
    public void testNumberToString() throws Exception {
        String[] stringsFromOneTwenty = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
                "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать",
                "двадцать"};
        for (int i = 0; i < 20; i++) {
            assertEquals(stringsFromOneTwenty[i], w.numberToString(i));
        }
    }

    @Test
    public void testGetNameExcel() throws Exception {


        InputStream in = new FileInputStream("src/test/resources/TestExel.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);

        long inNumber = 0;
        String inString = null;

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();

                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print((inNumber = (long) cell.getNumericCellValue()) + " = ");
                        break;

                    case Cell.CELL_TYPE_STRING:
                        System.out.print((inString = cell.getStringCellValue()));
                        break;

                    default:
                        break;
                }
            }
            System.out.println();
            assertEquals(inString, w.numberToString(inNumber));
        }
    }
}