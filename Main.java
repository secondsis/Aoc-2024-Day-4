import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Point;

public class Main {
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

        // populated 2d array
        // int[] forwardXMatch = new int[]{0, 1, 2, 3}; // {X, M, A, S}
        // int[] backwardXMatch = new int[]{0, -1, -2, -3}; // {S, A, M, X}
        // int[] upYMatch = new int[]{0, -1, -2,- 3};
        // int[] downYMatch = new int[]{0, 1, 2, 3};
        // int[][] diagonalUpRight = new int[][] {
        //     {0, 0}, {1, -1}, {2, -2}, {3, -3}
        // };

        ArrayList<Point> foundPoints = new ArrayList<>();

        int found = 0;
        String targetWord = "XMAS";

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // find an X to start.
                if(wordSearch[y][x] == 'X' && !foundPoints.contains(new Point(x, y))) {
                    // search for forwardXMatch, backwardXMatch, upYMatch, downYMatch, 
                    //forwardXMatch+upYMatch, forwardXMatch+downYMatch, backwardXMatch+upYMatch, backwardXMatch+downYMatch
                    if(x + 3 < width && y - 3 >= 0) {
                        //right, up
                        //wordSearch[y][x], wordSearch[y-1][x+1], wordSearch[y-2][x+2], wordSearch[y-3][x+3]
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y-1][x+1], wordSearch[y-2][x+2], wordSearch[y-3][x+3]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }

                    if(x + 3 < width && y + 3 < height) {
                        //right, down
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y+1][x+1], wordSearch[y+2][x+2], wordSearch[y+3][x+3]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }
                    if(x - 3 >= 0 && y - 3 >= 0) {
                        // left, up
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y-1][x-1], wordSearch[y-2][x-2], wordSearch[y-3][x-3]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }
                    if(x - 3 >= 0 && y + 3 < height) {
                        // left, down
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y+1][x-1], wordSearch[y+2][x-2], wordSearch[y+3][x-3]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }
                    if(x + 3 < width) {
                        // right
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y][x+1], wordSearch[y][x+2], wordSearch[y][x+3]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }

                    if(x - 3 >= 0) {
                        // left
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y][x-1], wordSearch[y][x-2], wordSearch[y][x-3]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }

                    if(y + 3 < height) {
                        // down
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y+1][x], wordSearch[y+2][x], wordSearch[y+3][x]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }

                    if(y - 3 >= 0) {
                        // up
                        char[] word = new char[]{wordSearch[y][x], wordSearch[y-1][x], wordSearch[y-2][x], wordSearch[y-3][x]};
                        String word2 = new String(word);
                        if(word2.equals(targetWord)) {
                            found++;
                            foundPoints.add(new Point(x, y));
                        }
                    }
                }
            }
        }

        System.out.println(found);
    }
}