package asset.scripts;

import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Default {
    public static final String DEFAULT_IMAGE_PATH = "./asset/picture/default_image.png";
    public static final String DEFAULT_SAVED_PATH = "./asset/saved.txt";
    public static final String DEFAULT_WORD_PATH = "./asset/words.txt";
    public static final int MAX_CHAPTER=3;
    public static final int MAX_EPISODE=10;
    public static final String EPISODE="episode", CHAPTER="chapter", HIGHEST_SCORE="highest_score";
    public static Map<String, Integer> loadData() throws FileNotFoundException{
        Map<String, Integer> data = new HashMap<String, Integer>();
        Scanner file = new Scanner(new File(DEFAULT_SAVED_PATH));
        while(file.hasNextLine()){
            String[] d = file.nextLine().split(":");
            data.put(d[0], Integer.valueOf(d[1]));
        }
        return data;
    }
}