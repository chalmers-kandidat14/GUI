import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InData {
    public static void main(String[] args) throws IOException {
        String fileName = "Exempel.txt";
 
        List<String> lines = Files.readAllLines(Paths.get(fileName),
				Charset.defaultCharset());
		String[] points = lines.toArray(new String[lines.size()]);
		
		System.out.println(points);
	}
}