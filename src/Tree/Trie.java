package Tree;

import java.util.ArrayList;


import sbi_project.Pair;

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
	public Node search ( String s ) throws TreeException
	{	
		//Use auxiliary method
		Node pointer = raiz;
		Pair<Integer, Node> result = searchWord(s, pointer);

		int length = result.getFirst();
		pointer = result.getSecond();

		//Check if the remaining pointer is end of word
		if(pointer.getTerminal() && length == s.length())
			return result.getSecond();

		return null;
	}
	
	/**
	 * Método auxiliar para buscar palavra na árvore.
	 * @param s Chave a ser buscada.
	 * @param pt O ponteiro atual.
	 * @return Tamanho do prefixo e um ponteiro para o maior
	 * prefixo da chave na árvore
	 * @throws TreeException 
	 * 
	 */
	private Pair<Integer, Node> searchWord(String s, Node pt) throws TreeException {
		// l = tamanho do maior prefixo em comum com a chave
		int l = 0;
		while( l < s.length() ) {

			//Recupera a posição do caractere
			int index = searchIndexAlfa(s.charAt(l) );
			if( pt.getPonteiro(index) != null) {
				//Continua checando os nós
				pt = pt.getPonteiro(index);
				l++;
			} else {
				//Para o laço quando não há mais dígitos
				break;
			}

		}

		Pair<Integer, Node> pair = new Pair<Integer,Node>(l, pt);

		return pair;
	}
	
	/**
	 * Insere uma nova palavra na árvore
	 * @param s   A palavra
	 * @throws TreeException Caso um caractere não seja reconhecido
	 * pelo alfabeto
	 */
	public void insertWord ( String s, int linha, String arquivo ) throws TreeException
	{
		/**
		 * Verifica se é uma palavra válida
		 */
		if (s.length() == 0) return;

		
		//Busca chave na árvore
		Node pt = raiz;
		Pair<Integer, Node> result = searchWord(s, pt);
		int length = result.getFirst();
		pt = result.getSecond();

		//Se a palavra ainda não estiver na árvore, adiciona
		//Caso contrário, atualiza número de ocorrências
		if( length <= s.length()) {
			//Insere chave a partir do final do prefixo encontrado
			while(length < s.length()) {
				//Cria novo nó
				Node node = new Node(s.charAt(length), false, alfabeto.length);

				//Recupera posição do caractere no alfabeto
				int index = searchIndexAlfa(s.charAt(length));
				
				pt.setPonteiro(index, node);

				//Continua inserindo
				pt = pt.getPonteiro(index);
				length++;
			}


			//Adiciona indice
			Index indice = new Index(linha, arquivo, 0);
			pt.addIndice(indice);
			
			//Ultimo nó é terminal
			pt.setTerminal(true);
		}
		
		if(length == s.length() ){

			//Se já houver palavra naquele arquivo e naquela linha,
			//atualiza número de ocorrencias
			Index index = pt.getIndice(arquivo, linha);
			if( index != null) {
				index.incrementaOcorrencia();
			} 
			
		}
		
		
		
	}


	/**
	 * Lista elementos da árvore.
	 * @param no Nó inicial.
	 * @param palavra Palavra guardada até então.
	 */
	public void listTree ( Node no, StringBuffer palavra )
	{		
		/**
		 * Quando a palavra é encontrada
		 */
		if ( no.getTerminal() )
		{
			System.out.println(palavra);
		}
		
		Node[] ponteiros = no.getPonteiros();
		int i=0;
		for ( /*empty*/; i < ponteiros.length; i++ )
		{
			if ( ponteiros[i] != null )
			{
				palavra.append(ponteiros[i].getChave()); 
				listTree(ponteiros[i], palavra);
			}
		}
		if (palavra.length() > 0)	
			palavra.deleteCharAt(palavra.length()-1);
			
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
	
	/**
	 * Retorna nó da árvore
	 * @return
	 */
	public Node getRoot ()
	{
		return raiz;
	}
}
