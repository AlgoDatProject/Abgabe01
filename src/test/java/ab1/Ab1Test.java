package ab1;

import ab1.impl.Nachnamen.Ab1Impl;
import org.junit.jupiter.api.*;

public class Ab1Test {
    private Ab1Impl impl;
    private int arr[];
    private Integer dataRaw[];
    private Integer dataSorted[];
    private Integer a[];
    private Integer as[];
    private int m1[][];
    private int m2[][];
    private int mErg[][];
    private int m21[][];
    private int m22[][];
    private int m2Erg[][];

    @BeforeEach
    public void init() {
        impl = new Ab1Impl();
        arr = new int[]{1, 2, 3, 4, 5, 6};
        dataRaw = new Integer[]{3, 6, 1, 10, 5, 7, 2, 9, 8, 4};
        dataSorted = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        a = new Integer[]{36, 42, 47, 19, 98, 62, 98, 14, 4, 21, 71, 83, 93, 40, 63, 77, 88, 68, 5, 43, 11, 43, 80, 92};
        as = new Integer[]{4, 5, 11, 14, 19, 21, 36, 40, 42, 43, 43, 47, 62, 63, 68, 71, 77, 80, 83, 88, 92, 93, 98, 98};
        m1 = new int[][]{{1, 2, 3, 1},
                {1, 2, 3, 1},
                {1, 2, 3, 1},
                {1, 1, 1, 1}};

        m2 = new int[][]{{2, 3, 4, 1},
                {2, 3, 4, 1},
                {2, 3, 4, 1},
                {1, 1, 1, 1}};
        ;

        mErg = new int[][]{{13, 19, 25, 7},
                {13, 19, 25, 7},
                {13, 19, 25, 7},
                {7, 10, 13, 4}};

        m21 = new int[][]{{2, 2, 3},
                {1, 2, 1},
                {-1, 2, 3}};

        m22 = new int[][]{{-1, 2, 3},
                {4, 1, 1},
                {2, -2, 3}};

        m2Erg = new int[][]{{12, 0, 17},
                {9, 2, 8},
                {15, -6, 8}};


    }

    @AfterEach
    public void dispose() {
        impl = null;
        arr = null;
        dataRaw = null;
        dataSorted = null;
        a = null;
        as = null;
        m1 = null;
        m2 = null;
        mErg = null;
    }


    @Test
    public void testCorrectElementBinarySearch() {
        int search = 5;

        int erg = impl.binarySearch(arr, search);

        Assertions.assertEquals(4, erg);
    }

    @Test
    public void testElementNotFoundBinarySearch() {
        int search = 8;

        int erg = impl.binarySearch(arr, search);

        Assertions.assertEquals(-1, erg);

    }

    @Test
    public void testShellSort() {

        impl.shellSort(dataRaw);
        Assertions.assertArrayEquals(dataSorted, dataRaw);
    }

    @Test
    public void testShellSortLongArr() {

        impl.shellSort(a);
        Assertions.assertArrayEquals(as, a);
    }

    @Test
    public void testQuickSort() {

        impl.quickSortStable(dataRaw);
        Assertions.assertArrayEquals(dataSorted, dataRaw);

    }

    @Test
    public void testQuickSortLongArr() {

        impl.quickSortStable(a);
        Assertions.assertArrayEquals(a, as);

    }

    @Test
    public void testMatrixMult4x4() {

        int[][] matrix = impl.mult(m1, m2);

        Assertions.assertArrayEquals(mErg, matrix);

    }

    /*@Test
    public void testMatrixMult3x3() {
        int[][] matrix = impl.mult(m21, m22);

        Assertions.assertArrayEquals(m2Erg, matrix);
    }*/


}
