package ab2.impl.Nachnamen;

import ab2.Ab2;

public class Ab2Impl implements Ab2 {

    @Override
    public void toHeap(int[] data) {
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
    }

    @Override
    public boolean containedInHashSet(int[] hashtable, int element) {
        return false;
    }
}
