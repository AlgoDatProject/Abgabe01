package ab2.impl.Nachnamen;

import ab2.Ab2;

public class Ab2Impl implements Ab2 {

    @Override
    public void toHeap(int[] data) {
        for (int i = (data.length / 2) - 1; i >= 0 ; i--) {
            int a = i;
            while((a+1) * 2 - 1 < data.length ) {
                int right = (a + 1) * 2;
                int left = right - 1;
                if (right >= data.length) {
                    if (data[a] < data[left]) {
                        int t = data[a];
                        data[a] = data[left];
                        data[left] = t;
                        a = left;
                    } else break;
                } else {
                    if (data[a] < data[left] || data[a] < data[right]) {
                        if (data[right] < data[left]) {
                            int t = data[a];
                            data[a] = data[left];
                            data[left] = t;
                            a = left;
                        } else {
                            int t = data[a];
                            data[a] = data[right];
                            data[right] = t;
                            a= right;
                        }
                    } else break;
                }
            }
        }

    }

    @Override
    public int quickselect(int[] data, int k) {

        //divide into groups of 5 each
        data = sortGroups(data);

        //get array of medians
        int[] medians = getMedians(data);

        int pivot;

        //check if group smaller 5
        if (medians.length <= 5) {
            medians = sortGroups(medians);
            pivot = medians[medians.length / 2];
        } else {
            pivot = quickselect(medians, medians.length / 2);
        }

        int posPivot = partition(data, pivot);

        if (k - 1 > posPivot) {
            int[] dataRec = new int[data.length - posPivot - 1];
            int index = posPivot + 1;
            for (int i = 0; i < dataRec.length; i++) {
                dataRec[i] = data[index];
                index++;
            }
            return quickselect(dataRec, k - posPivot - 1);
        } else if (k - 1 < posPivot) {
            int[] dataRec = new int[posPivot];
            for (int i = 0; i < dataRec.length; i++) {
                dataRec[i] = data[i];
            }
            return quickselect(dataRec, k);
        } else {
            return pivot;
        }
    }

    /**
     * Helper method for quickSelect algorithm
     *
     * @param data  group sorted array
     * @param pivot element which should be used as a pivot element
     * @return the position of the pivot element in the partitioned array
     */

    public int partition(int[] data, int pivot) {
        int low = 0;
        int pos = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == pivot) {
                pos = i;
            }
        }
        swap(data, 0, pos);
        int stop = low + 1; // Left pointer stop is our border; Setting it just to the right of the pivot
        for (int i = stop; i < data.length; i++) { // Iterate thorugh items and compare them to the pivot (arr[low])
            if (data[i] < data[low]) {
                swap(data, i, stop++); //Swap it with the border
            }
        }
        swap(data, low, stop - 1); //Swap pivot to right position and return index of the pivot
        return stop - 1;
    }

    /**
     * helper method for partitioning method
     *
     * @param arr  data array
     * @param pos1 swap position with
     * @param pos2 position to swap
     */
    private void swap(int[] arr, int pos1, int pos2) {
        int holder = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = holder;
    }

    /**
     * Helper method for quickSelect algorithm
     *
     * @param data array of input data should be sorted in 5-element groups first
     * @return array of medians out of a given input array
     */
    public int[] getMedians(int[] data) {
        int length1 = data.length / 5;
        int lengthBoth = 0;
        if ((data.length % 5) != 0 && data.length > 5) {
            lengthBoth = length1 + 1;
        } else if ((data.length % 5) != 0) {
            lengthBoth = 1;
        } else {
            lengthBoth = length1;
        }

        int[] medians;

        //fill first part of medians
        int[] medians1 = new int[length1];
        int index = 2;
        for (int i = 0; i < length1; i++) {
            medians1[i] = data[index];
            index += 5;
        }

        //fill first part in final median array
        medians = new int[lengthBoth];
        for (int i = 0; i < medians1.length; i++) {
            medians[i] = medians1[i];
        }

        //get last median if it exists
        if (lengthBoth > length1) {
            int[] dataOff = new int[data.length % 5];
            for (int i = 0; i < dataOff.length; i++) {
                dataOff[i] = data[data.length - dataOff.length + i];
            }
            medians[lengthBoth - 1] = dataOff[dataOff.length / 2];
        }

        return medians;
    }

    /**
     * Helper method for quickSelect algorithm
     *
     * @param data unsorted data array
     * @return the data array but sorted by 5-step groups
     */
    public int[] sortGroups(int[] data) {
        if (data.length > 5) {
            for (int i = 0; i < data.length; i += 5) {

                //sort groups
                int index = i + 1;
                for (int j = i + 1; j < i + 5; j++) {

                    if (index == data.length) break;

                    int tempIndex = index;

                    //swap if the number with the greater index is bigger
                    while (data[tempIndex] < data[tempIndex - 1]) {
                        int var = data[tempIndex];
                        data[tempIndex] = data[tempIndex - 1];
                        data[tempIndex - 1] = var;
                        if (tempIndex - i - 1 == 0) {
                            break;
                        }
                        tempIndex--;
                    }
                    index++;
                }
            }
        } else {
            int index = 1;
            for (int i = 0; i < data.length; i++) {

                if (index == data.length) {
                    break;
                }

                int tempIndex = index;

                //swap if the number with the greater index is bigger
                while (data[tempIndex] < data[tempIndex - 1]) {
                    int var = data[tempIndex];
                    data[tempIndex] = data[tempIndex - 1];
                    data[tempIndex - 1] = var;
                    if (tempIndex - 1 == 0) {
                        break;
                    }
                    tempIndex--;
                }
                index++;
            }
        }
        return data;
    }

