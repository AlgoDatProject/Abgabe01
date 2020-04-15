package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

    @Override
    public int binarySearch(int[] arr, int el) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int index = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid] == el) { //Checks if the int element is in the middle of the Array
                return index = mid; //if yes, the index which is going to be returned gets the position of the element in the Array
            } else if (arr[mid] > el) { //Ignore right half
                high = mid - 1;
            } else if (arr[mid] < el) {//ignore left half
                low = mid + 1;
            }
        }
        if (low > high) {
            return index = -1; //Element was not present
        }

        return index;
    }


    @Override
    public void shellSort(Integer[] data) {

        //dividing our length each time by 2 will decrease the gap distance every iteration and eventually end in a gap of only 1
        for (int gap = data.length / 2; gap > 0; gap /= 2) {

            //iterate over the elements on the right half of the gap
            for (int right = gap; right < data.length; right++) {

                //to store the current value of the element which will be later compared and
                //the index of the current gap value
                int compare = data[right];
                int temp = right;

                while (temp >= gap && data[temp - gap] > compare) {

                    //while is only executed if the two elements separated by the gap are in the wrong order
                    //then it switches the lower one to the right place
                    data[temp] = data[temp - gap];

                    //reducing temp by the gap amount ensures that while loop will break after temp gets lower than gap
                    temp -= gap;
                }

                //now the lesser number has two switch to the left
                data[temp] = compare;
            }

        }

    }


    public void quickSortStable(Integer[] data) {
        sort(data, 0, data.length - 1);  //calling the private recursive method with the full array and the first and last indicies
    }

    private void sort(Integer[] arr, int low, int high) {
        if (low < high + 1) { //Checks if there is more than one Item to sort
            int part = parition(arr, low, high); //Gets a new Pivot-Element
            sort(arr, low, part - 1); //Recursive call for the left partition
            sort(arr, part + 1, high); //Recursive callf for the right partition
        }

    }

    private void swap(Integer[] arr, int pos1, int pos2) { // Utility funktion for swaping positions in the Array
        int holder = arr[pos1]; //Placeholder for the value on position1
        arr[pos1] = arr[pos2];
        arr[pos2] = holder;
    }

    private int getPivot(int low, int high) { //Receives low and high index for the partitions
        return (high + low) / 2;  //Returns random number between the range of low and high
    }

    private int parition(Integer[] arr, int low, int high) {
        swap(arr, low, getPivot(low, high)); //Getting an Pivot within the given range of low and high + Swap swaps pivot to the leftmost position
        int stop = low + 1; // Left pointer stop is our border; Setting it just to the right of the pivot
        for (int i = stop; i <= high; i++) { // Iterate thorugh items and compare them to the pivot (arr[low])
            if (arr[i] < arr[low]) {
                swap(arr, i, stop++); //Swap it with the border
            }
        }
        swap(arr, low, stop - 1); //Swap pivot to right position and return index of the pivot
        return stop - 1;
    }


    @Override
    public int[][] mult(int[][] m1, int[][] m2) {

        int[][] result;

        int k = 3;                 // Dimension k

        if (m1.length == m2.length && m1[0].length == m2[0].length && m1.length == m2[0].length) {   //check if the arrays are the same length

            if (m1.length >= k) {    //case one: Strassen Algorithmus
                int n = m1.length;
                result = new int[n][n];


                if (n == 1) {
                    result[0][0] = m1[0][0] * m2[0][0];
                } else {
                    int[][] m111 = new int[n / 2][n / 2];
                    int[][] m112 = new int[n / 2][n / 2];
                    int[][] m121 = new int[n / 2][n / 2];
                    int[][] m122 = new int[n / 2][n / 2];
                    int[][] m211 = new int[n / 2][n / 2];
                    int[][] m212 = new int[n / 2][n / 2];
                    int[][] m221 = new int[n / 2][n / 2];
                    int[][] m222 = new int[n / 2][n / 2];

                    split(m1, m111, 0, 0);             //Dividing matrix into 4 halves
                    split(m1, m112, 0, n / 2);
                    split(m1, m121, n / 2, 0);
                    split(m1, m122, n / 2, n / 2);

                    split(m2, m211, 0, 0);
                    split(m2, m212, 0, n / 2);
                    split(m2, m221, n / 2, 0);
                    split(m2, m222, n / 2, n / 2);

                    int[][] M1 = mult(add(m111, m122), add(m211, m222)); //auxiliary matrix
                    int[][] M2 = mult(add(m121, m122), m211);
                    int[][] M3 = mult(m111, sub(m212, m222));
                    int[][] M4 = mult(m122, sub(m221, m211));
                    int[][] M5 = mult(add(m111, m112), m222);
                    int[][] M6 = mult(sub(m121, m111), add(m211, m212));
                    int[][] M7 = mult(sub(m112, m122), add(m221, m222));

                    int[][] C11 = add(sub(add(M1, M4), M5), M7); //result
                    int[][] C12 = add(M3, M5);
                    int[][] C21 = add(M2, M4);
                    int[][] C22 = add(sub(add(M1, M3), M2), M6);

                    join(C11, result, 0, 0); //join 4 halves into one result matrix
                    join(C12, result, 0, n / 2);
                    join(C21, result, n / 2, 0);
                    join(C22, result, n / 2, n / 2);

                    return result;

                }

            }
        } else if (m1.length == m2[0].length) {
            result = new int[m1.length][m2[0].length];//case:normal multiplication matrix
            int i, j, l;
            for (i = 0; i < m1.length; i++) {
                for (j = 0; j < m2[0].length; j++) {
                    int sum = 0;
                    for (l = 0; l < m2.length; l++) {
                        sum = sum + m1[i][l] * m2[l][j];
                        result[i][j] = sum;
                    }
                }
            }
            return result;


        }


        //return new int[0][];//vorgegeben ????????
        return null;
    }


    public int[][] sub(int[][] m1, int[][] m2) { //function to sub two matrices
        int n = m1.length;
        int[][] m3 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m3[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return m3;

    }

    public int[][] add(int[][] m1, int[][] m2) { //function to add two matrices
        int n = m1.length;
        int[][] m3 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m3[i][j] = m1[i][j] + m2[i][j];

            }
        }
        return m3;
    }

    public void split(int[][] A, int[][] B, int i, int j) { //function to split the matrix into little matrices
        for (int i1 = 0, i2 = i; i1 < B.length; i1++, i2++) {
            for (int j1 = 0, j2 = j; j1 < B.length; j1++, j2++) {
                B[i1][j1] = A[i2][j2];
            }

        }
    }

    public void join(int[][] B, int[][] A, int i, int j) { //function to join the matrices into one matrix
        for (int i1 = 0, i2 = i; i1 < B.length; i1++, i2++) {
            for (int j1 = 0, j2 = j; j1 < B.length; j1++, j2++) {
                A[i2][j2] = B[i1][j1];
            }
        }
    }
}
