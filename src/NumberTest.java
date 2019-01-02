import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class NumberTest {

            // тест для проверки одного значения
    @Test
    public void getString() throws Exception {

        String testNumber = "123";
        String testNumberText = "сто двадцать три";

        Number expected = new Number(testNumber);
        expected.toString();
        Assert.assertEquals("Ошибка в переводе числа " + testNumber, expected.getString(), testNumberText);

    }
    String num = "";
    String numStr = "";

            // Data Driven Test
    @Test
    public void fromExcel() throws Exception{
        Number expected;
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream("TestData.xls");
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();

        Row row = it.next();
        Iterator<Cell> cells = row.iterator();

        while (cells.hasNext()) {

            Cell cell = cells.next();
            CellType cellType = cell.getCellType();

            switch (cellType) {
                case STRING:
                    numStr += cell.getStringCellValue();
                    break;
                case NUMERIC:
                    num += cell.getNumericCellValue();
                    num = num.substring(0,num.indexOf('.'));
                    break;
                default:
                    break;
            }
        }
        expected = new Number(num);
        expected.toString();
        Assert.assertEquals("Ошибка в переводе числа " + num, expected.getString(), numStr);
    }

            // тесты для проврки правильности вводимого числа

    @Test //(expected = Exception.class)
    public void emptyString() throws Exception{
        Number N = new Number("");
    }

    @Test //(expected = Exception.class)
    public void symbolsInString() throws Exception{
        Number N = new Number("123-4");
    }

    @Test //(expected = Exception.class)
    public void tooHigh() throws Exception{
        Number N = new Number("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    }

    @Test //(expected = Exception.class)
    public void startsWithZero() throws Exception{
        Number N = new Number("0123");
    }
}