package Tree;

/**
 * Classe para uso da árvore, contém alguns
 * testes para a estrutura implementada.
 * @author gabriel
 *
 */
public class TesteTree
{

	public static void main(String[] args)
	{
		char[] alfa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z'};
		
		Trie arvore = new Trie( alfa );
		
		arvore.insertWord("casa");
		arvore.insertWord("camelo");
		arvore.insertWord("bacia");
		arvore.insertWord("vaca");
		arvore.insertWord("árvore");
		arvore.insertWord("comida");
		
		if ( arvore.search("casa") )
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("camelo") )
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("bacia") )
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("vaca") )
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( !arvore.search("cas") )
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("comida") )
			System.out.println("OK");
		else System.out.println("Deu ruim");
	}

}
