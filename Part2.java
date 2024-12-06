import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Point;

public class Part2 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        char[][] wordSearch = new char[999][999];
        int height = 0;
        int width = 0;
        for (; scan.hasNext(); height++) {
            String line = scan.nextLine();
            width = line.length();
            char[] lineArr = line.toCharArray();
            wordSearch[height] = lineArr;
        }
        scan.close();

        ArrayList<Point> foundPoints = new ArrayList<>();

        /**
         * M.S
         * .A.
         * M.S
         * 
         * M.M
         * .A. // must always be the same
         * S.S
         * (0,0) ( , ) ( , )
         * ( , ) (1,1) ( , )
         * (0,2) ( , ) (2,2)
         */

        int found = 0;
        String targetWord = "MAS";

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // find an X to start.
                if (wordSearch[y][x] == 'A' && !foundPoints.contains(new Point(x, y))) {
                    /**
                     * M S
                     * A OR A
                     * S M
                     * AND
                     * M S
                     * A OR A
                     * S M
                     */

                    if (x - 1 >= 0 && x + 1 < width && y - 1 >= 0 && y + 1 < height) {
                        /**
                         * M S
                         * A OR A
                         * S M
                         */
                        String word = new String(
                                new char[] { wordSearch[y - 1][x - 1], wordSearch[y][x], wordSearch[y + 1][x + 1] });
                        if (word.equals("MAS") || word.equals("SAM")) {
                            String word2 = new String(
                                new char[] {wordSearch[y + 1][x - 1], wordSearch[y][x], wordSearch[y - 1][x + 1]}
                            );
                            if(word2.equals("MAS") || word2.equals("SAM")) {
                                found++;
                                foundPoints.add(new Point(x,y));
                            }
                        }
                    }
                }
            }
        }

        System.out.println(found);
    }
}