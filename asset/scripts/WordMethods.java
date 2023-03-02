package asset.scripts;
import java.io.File;
import java.util.*;
import java.io.*;
public class WordMethods{
    private ArrayList<String> words = new ArrayList<>();
    private Integer currentLevel = 0;

    private static void fetchWords(){
        try {
            Scanner scan = new Scanner(new File(Default.DEFAULT_WORD_PATH));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        //test
    }
}