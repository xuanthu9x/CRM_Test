package helper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src/main/resources/configs/config.properties";

    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        // Add tất cả file Properties vào đây theo mẫu
        files.add("src/main/resources/configs/config.properties");
//        files.add("src/test/resources/configs/local.properties");
//        files.add("src/test/resources/configs/production.properties");
//        files chứa list cac file properties cần load
        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            return properties; // đối tượng properties chứa tất cả các cặp key-value từ các file
        } catch (IOException ioe) {
            return new Properties();
        }
    }

    // setFile để chỉ định file properties cụ thể để làm việc
    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePath;
            //SystemHelper.getCurrentDir() get đường dẫn hiện tại của project
            // relPropertiesFilePath get đươờng dẫn tương đối của file properties từ project tới file cần set
            file = new FileInputStream(linkFile);
            // FileInputStream để đọc file từ đường dẫn linkFile
            properties.load(file);
            // Load nội dung file vào đối tượng properties
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // setDefaultFile để chỉ định file properties mặc định để làm việc
    public static void setDefaultFile() {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        String value = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Lấy giá trị từ file đã Set
            value = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    /*public static void setValue(String key, String keyValue) {
        try {
            if (file == null) {
                properties = new Properties(); // nếu file null thì khởi tạo properties mới
                file = new FileInputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
                // get đường dẫn file properties mặc định gắn vào file
                properties.load(file);
                // đọc nội dung file vào properties
                file.close();
                // đóng file sau khi đọc xong
                out = new FileOutputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
                // out để ghi file từ đường dẫn file properties mặc định ???? ghi vào đâu
            }

            out = new FileOutputStream(linkFile);
            System.out.println(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    public static void setValue1(String key, String keyValue) {
         linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
         properties = new Properties();

        try {
            // ĐỌC
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();

            // GHI
            out = new FileOutputStream(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
