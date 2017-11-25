package Tree;


/**
 * Classe para a estrutura de dados
 * @author gabriel
 *
 */
public class Trie
{
	private char[] alfabeto;    // Alfabeto usado pela árvore
	private Node raiz;          // raiz da árvore
	private Node noDeParada;    // Um nó auxiliar para ajudar na busca e inserção
	private int indexDeParada;  // índice auxiliar para ajudar na busca e inserção
	
	/**
	 * Construtor
	 * @param alfabeto  alfabeto reconhecido pela árvore
	 */
	public Trie ( char[] alfabeto )
	{
		this.alfabeto = alfabeto;
		raiz = new Node( '#', false, alfabeto.length );
		noDeParada = raiz;
		indexDeParada = 0;
	}
	
	/**
	 * Busca uma palavra na árvore
	 * @param s  palavra buscada
	 * @return   true se palavra encontrada, false caso contrário.
	 * @throws TreeException Caso algum caracter não seja reconhecido
	 * pelo alfabeto.
	 */
	public Node search ( String s )
	{	
		int alturaPercorrida = 0;
		int tamanhoPalavra = s.length();
		
		Node pt = raiz;
		Node aux;
		
		int indexAlfa = 0;
		while ( alturaPercorrida < tamanhoPalavra )
		{
			indexAlfa = searchIndexAlfa(s.charAt(alturaPercorrida));
			try {
				aux = pt.getPonteiro(indexAlfa);
				if ( aux != null )
				{
					pt = aux;
					alturaPercorrida++;
					noDeParada = pt;
					indexDeParada = alturaPercorrida;
				}
				else return null;
		
			} catch( TreeException e)
			{
				System.out.println(e.toString() + " -> " + s.charAt(alturaPercorrida));
				return null;
			}
		}
		/**
		 * Verificar se a palavra realmente existe na árvore
		 * e não é apenas um prífixo de uma outra palavra.
		 */
		if ( noDeParada.getTerminal() )
			return noDeParada;
		return null;
	}
	
	/**
	 * Insere uma nova palavra na árvore
	 * @param s   A palavra
	 * @throws TreeException Caso um caractere não seja reconhecido
	 * pelo alfabeto
	 */
	public void insertWord ( String s, int linha, String arquivo )
	{
		/**
		 * Verifica se é uma palavra válida
		 */
		if (s.length() == 0) return;
		
		int indexAlfa = 0;
		Node auxNo;
		
		if ( this.search(s) == null )
		{
			for ( int i=indexDeParada; i< s.length(); i++)
			{
				indexAlfa = searchIndexAlfa(s.charAt(i));
				auxNo = new Node(s.charAt(i), false, alfabeto.length);
				
				try {
					noDeParada.inserirPonteiro(indexAlfa, auxNo);
					noDeParada = auxNo;
				}catch(TreeException e) {
					
				}
				
			}
			
			/**
			 * Identificar palavra
			 */
			Index indice = new Index(linha, arquivo, 1);
			noDeParada.addIndice(indice);
			noDeParada.setTerminal(true);
			
			/**
			 * Resetar auxiliares e indicar que o nó é terminal
			 */
			noDeParada = raiz;
			indexDeParada = 0;
		}
	}
	
	// TODO
	public void listTree ()
	{
		
	}
	
	/**
	 * Procura o índice de um caractere no alfabeto por busca binária
	 * @param letra   O caractere
	 * @return  O índice caso tenha encontrado, -1 caso contrário
	 */
	public int searchIndexAlfa ( Character letra )
	{
		/**
		 * Inicialização
		 */
		int half;
		int left = 0;
		int right = alfabeto.length-1;
		
		/**
		 * Busca binária
		 */
		while (left <= right)
		{
			half = (left + right)/2;
			if ( Character.compare(alfabeto[half], letra) == 0 )
				return half;
			else if ( Character.compare(alfabeto[half], letra) < 0 )
				left = half+1;
			else if ( Character.compare(alfabeto[half], letra) > 0 )
				right = half-1;
		}
		
		return -1;
	}
	
	/**
	 * Remove palavra da árvore.
	 * @param s String a ser removida.
	 * @throws TreeException 
	 */
	public void remove(String s) throws TreeException {
		//Usa método auxiliar
		raiz = removeHelper(s, raiz, 0);
	}

	/**
	 * Método que auxilia a remoção na árvore
	 * @param s String a ser removida
	 * @param pt Ponteiro para o nó atual
	 * @param l Índice atual da palavra
	 * @return Nó após a tentativa de remoção
	 * @throws TreeException 
	 */
	private Node removeHelper(String s, Node pt, int l) throws TreeException {
		//Caso base 1 = null pt
		if(pt == null)
			return null;

		//Caso 2: chave é prefixo de outra 
		//apenas desmarca como fim de palavra
		if(l == s.length() && pt.getTerminal() )
			pt.setTerminal(false);;

		// Não é fim de palavra => analisa nós filhos
		if(l < s.length() ) {
			int index = searchIndexAlfa(s.charAt(l));
			Node aux = pt.getPonteiro(index);
			pt.setPonteiro(index, removeHelper(s, aux, l+1));
		}
		
		//O ponteiro atual é fim de palavra
		if(pt.getTerminal())
			return pt;

		//Verifica se ponteiro atual é folha
		for(int i = 0; i < 26; i++)
			if(pt.getPonteiro(i) != null) return pt;

		return null;
	}
}
