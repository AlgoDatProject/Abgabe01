package ab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ab1Test {
	@Test
	public void failBecauseOfMissingTests() {
		Assertions.fail("Test will follow");
	}

@Test
public void TestCorrectElement(){
	int arr [] = {1,2,3,4,5,6};
	int search = 5;

	Sort s = new Sort();

	int erg = s.binarySearch(arr,search);

	Assert.assertEquals(4,erg);
}

@Test
public void TestElementNotFound (){
	int arr [] = {1,2,3,4,5,6};
	int search = 8;

	Sort s = new Sort();

	int erg = s.binarySearch(arr,search);

	Assert.assertEquals(-1,erg);
}


}
