package intlist;




import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class IntListTest {

	@Test
	void test() {


		IntList intList = new IntList();
		intList.addElement(42);
		int[] elements = intList.getElements();
		assertEquals(42,elements[0]);
		assertArrayEquals(new int[] {42},elements);
		elements[0] = 43;
		assertEquals(42,intList.getElementAt(0));
		// representation exposure
		
	}


}


