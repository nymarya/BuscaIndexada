package Tree;

/**
 * Classe para uso da Ã¡rvore, contÃ©m alguns
 * testes para a estrutura implementada.
 * @author gabriel
 *
 */
public class TesteTree
{

	public static void main(String[] args)
	{
		char[] alfa = { '-','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z', 'à', 'á', 'â', 'ã',
				'ç', 'è', 'é', 'ê', 'ì', 'í', 'î', 'ò', 'ó', 'ô','õ', 'ù', 'ú', 'û'};
		
		Trie arvore = new Trie( alfa );
		/**
		arvore.insertWord("casa", 1, "a");
		arvore.insertWord("casa", 1, "a");
		arvore.insertWord("camelo", 5, "b");
		arvore.insertWord("bacia", 2, "a");
		arvore.insertWord("vaca", 13, "a");
		arvore.insertWord("Ã¡rvore", 15, "b");
		arvore.insertWord("comida", 1, "a");
		arvore.insertWord("camarada", 1, "a");
		arvore.insertWord("cama", 1, "a");
		
		if ( arvore.search("casa") != null)
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("camelo") != null)
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("bacia") != null)
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("vaca") != null)
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("cas") == null)
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		if ( arvore.search("comida") != null )
			System.out.println("OK");
		else System.out.println("Deu ruim");
		
		arvore.listTree(arvore.getRaiz(), new StringBuffer());**/
	}
}
