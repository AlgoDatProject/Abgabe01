package ab1;

import ab1.impl.Nachnamen.Ab1Impl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

public class Ab1Test {
    private Ab1Impl impl;
    private int arr[];
    private Integer dataRaw[];
    private Integer dataSorted[];

    @BeforeEach
    public void init() {
        impl = new Ab1Impl();
        arr = new int[]{1, 2, 3, 4, 5, 6};
        dataRaw = new Integer[]{3,6,1,10,5,7,2,9,8,4};
        dataSorted = new Integer[]{1,2,3,4,5,6,7,8,9,10};

    }

    @AfterEach
    public void dispose() {
        impl = null;
        arr = null;
        dataRaw = null;
        dataSorted = null;
    }


    @Test
    public void TestCorrectElementBinarySearch() {
        int search = 5;


        int erg = impl.binarySearch(arr, search);

        Assertions.assertEquals(4, erg);
    }

    @Test
    public void TestElementNotFoundBinarySearch() {
        int search = 8;

        int erg = impl.binarySearch(arr, search);

        Assertions.assertEquals(-1, erg);
    }

    @Test
    public void TestShellSort(){

        impl.shellSort(dataRaw);
        Assertions.assertArrayEquals(dataSorted, dataRaw);
    }




}
