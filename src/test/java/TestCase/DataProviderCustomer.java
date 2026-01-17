package TestCase;

import helper.ExcelHelper;
import helper.SystemHelper;
import org.testng.annotations.DataProvider;

public class DataProviderCustomer {
    @DataProvider(name = "data_provider_customer")
    public Object[][] dataCustomer() {
        return new Object[][]{{"T_Test2_170126", "VAT123", "0555555555","google.com","Gold","USD$","English","Lac Long Quan","Ho Chi Minh","Quan 11","123","United States"}};
    }

    @DataProvider(name = "data_provider_customer_excel_hashtable")
    public Object[][] dataCustomerFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/DataTest/Login.xlsx", "Login", 1, 2);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_customer_excel_specific_rows_hashtable")
    public Object[][] data_provider_customer_excel_specific_rows_hashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        int[] specificRows = new int[] {1, 2, 4}; //Dòng cụ thể cần lấy
        Object[][] data = excelHelper.getDataHashTableFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/DataTest/Customers.xlsx", "AddCustomer", specificRows);
        System.out.println("getDataHashTableFromSpecificRows: " + data);
        return data;
    }
}
