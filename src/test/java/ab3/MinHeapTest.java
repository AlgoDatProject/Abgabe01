package ab3;

import ab3.impl.Oraze_Röttl_Miklau.AB3Impl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/*********************************
 Created by Fabian Oraze on 07.07.20
 *********************************/

public class MinHeapTest {

    private ArrayList<Integer> listFrequency;
    private ArrayList<Character> charList;
    private AB3Impl coder;
    private AB3Impl.MinHeap heap;


    @BeforeEach
    public void init() {
        coder = new AB3Impl();
        listFrequency = new ArrayList<>();
        listFrequency.add(0, 3);
        listFrequency.add(1, 7);
        listFrequency.add(2, 8);
        listFrequency.add(3, 10);
        listFrequency.add(4, 12);
        listFrequency.add(5, 20);
        listFrequency.add(6, 30);

        charList = new ArrayList<>();
        charList.add(0, 'a');
        charList.add(1, 'b');
        charList.add(2, 'c');
        charList.add(3, 'd');
        charList.add(4, 'e');
        charList.add(5, 'f');
        charList.add(6, 'g');
        heap = coder.new MinHeap(listFrequency.size());

    }

    @AfterEach
    public void reset() {
        listFrequency = null;
        charList = null;
        coder = null;
        heap = null;
    }

    @Test
    public void testHeapAdd() {
        addToHeap();
        for (int i = 0; i < listFrequency.size(); i++) {
            try {
                Assertions.assertEquals('a' + i, heap.getNode(i).symbol);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testTreeAfterInternalNodeCreations() {
        addToHeap();
        while (heap.getSize() > 1) {
            heap.createInternalNode();
        }
        try {
            Assertions.assertEquals(90, heap.getNode(0).frequency);
            Assertions.assertEquals(1, heap.getSize());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


    private void addToHeap() {
        for (int i = 0; i < listFrequency.size(); i++) {
            heap.add(coder.new HuffmanNode(listFrequency.get(i), charList.get(i)));
        }
    }


}