@Override
    public void insertIntoHashSet(int[] hashtable, int element) {
        int currentSize = hashtable.length;
        int hash = getHash(element, currentSize);

        if (contains(hashtable, element) == false) { // If the Hastable already contains the element nothing happens
            if (checkIfFull(hashtable) == false) { // If the Hashtable is full, nothing happens

              if (hash == 0) { //Check if the Hash equals zero,
                    if (hashtable[hash] != -1) {
                        HashIsZero(hashtable, hash, element); // When it is zero and der is no empty space, HashIsZero-Method is called
                    } else {
                        hashtable[hash] = element; //If there is an empty space, the element is stored here
                    }
                }

                else if (hash == hashtable.length) { //Check if the Hash equals the Array-Size
                    if (hashtable[hash] != 1) {
                        HashIsLength(hashtable, hash, element); //When yes and there is no empty space, HashIsLength-Method is called
                    } else {
                        hashtable[hash] = element;
                    }
                }
                
                 else if (hash > 0 && hash < hashtable.length && hashtable[hash] != -1) { //Check if the Hash is in between the max and the min and if there is no free space
                    FindPlace(hashtable, hash, element); //When there is no free space, we call FindPlace
                } else {
                    hashtable[hash] = element; //If there is free space, we store the element here

                }
            }
        }


    }

    private boolean contains(int[] hashmap, int element) {
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i] == element) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfFull(int[] hashmap) { //Checks if Array is full so it doesn`t go through the whole process
        for (int i = 0; i < hashmap.length; i++) {
            if (hashmap[i] == -1) {
                return false;
            }
        }
        return true;
    }

    private int getHash(int el, int tableSize) { // Calculate where the Element should get stored
        int hash = el % tableSize;
        return hash;
    }

    private void HashIsZero(int[] hashtable, int hash, int el) {
        hash = hashtable.length;

        while (hashtable[hash] != -1 && hash > 0) { //Reduce as long as there is no free space or it is bigger than 0
            hash--;
        }
        if (hash == 0 && hashtable[hash] == -1) { //When it is zero check if there is a free space, if yes store element here and return
            hashtable[hash] = el;

        } else if (hash == 0 && hashtable[hash] != -1) { // If no just return the table

        } else if (hashtable[hash] == -1) { // If there is a free space store the value here
            hashtable[hash] = el;

        }
    }

    private void HashIsLength(int[] hashtable, int hash, int el) {
        hash = hashtable.length;
        while (hashtable[hash] != -1 && hash > 0) { //Same as in HashIsZero
            hash--;
        }
        if (hash == 0 && hashtable[hash] == -1) {
            hashtable[hash] = el;
        } else if (hash == 0 || hashtable[hash] != -1) {

        } else if (hashtable[hash] == -1) {
            hashtable[hash] = el;

        }
    }

    private void FindPlace(int[] hashtable, int hash, int el) {
        while (hashtable[hash] != -1 && hash > 0) {
            hash--;
        }
        if (hashtable[hash] == -1) {
            hashtable[hash] = el;
            } else if (hash == 0 && hashtable[hash] == -1) {
            hashtable[hash] = el;
        } else if (hash == 0 && hashtable[hash] != -1) { // If there is no space call HashIsLength to check the upper Array
            HashIsLength(hashtable, hash, el);
        }
    }


@Override
    public boolean containedInHashSet(int[] hashtable, int element) {

        int save = getHash(element, hashtable.length);
        return findElement(hashtable, element, save);
    }


    public boolean findElement(int[] hashtable, int element, int save) {
        if (save == 0) {
            if (hashtable[save] == element) {
                return true;
            } else {
                for (int i = hashtable.length; i > 0; i--) {
                    if (hashtable[i] == element) {
                        return true;
                    }
                }
            }
        } else if (save == hashtable.length) {
            for (int i = hashtable.length; i > 0; i--) {
                if (hashtable[i] == element) {
                    return true;
                }
            }
        } else if (save > 0 && save < hashtable.length) {
            int counter = save;
            while (counter > 0) {
                if (hashtable[counter] == element) {
                    return true;
                }
                counter--;
            }
            for (int i = hashtable.length; i > save; i--) {
                if (hashtable[save] == element) {
                    return true;
                }
            }
        }
        return false;
    }
}
