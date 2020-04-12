package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

    @Override
    public int binarySearch(int[] data, int element) {
        int low = 0;
        int high = data.length - 1;
        int mid = 0;
        int index = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (data[mid] == element) { //Checks if the int element is in the middle of the Array
                return index = mid; //if yes, the index which is going to be returned gets the position of the element in the Array
            } else if (data[mid] > element) { //Slicing the Array and checking if the element is greater or smaller than the mid.
                high = mid - 1;
            } else if (data[mid] < element) {
                low = mid + 1;
            }
        }
        if (low > high) {
            return index = -1; //In case we haven't been in the loop the index now has the integer -1 which tells us that the element couldn't be found
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

                while (temp >= gap &&data[temp - gap] > compare) {

                    //while is only executed if the two elements separated by the gap are in the wrong order
					//then it switches the lower one to the right place
					data[temp] = data[temp-gap];

					//reducing temp by the gap amount ensures that while loop will break after temp gets lower than gap
					temp -= gap;
                }

                //now the lesser number has two switch to the left
                data[temp] = compare;
            }

        }

    }

    @Override
    public void quickSortStable(Integer[] arr) {
        sort(arr,0,arr.length-1);  //calling the private recursive method with the full array and the first and last indicies
  }

  private void sort (int [] arr, int low, int high){
    if(low < high+1){ //Checks if there is more than one Item to sort
            int part = parition(arr, low, high); //Gets a new Pivot-Element
            sort(arr, low, part-1); //Recursive call for the left partition
            sort(arr, part+1,high); //Recursive callf for the right partition
        }

  }

  private void swap (int [] arr, int pos1, int pos2){ // Utility funktion for swaping positions in the Array
    int holder = arr[pos1]; //Placeholder for the value on position1
       arr[pos1] = arr[pos2];
       arr[pos2] = holder;
  }

  private int getPivot(int low, int high){

  }

  private int parition (int [] arr, int low, int high){

  }

    }

    @Override
    public int[][] mult(int[][] m1, int[][] m2) {
        return new int[0][];
    }
}
