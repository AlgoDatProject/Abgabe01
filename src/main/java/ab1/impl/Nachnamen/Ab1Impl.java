package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	@Override
	public int binarySearch(int[] data, int element) {
 int low = 0;
 int high = data.length - 1;
 int mid = 0;
 int index = 0;

 while (low <= high){
	 mid = (low + high)/2;

	 if (data[mid] == element){ //Checks if the int element is in the middle of the Array
		 return index = mid; //if yes, the index which is going to be returned gets the position of the element in the Array
}
		 else if (data[mid] > element){ //Slicing the Array and checking if the element is greater or smaller than the mid. 
			 high = mid -1;
	 } else if (data[mid] < element){
		 low= mid+1;
	 }
 }
		 if (low > high){
		return index = -1; //In case we haven't been in the loop the index now has the integer -1 which tells us that the element couldn't be found
 	  }

		return index;
	}



	@Override
	public void shellSort(Integer[] data) {

	}

	@Override
	public void quickSortStable(Integer[] data) {

	}

	@Override
	public int[][] mult(int[][] m1, int[][] m2) {
		return new int[0][];
	}
}
