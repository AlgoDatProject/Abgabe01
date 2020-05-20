package ab2;

import ab2.impl.Nachnamen.Ab2Impl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*********************************
 Created by Fabian Oraze on 19.05.20
 *********************************/

public class TestQuickSelect {

    private int[] myArr;
    private int[] sorted;
    private int[] medians;
    private int[] pivots;
    private Ab2Impl quickSelect;

    @BeforeEach
    public void init() {
        myArr = new int[]{10, 20, 8, 4, 7, 1, 11, 6, 3, 2};
        sorted = new int[]{4, 7, 8, 10, 20, 1, 2, 3, 6, 11};
        medians = new int[]{8, 3};
        pivots = new int[]{8, 10, 20, 4, 7};
        quickSelect = new Ab2Impl();

    }

    @AfterEach
    public void tearDown() {
        myArr = null;
        sorted = null;
        medians = null;
        quickSelect = null;
        pivots = null;
    }


    @Test
    public void testSortGroup() {
        Assertions.assertArrayEquals(sorted, quickSelect.sortGroups(myArr));
    }

    @Test
    public void testMedians() {
        Assertions.assertArrayEquals(medians, quickSelect.getMedians(sorted));
    }

    @Test
    public void testCornerCaseMedians() {
        int[] arr = new int[]{1, 4, 6, 8, 13, 7, 10, 13, 15, 20, 17, 21, 29, 30};
        int[] exp = new int[]{6, 13, 29};
        Assertions.assertArrayEquals(exp, quickSelect.getMedians(arr));
    }

    @Test
    public void testPartitioning() {
        int real = quickSelect.partition(pivots, 7);
        int[] expArr = new int[]{4, 7, 20, 10, 8};
        int expPos = 1;
        Assertions.assertArrayEquals(expArr, pivots);
        Assertions.assertEquals(expPos, real);

    }

    @Test
    public void testQuickSelect() {
        int k = quickSelect.quickselect(myArr, 8);
        Assertions.assertEquals(11, k);
    }

}
