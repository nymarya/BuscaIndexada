package Tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrieTest {

	@Test
	void test() {
		char[] alfa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z'};
		
		Trie arvore = new Trie( alfa );
		
		arvore.insertWord("casa",0, "");
		arvore.insertWord("casamento",0, "");
		arvore.insertWord("camelo", 0, "");
		arvore.insertWord("bacia", 0, "");
		arvore.insertWord("vaca", 0, "");
		arvore.insertWord("comida", 0, "");
		arvore.insertWord("comi", 0, "");
		
		assertEquals(true, arvore.search("casa"));
		assertEquals(true, arvore.search("casamento"));
		
		try {
			arvore.remove("casa");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false, arvore.search("casa"));
		assertEquals(true, arvore.search("casamento"));
		assertEquals(true, arvore.search("camelo"));
		
		try {
			arvore.remove("comida");
		} catch (TreeException e) {
			e.printStackTrace();
		}
		assertEquals(false, arvore.search("comida"));
		assertEquals(true, arvore.search("comi"));
		
		try {
			arvore.remove("vaca");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(false, arvore.search("vaca"));
		
		try {
			arvore.remove("ajshajh");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
