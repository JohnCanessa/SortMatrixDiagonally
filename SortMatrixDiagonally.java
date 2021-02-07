import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1329. Sort the Matrix Diagonally
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 */
public class SortMatrixDiagonally {


    /**
     * Given an m x n matrix mat of integers, 
     * sort each matrix diagonal in ascending order 
     * and return the resulting matrix.
     * 
     * Runtime: 7 ms, faster than 65.97% of Java online submissions.
     * Memory Usage: 39.8 MB, less than 80.41% of Java online submissions.
     */
    static int[][] diagonalSort1(int[][] mat) {

        // **** sanity checks ****
        if (mat.length == 1 || mat[0].length == 1)
            return mat;

        // **** initialization ****
        int rows            = mat.length;
        int cols            = mat[0].length;
        List<Integer> lst   = new ArrayList<Integer>();
    
        // **** go up along the left side of the matrix ****
        for (int rr = rows - 2; rr >= 0; rr--) {

            // **** clear the list ****
            lst.clear();

            // **** go diagonally (copy to list) ****
            for (int r = rr, c = 0; r < rows && c < cols; r++, c++) {
                lst.add(mat[r][c]);
            }

            // **** sort the list ****
            lst.sort((a,b) -> a - b);

            // **** go diagonally (update matrix) ****
            for (int r = rr, c = 0; r < rows && c < cols; r++, c++) {
                mat[r][c] = lst.get(c);
            }
        }

        // **** go right along the upper side of the matrix ****
        for (int cc = 1; cc < cols - 1; cc++) {

            // **** clear list ****
            lst.clear();

            // **** go diagonally (copy to list)****
            for (int r = 0, c = cc; r < rows && c < cols; r++, c++) {
                lst.add(mat[r][c]);
            }

            // **** sort the list ****
            lst.sort((a,b) -> a - b);

            // **** go diagonally (update matrix) ****
            for (int r = 0, c = cc; r < rows && c < cols; r++, c++) {
                mat[r][c] = lst.get(r);
            }
        }

        // **** return diagonally sorted matrix ****
        return mat;
    }


    /**
     * Given an m x n matrix mat of integers, 
     * sort each matrix diagonal in ascending order 
     * and return the resulting matrix.
     * 
     * Runtime: 5 ms, faster than 83.88% of Java online submissions.
     * Memory Usage: 39.8 MB, less than 70.60% of Java online submissions.
     */
    static int[][] diagonalSort(int[][] mat) {

        // **** sanity checks ****
        if (mat.length == 1 || mat[0].length == 1)
            return mat;

        // **** initialization ****
        int rows                    = mat.length;
        int cols                    = mat[0].length;
        PriorityQueue<Integer> pq   = new PriorityQueue<Integer>();
    
        // **** go up along the left side of the matrix ****
        for (int rr = rows - 2; rr >= 0; rr--) {

            // **** go diagonally (copy to list) ****
            for (int r = rr, c = 0; r < rows && c < cols; r++, c++) {
                pq.add(mat[r][c]);
            }

            // **** go diagonally (update matrix) ****
            for (int r = rr, c = 0; r < rows && c < cols; r++, c++) {
                mat[r][c] = pq.remove();
            }
        }

        // **** go right along the upper side of the matrix ****
        for (int cc = 1; cc < cols - 1; cc++) {

            // **** go diagonally (copy to list)****
            for (int r = 0, c = cc; r < rows && c < cols; r++, c++) {
                pq.add(mat[r][c]);
            }

            // **** go diagonally (update matrix) ****
            for (int r = 0, c = cc; r < rows && c < cols; r++, c++) {
                mat[r][c] = pq.remove();
            }
        }

        // **** return diagonally sorted matrix ****
        return mat;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open a buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read matrix dimensions ****
        int[] rowCol = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int rows = rowCol[0];
        int cols = rowCol[1];

        // **** ****
        int[][] mat = new int[rows][];

        // **** read values for rows ****
        for (int r = 0; r < rows; r++) {
            mat[r] = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        }

        // **** close the buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< rows: " + rows + " cols: " + cols);
        System.out.println("main <<< mat: ");
        for (int r = 0; r < rows; r++)
            System.out.println(Arrays.toString(mat[r]));

        // **** diagonal sort matrix ****
        mat = diagonalSort(mat);

        // ???? ????
        System.out.println("main <<< result: ");
        for (int r = 0; r < rows; r++)
            System.out.println(Arrays.toString(mat[r]));
    }

}