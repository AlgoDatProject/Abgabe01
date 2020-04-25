package ab1;

import ab1.impl.Nachnamen.Ab1Impl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Ab1Test {

    private Random rand = new Random(System.currentTimeMillis());

    private static Ab1 ab1Impl = new Ab1Impl();

    private static int pts = 0;

    private static int NUM_TESTS = 100;
    private static int ARRAY_SIZE_SMALL = 1000;
    private static int ARRAY_SIZE_HUGE = 100000;

    private static int SIZE_MAT = 200;

    @Test
    public void testBinarySearch() {
        for (int i = 0; i < NUM_TESTS; i++) {
            int[] data = getRandomArray(ARRAY_SIZE_HUGE);
            Arrays.sort(data);

            for (int j = 0; j < NUM_TESTS; j++) {
                // Element sicher vorhanden
                int value = data[rand.nextInt(ARRAY_SIZE_HUGE)];

                int pos = ab1Impl.binarySearch(data, value);

                assertEquals(value, data[pos]);
            }

            for (int j = 0; j < NUM_TESTS / 2; j++) {
                // Element eventuell vorhanden
                int value = Math.abs(rand.nextInt(2 * ARRAY_SIZE_HUGE));

                int pos = ab1Impl.binarySearch(data, value);
                int posRef = Arrays.binarySearch(data, value);
                if (posRef < 0)
                    posRef = -1;

                if (posRef >= 0)
                    assertEquals(value, data[pos]);
                else
                    assertEquals(-1, pos);
            }

        }

        pts += 2;
    }

    @Test
    public void testShellSort() {
        for (int i = 0; i < NUM_TESTS; i++) {
            Integer[] data = getRandomArrayInteger(ARRAY_SIZE_SMALL);

            ab1Impl.shellSort(data);

            assertEquals(true, isSorted(data));
        }

        pts += 3;
    }

    @Test
    public void testQuickSort() {
        for (int i = 0; i < NUM_TESTS; i++) {
            Integer[] data = getRandomArrayInteger(ARRAY_SIZE_HUGE);

            ab1Impl.quickSortStable(data);

            assertEquals(true, isSorted(data));
        }

        pts += 2;
    }

    @Test
    public void testQuickSortStable() {
        for (int i = 0; i < NUM_TESTS; i++) {
            Integer[] data = getRandomArrayInteger(ARRAY_SIZE_SMALL);
            Integer[] dataOriginal = copyArray(data);

            ab1Impl.quickSortStable(data);

            assertEquals(true, isSorted(data));
            assertEquals(true, checkStableSorting(data, dataOriginal));
        }

        pts += 3;
    }

    @Test
    public void testMatMultSquare() {
        for (int i = 0; i < NUM_TESTS; i++) {
            int[][] mat1 = getRandomMat(SIZE_MAT, SIZE_MAT);
            int[][] mat2 = getRandomMat(SIZE_MAT, SIZE_MAT);

            int[][] mat3 = ab1Impl.mult(mat1, mat2);

            assertTrue(checkMat(matMult(mat1, mat2), mat3));
        }

        pts += 2;
    }

    @Test
    public void testMatMultNotSquare1() {
        for (int i = 0; i < NUM_TESTS; i++) {
            int[][] mat1 = getRandomMat(SIZE_MAT, SIZE_MAT / 2);
            int[][] mat2 = getRandomMat(SIZE_MAT / 2, SIZE_MAT);

            int[][] mat3 = ab1Impl.mult(mat1, mat2);

            assertTrue(checkMat(matMult(mat1, mat2), mat3));
        }

        pts += 1;
    }

    @Test
    public void testMatMultNotSquare2() {
        for (int i = 0; i < NUM_TESTS; i++) {
            int[][] mat1 = getRandomMat(SIZE_MAT, SIZE_MAT / 2);
            int[][] mat2 = getRandomMat(SIZE_MAT / 2, SIZE_MAT / 2);

            int[][] mat3 = ab1Impl.mult(mat1, mat2);

            assertTrue(checkMat(matMult(mat1, mat2), mat3));
        }

        pts += 1;
    }

    @Test
    public void testMatMultCorrectness() {
        int[][] mat1 = {
                new int[]{5, 6, 1},
                new int[]{-2, -1, 3},
                new int[]{4, 7, -4}
        };

        int[][] mat2 = {
                new int[]{0, 2, 5},
                new int[]{6, 8, 3},
                new int[]{4, -2, -1}
        };

        int[][] expected = {
                new int[]{40, 56, 42},
                new int[]{6, -18, -16},
                new int[]{26, 72, 45}
        };

        int[][] actual = ab1Impl.mult(mat1, mat2);

        assertArrayEquals(expected[0], actual[0]);
        assertArrayEquals(expected[1], actual[1]);
        assertArrayEquals(expected[2], actual[2]);

        pts += 1;
    }

    @AfterAll
    public static void printPts() {
        System.out.println("Punkte: " + pts);
    }

    private boolean checkMat(int[][] mat1, int[][] mat2) {
        if (mat1.length != mat2.length)
            return false;

        for (int i = 0; i < mat1.length; i++) {
            if (!Arrays.equals(mat1[i], mat2[i]))
                return false;
        }

        return true;
    }

    private boolean checkStableSorting(Integer[] data, Integer[] dataOriginal) {
        for (int i = 0; i < data.length; i++) {
            Integer curValue = data[i];

            // lese die gleichen Keys entsprechend der Reihenfolge aus den Daten
            List<Integer> filteredDataSameValue = Arrays.stream(data).filter(d -> d.intValue() == curValue.intValue()).collect(Collectors.toList());
            List<Integer> filteredDataOriginalSameValue = Arrays.stream(dataOriginal).filter(d -> d.intValue() == curValue.intValue()).collect(Collectors.toList());

            if (filteredDataSameValue.size() != filteredDataOriginalSameValue.size())
                return false;

            for (int j = 0; j < filteredDataSameValue.size(); j++) {
                // teste, ob die Integer Objekte gleich sind (dh die Reihenfolge stimmer -> stabil)
                if (filteredDataSameValue.get(j) != filteredDataOriginalSameValue.get(j))
                    return false;
            }
        }

        return true;
    }

    private int[] getRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Math.abs(rand.nextInt(2 * size));
        }
        return arr;
    }

    private int[][] getRandomMat(int x, int y) {
        int[][] mat = new int[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                mat[i][j] = Math.abs(rand.nextInt(mat.length));
            }
        }

        return mat;
    }

    private Integer[] getRandomArrayInteger(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.valueOf(Math.abs(rand.nextInt(2 * size)));
        }
        return arr;
    }

    private boolean isSorted(Integer[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1])
                return false;
        }
        return true;
    }

    private Integer[] copyArray(Integer[] data) {
        return data.clone();
    }

    private int[][] matMult(int[][] m1, int[][] m2) {
        int dim1 = m1.length;    // x of m1
        int dim2 = m2.length;   // x of m2 = y of m1
        int dim3 = m2[0].length; // y of m2

        int[][] result = new int[dim1][dim3];

        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim3; j++) {
                int val = 0;
                for (int k = 0; k < dim2; k++) {
                    val += m1[i][k] * m2[k][j];
                }
                result[i][j] = val;
            }
        }

        return result;
    }
}
