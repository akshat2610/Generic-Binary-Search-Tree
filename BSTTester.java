package bst;

import static org.junit.Assert.*;
import org.junit.Test;

public class BSTTester {
	@Test
	public void test_multiple_insertions_integers() {
		//But does integer wrapper implements Comparable<Integer> ????
		BST<Integer> integers 	= new BST<>();

		integers.insert(5);
		integers.insert(3);
		integers.insert(4);


	}

	@Test
	public void test_multiple_insertions_string() {
		BST<String> strings 	= new BST<>();

		strings.insert("C");
		strings.insert("B");
		strings.insert("A");
		strings.insert("F");

		strings.insert("Z");
		strings.insert("D");
		strings.insert("K");
		strings.insert("E");

		//skewed insertions
		strings.insert("L");
		strings.insert("M");
		strings.insert("N");
		strings.insert("O");

	}

	@Test
	public void test_find() {
		BST<String> strings 	= new BST<>();

		strings.insert("C");
		strings.insert("B");
		strings.insert("A");
		strings.insert("F");

		strings.insert("Z");
		strings.insert("D");
		strings.insert("K");
		strings.insert("E");

		//skewed insertions
		strings.insert("L");
		strings.insert("M");
		strings.insert("N");
		strings.insert("O");
		
		assertEquals("L", strings.find(strings.get_root(), "L").get_data());
		assertEquals("M", strings.find(strings.get_root(), "M").get_data());
		assertEquals(null, strings.find(strings.get_root(), "X"));
		
		assertEquals(true, strings.delete("F"));
		assertEquals(false, strings.delete("X"));
		assertEquals(null, strings.get_root().get_right_child());
	}

	@Test
	public void test_misc() {
		BST<String> strings 	= new BST<>();

		strings.insert("C");
		strings.insert("B");
		strings.insert("A");
		strings.insert("F");

		strings.insert("Z");
		strings.insert("D");
		strings.insert("K");
		strings.insert("E");
		
		BST<String>.Node<String> node = strings.find(strings.get_root(), "F");
		BST<String>.Node<String> node1 = strings.find(strings.get_root(), "F");
		
		assertEquals(false, node1.set_left_child(node));
	}
	
	@Test
	public void test_bst_with_mammals() {
		BST<Mammal> mammals		= new BST<>();
		
		Mammal serena			= new Mammal("Human", 150);
		Mammal dog				= new Mammal("Dog", 22);
		Mammal frog				= new Mammal("Frog", 1);
		Mammal rhino			= new Mammal("Rhino", 3000);
		
		Mammal tiger			= new Mammal("Tiger", 700);
		Mammal blue_whale		= new Mammal("Whale", 350000);
		Mammal something		= new Mammal("Something", 350);
		Mammal dolphin			= new Mammal("Dolphin", 110);
		
		mammals.insert(serena);
		mammals.insert(rhino);
		mammals.insert(dog);
		mammals.insert(frog);
		
		mammals.insert(blue_whale);
		mammals.insert(tiger);
		mammals.insert(dolphin);
		mammals.insert(something);
		
		assertEquals(serena, mammals.get_root().get_data());
		assertEquals(true, dog.equals(mammals.find(mammals.get_root(), serena).get_left_child().get_data()));
		assertEquals(true, frog.equals(mammals.find(mammals.get_root(), dog).get_left_child().get_data()));		
	}
	
	@Test
	public void test_size() {
		BST<String> strings 	= new BST<>();

		strings.insert("C");
		strings.insert("B");
		strings.insert("A");
		strings.insert("F");

		assertEquals(4, strings.get_size());
		
		strings.insert("Z");
		strings.insert("D");
		strings.insert("K");
		strings.insert("E");
		
		assertEquals(8, strings.get_size());
		
		strings.delete("Z");
		assertEquals(6, strings.get_size());
		
		strings.delete("D");
		assertEquals(4, strings.get_size());
		
		strings.delete("K");
		assertEquals(4, strings.get_size());
		
		strings.insert("L");
		strings.insert("M");
		strings.insert("N");
		strings.insert("O");
		
		strings.delete("C");
		assertEquals(0, strings.get_size());
	}
	
	@Test
	public void test_least_common_ancestor() {
		BST<String> strings 	= new BST<>();

		strings.insert("C");
		strings.insert("B");
		strings.insert("A");
		strings.insert("F");

		strings.insert("Z");
		strings.insert("D");
		strings.insert("K");
		strings.insert("E");

		//skewed insertions
		strings.insert("L");
		strings.insert("M");
		strings.insert("N");
		strings.insert("O");
		
		assertEquals("F", strings.get_least_common_ancestor("N", "E"));
		assertEquals("C", strings.get_least_common_ancestor("F", "Z"));
		assertEquals("C", strings.get_least_common_ancestor("O", "A"));
		assertEquals("Z", strings.get_least_common_ancestor("K", "L"));
		assertEquals("C", strings.get_least_common_ancestor("F", "F"));
		assertEquals(null, strings.get_least_common_ancestor("C", "Z"));
	}
}
