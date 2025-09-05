package Activities;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
 
public class Activity14 {
	public static void main(String[] args) throws IOException {
        try {
            File file = new File("C:\\Users\\PentelaSireesha\\Desktop\\Sri");
            boolean fStatus = file.createNewFile();
            if(fStatus) {    
                System.out.println("File created successfully!");
             //   FileUtils.writeStringToFile(file, "Some text in a file", Charset.defaultCharset());
                FileUtils.writeStringToFile(file, "Some text in a file", StandardCharsets.UTF_8);
            } else {
                System.out.println("File already exists at this path.");
            }
 
            // Read file
            System.out.println("Data in file: " + FileUtils.readFileToString(file, "UTF8"));
 
            // Create directory
            File destDir = new File("src/main/resources/destDir");
            // Copy file to directory
            FileUtils.copyFileToDirectory(file, destDir);
 
            // Get file from new directory
            File newFile = FileUtils.getFile(destDir, "newfile.txt");
            // Read data from file
            String newFileData = FileUtils.readFileToString(newFile, "UTF8");
            // Print it
            System.out.println("Data in new file: " + newFileData);
        } catch(IOException errMessage) {
            System.out.println(errMessage);
        }
    }

}
