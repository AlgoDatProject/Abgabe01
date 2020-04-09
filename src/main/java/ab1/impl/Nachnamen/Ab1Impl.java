package ab1.impl.Nachnamen;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	@Override
	public int binarySearch(int[] data, int element) {
 int low = 0;
 int high = data.length()-1;
 int mid = 0;

 while (low <= high){
	 mid = (low + high)/2;

	 if (data[mid] == element){
		 System.out.prinln("Value is found at " + mid);
		 break;
}
}

		return 0;
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
