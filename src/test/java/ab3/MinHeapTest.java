package ab3;

import ab3.impl.Oraze_Röttl_Miklau.AB3Impl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

/*********************************
 Created by Fabian Oraze on 07.07.20
 *********************************/

public class MinHeapTest {

    ArrayList<Integer> listFrequency;
    ArrayList<Character> charList;
    private AB3Impl coder;

    @BeforeEach
    public void init() {
        coder = new AB3Impl();
        listFrequency = new ArrayList<>();
        listFrequency.add(0, 10);
        listFrequency.add(1, 3);
        listFrequency.add(2, 12);
        listFrequency.add(3, 30);
        listFrequency.add(4, 7);
        listFrequency.add(5, 8);
        listFrequency.add(6, 20);

        charList = new ArrayList<>();
        charList.add(0, 'd');
        charList.add(1, 'a');
        charList.add(2, 'e');
        charList.add(3, 'g');
        charList.add(4, 'b');
        charList.add(5, 'c');
        charList.add(6, 'f');

    }

    @AfterEach
    public void reset() {
        listFrequency = null;
        charList = null;
        coder = null;
    }





}
