package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name = "loginDataP")
    public Object[][] getData() throws IOException {
    	System.out.println("⚡ DataProvider 'loginDataP' is being called!");
    	
        String sheetName = "Sheet1";
        String filePath = System.getProperty("user.dir") + "\\testData\\opencart_testdata.xlsx";

        // Initialize Excel Utility class
        ExcelUtils eu = new ExcelUtils(filePath);

        // Get total rows and columns (excluding headers)
        int totalRows = eu.getRowCount(sheetName);
        int totalCols = eu.getCellCount(sheetName, 1);

        if (totalRows == 0 || totalCols == 0) {
            throw new RuntimeException("No data found in Excel sheet: " + sheetName);
        }

        // Initialize 2D array
        Object[][] loginData = new Object[totalRows][totalCols];

        // Loop through each cell in each row
        for (int r = 1; r <= totalRows; r++) {
            for (int c = 0; c < totalCols; c++) {
                loginData[r - 1][c] = eu.readCellData(sheetName, r, c);
            }
        }

        return loginData;
    }
    

    // Debugging method (Optional)
    public static void main(String[] args) throws IOException {
        DataProviderUtils dp = new DataProviderUtils();
        Object[][] data = dp.getData();

        System.out.println("✅ Printing Data from Excel:");
        for (Object[] row : data) {
            for (Object cell : row) {
//                System.out.print(cell + "\t");
            	 System.out.print(cell + " | ");
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------");
        }
    }
    
    // Non Excel Local Data Provider
    @DataProvider(name = "loginDataLoc")
    public Object[][] getLoginTestData() {
        return new Object[][] {
            { "wisdomochus1@gmail.com", "Testing!23", "Valid", "Pass", "Pass" },
            { "user2@test.com", "WrongPass", "Invalid", "Pass", "Fail" }
        };
    }

}
