package Tree;

/**
 * Represente um nó de uma árvore
 * chave = # indica que o nó é a raiz.
 * @author gabriel
 *
 */
public class Node
{
	private Character chave;  // conteúdo do nó
	private Node ponteiros[]; // "ponteiros" para possíveis nós
	private boolean terminal; //indica se é o final de uma palavra
	
	/**
	 * Construtor
	 * @param tamAlfa  tamanho do alfabeto que um nó representa
	 */
	public Node ( int tamAlfa )
	{
		chave = null;
		ponteiros = new Node[tamAlfa];
		terminal = false;
	}
	
	/**
	 * Construtor completo
	 * @param chave     conteúdo do nó
	 * @param terminal  indicativo se o nó representa o final de uma palavra
	 * @param tamAlfa   tamanho do alfabeto que um nó representa
	 */
	public Node ( Character chave, boolean terminal, int tamAlfa)
	{
		this.chave = chave;
		ponteiros = new Node[tamAlfa];
		this.terminal = terminal;
	}

	/**
	 * Insere uma referência para um novo nó, representa uma ligação
	 * com uma nova letra do alfabeto, característico das árvores Trie.
	 * @param index    O índice do dígito, semelhante a letra do alfabeto.
	 * @param element  O nó que deseja ser referenciado
	 * @throws TreeException caso seja informado uma posição inexistente no
	 * alfabeto
	 */
	public void inserirPonteiro ( int index, Node element ) throws TreeException
	{
		if ( index < 0 || index > ponteiros.length)
			throw new TreeException();
			
		if ( ponteiros[index] == null )
		{
			ponteiros[index] = element;
		}
	}
	
	/**
	 * Verifica se existe uma referência para determinada letra 
	 * do alfabeto a partir do nó.
	 * @param index  A posição da letra no alfabeto que é semelhante
	 * a posição da lista de ponteiros.
	 * @return O nó caso a referência exista, false caso contrário.
	 * @throws TreeException Caso seja solicitado uma posição inexistente 
	 * no alfabeto
	 */
	public Node getPonteiro ( int index ) throws TreeException
	{
		if (index < 0 || index > ponteiros.length)
			throw new TreeException();
		
		if ( ponteiros[index] != null )
			return ponteiros[index];
		return null;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public Character getChave()
	{
		return chave;
	}

	public void setChave(Character chave)
	{
		this.chave = chave;
	}

	public boolean getTerminal()
	{
		return terminal;
	}

	public void setTerminal(boolean terminal)
	{
		this.terminal = terminal;
	}
	
}
