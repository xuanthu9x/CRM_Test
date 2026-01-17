package TestCase;

import helper.ExcelHelper;
import helper.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderLogin {
    @DataProvider(name = "data_provider_login")
    public Object[][] dataLogin() {
        return new Object[][]{{"admin@example.com", "123456"}, {"user1@example.com", "123456"}};
    }



    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/resources/DataTest/Login.xlsx", "Login");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_hashtable")
    public Object[][] dataLoginHRMFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/DataTest/Login.xlsx", "Login", 1, 2);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_specific_rows")
    public Object[][] data_provider_login_excel_specific_rows() {
        ExcelHelper excelHelper = new ExcelHelper();
        int[] specificRows = new int[] {1, 3, 4}; //Dòng cụ thể cần lấy
        Object[][] data = excelHelper.getDataFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/DataTest/Login.xlsx", "Login", specificRows);
        System.out.println("getDataFromSpecificRows: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_specific_rows_hashtable")
    public Object[][] data_provider_login_excel_specific_rows_hashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        int[] specificRows = new int[] {1, 2, 4}; //Dòng cụ thể cần lấy
        Object[][] data = excelHelper.getDataHashTableFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/DataTest/Login.xlsx", "Login", specificRows);
        System.out.println("getDataHashTableFromSpecificRows: " + data);
        return data;
    }
}
