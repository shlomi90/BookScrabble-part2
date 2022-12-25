package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class IOSearcher {

    public static boolean search(String word, String... files) throws IOException {
        for (String string : files) {
            BufferedReader reader = new BufferedReader(new FileReader(string));
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (line.contains(word)) {
                    reader.close();
                    return true;
                }
            }
        }
        return false;

    }

}
