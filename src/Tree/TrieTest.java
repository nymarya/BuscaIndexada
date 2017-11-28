package Tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrieTest {

	@Test
	public void test() throws TreeException {
		char[] alfa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z'};

		Trie arvore = new Trie( alfa );

		try {
			arvore.insertWord("casa",0, "");
			arvore.insertWord("casamento",0, "");
			arvore.insertWord("camelo", 0, "");
			arvore.insertWord("bacia", 0, "");
			arvore.insertWord("vaca", 0, "");
			arvore.insertWord("comida", 0, "");
			arvore.insertWord("comi", 0, "");
			assertNotEquals(null, arvore.search("casa"));
			assertNotEquals(null, arvore.search("casamento"));
			assertNotEquals(null, arvore.search("camelo"));
			assertNotEquals(null, arvore.search("bacia"));
			assertNotEquals(null, arvore.search("vaca"));
			assertNotEquals(null, arvore.search("comida"));
			assertNotEquals(null, arvore.search("comi"));
		} catch (TreeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		try {
			arvore.remove("casa");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null, arvore.search("casa"));
		assertNotEquals(null, arvore.search("casamento"));
		assertNotEquals(null, arvore.search("camelo"));



		try {
			arvore.remove("vaca");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null, arvore.search("vaca"));
		
		try {
			arvore.remove("comida");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(null, arvore.search("comida"));

		try {
			arvore.remove("ajshajh");
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			arvore.insertWord("see", 0, "");

			//assertNotEquals(null, arvore.search("see"));
			arvore.insertWord("world", 0, "");
			assertNotEquals(null, arvore.search("world"));
			arvore.insertWord("grain", 0, "");
			assertNotEquals(null, arvore.search("grain"));
			arvore.insertWord("sand", 0, "");
			//assertNotEquals(null, arvore.search("sand"));
			arvore.insertWord("and", 0, "");
			arvore.insertWord("heaven", 0, "");
			arvore.insertWord("wild", 0, "");
			arvore.insertWord("flower", 0, "");
			arvore.insertWord("hold", 0, "");
			arvore.insertWord("infinity", 0, "");
			arvore.insertWord("palm", 0, "");
			arvore.insertWord("your", 0, "");
			arvore.insertWord("hand", 0, "");
			arvore.insertWord("eternity", 0, "");
			arvore.insertWord("hour", 0, "");
			arvore.insertWord("robin", 0, "");
			arvore.insertWord("redbeast", 0, "");
			arvore.insertWord("cage", 0, "");
			arvore.insertWord("puts", 0, "");
			arvore.insertWord("all", 0, "");
			arvore.insertWord("rage", 0, "");
			arvore.insertWord("every", 0, "");
			arvore.insertWord("night", 0, "");
			arvore.insertWord("morn", 0, "");
			arvore.insertWord("some", 0, "");
			arvore.insertWord("some", 0, "");
			arvore.insertWord("misery", 0, "");
			arvore.insertWord("are", 0, "");
			arvore.insertWord("born", 0, "");
			arvore.insertWord("sweet", 0, "");
			arvore.insertWord("delight", 0, "");
			arvore.insertWord("endles", 0, "");
			arvore.insertWord("endless", 0, "");
			arvore.remove("endles");
			assertEquals(null, arvore.search("endles"));
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		arvore.listTree(arvore.getRoot(),  new StringBuffer());

	}

}
