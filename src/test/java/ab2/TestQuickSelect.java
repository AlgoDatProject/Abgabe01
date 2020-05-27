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
    private int[] sortedInGroups;
    private int[] sorted;
    private int[] medians;
    private int[] pivots;
    private Ab2Impl quickSelect;

    @BeforeEach
    public void init() {
        myArr = new int[]{10, 20, 8, 4, 7, 1, 11, 6, 4, 2, 22, 5};
        sortedInGroups = new int[]{4, 7, 8, 10, 20, 1, 2, 4, 6, 11, 5, 22};
        sorted = new int[]{1, 2, 4, 4, 5, 6, 7, 8, 10, 11, 20, 22};
        medians = new int[]{8, 4, 22};
        pivots = new int[]{8, 10, 20, 4, 7};
        quickSelect = new Ab2Impl();

    }

    @AfterEach
    public void tearDown() {
        myArr = null;
        sortedInGroups = null;
        sorted = null;
        medians = null;
        quickSelect = null;
        pivots = null;
    }


    @Test
    public void testSortGroup() {
        Assertions.assertArrayEquals(sortedInGroups, quickSelect.sortGroups(myArr));
    }

    @Test
    public void testMedians() {
        Assertions.assertArrayEquals(medians, quickSelect.getMedians(sortedInGroups));
    }

    @Test
    public void testCornerCaseMedians() {
        int[] arr = new int[]{1, 4, 6, 8, 13, 7, 10, 13, 15, 20, 17, 21, 29, 30};
        int[] exp = new int[]{6, 13, 29};
        Assertions.assertArrayEquals(exp, quickSelect.getMedians(arr));
    }

    @Test
    public void testPartitioning() {
        int real = quickSelect.partition(pivots, 10);
        int expPos = 3;
        Assertions.assertEquals(expPos, real);

    }

    @Test
    public void testQuickSingleCall() {
        int k = quickSelect.quickselect(myArr, 7);
        Assertions.assertEquals(sorted[6], k);
    }


    @Test
    public void testQuickSelect() {
        for (int i = 0; i < myArr.length; i++) {
            int k = quickSelect.quickselect(myArr, i + 1);
            Assertions.assertEquals(sorted[i], k);
        }

    }

    @Test
    public void testPartitionMore() {
        int pivot = 7;
        quickSelect.partition(myArr, pivot);
        for (int i = 0; i < myArr.length; i++) {
            if (myArr[i] < pivot) {
                for (int j = 0; j < myArr.length; j++) {
                    if (myArr[j] == pivot) break;
                    Assertions.assertTrue(myArr[j] < pivot);
                }
            } else {
                for (int j = myArr.length - 1; j > 0; j--) {
                    if (myArr[j] == pivot) break;
                    Assertions.assertTrue(myArr[j] > pivot);
                }
            }
        }
    }


}
