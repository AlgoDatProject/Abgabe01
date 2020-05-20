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

        //check if group smaller 5
        if (data.length < 5) {
            return data[k];
        }

        //get array of medians
        int[] medians = getMedians(data);

        int pivot = quickselect(medians, ((data.length + 1) / 5) / 2);

        int posPivot = partition(data, pivot);

        if (k == posPivot) {
            return pivot;
        }
        if (k < posPivot) {
            int[] dataRec = new int[posPivot - 1];
            for (int i = 0; i < dataRec.length; i++) {
                dataRec[i] = data[i];
            }
            return quickselect(dataRec, k);
        } else {
            int[] dataRec = new int[data.length - pivot];
            int index = 0;
            for (int i = pivot; i < data.length; i++) {
                dataRec[index] = data[i];
                index++;
            }
            return quickselect(dataRec, k - posPivot);
        }

    }

    /**
     * Helper method for quickSelect algorithm
     *
     * @param data  group sorted array
     * @param pivot element which should be used as a pivot element
     * @return the position of the pivot element after the partitioning
     */
    public int partition(int[] data, int pivot) {
        int low = 0;
        int high = data.length - 1;
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (data[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;

        int pos = 0;
        for (int j = 0; j < high; j++) {
            if (data[j] == pivot) {
                pos = j;
                break;
            }
        }
        return pos;
    }

    /**
     * Helper method for quickSelect algorithm
     *
     * @param data array of input data should be sorted in 5-element groups first
     * @return array of medians out of a given input array
     */
    public int[] getMedians(int[] data) {
        int length;
        if ((data.length % 5) != 0) {
            length = data.length / 5 + 1;
        } else {
            length = data.length / 5;
        }
        int[] medians = new int[length];
        int index = 0;
        for (int i = 2; i < data.length; i += 5) {
            medians[index] = data[i];
            index++;
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
            for (int i = 0; i < data.length; i++) {

                int index = i + 1;
                if (index == data.length) {
                    break;
                }

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
