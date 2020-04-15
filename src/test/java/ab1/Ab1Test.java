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

    @BeforeEach
    public void init() {
        impl = new Ab1Impl();
        arr = new int[]{1, 2, 3, 4, 5, 6};
        dataRaw = new Integer[]{3,6,1,10,5,7,2,9,8,4};
        dataSorted = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        a = new Integer[]{36, 42, 47, 19, 98, 62, 98, 14, 4, 21, 71, 83, 93, 40, 63, 77, 88, 68, 5, 43, 11, 43, 80, 92};
        as = new Integer[]{4, 5, 11, 14, 19, 21, 36, 40, 42, 43, 43, 47, 62, 63, 68, 71, 77, 80, 83, 88, 92, 93, 98, 98};


    }

    @AfterEach
    public void dispose() {
        impl = null;
        arr = null;
        dataRaw = null;
        dataSorted = null;
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
    public void testShellSort(){

        impl.shellSort(dataRaw);
        Assertions.assertArrayEquals(dataSorted, dataRaw);
    }

    @Test
    public void testShellSortLongArr(){

        impl.shellSort(a);
        Assertions.assertArrayEquals(as, a);
    }

    @Test
    public void testQuickSort(){

        impl.quickSortStable(dataRaw);
        Assertions.assertArrayEquals(dataSorted,dataRaw);

    }

    @Test
    public void testQuickSortLongArr(){

        impl.quickSortStable(a);
        Assertions.assertArrayEquals(a,as);

    }

    @Test
    public void testMatrizenMult(){
        int[][]m1={{1,2,3},{1,2,3},{1,2,3}};
        int[][]m2={{2,3,4},{2,3,4},{2,3,4}};
        int[][]m3={{12,18,24},{12,18,24},{12,18,24}};



        Assertions.assertEquals(m3,impl.mult(m1,m2));
    }




}
