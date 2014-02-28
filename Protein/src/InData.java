import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
 
public class InData {
    public static void main(String[] args) {
        String fileName = "Exempel.txt";
		Scanner r;
 
        try {
            r = new Scanner(fileName);
			while(r.hasNext()) {
				System.out.println(r.nextLine());
			}
        } catch (IOException e) {
        }
    }
}