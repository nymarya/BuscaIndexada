package Tree;

import java.io.Serializable;
import java.util.ArrayList;


import sbi_project.Pair;

/**
 * Classe para a estrutura de dados
 * @author gabriel
 *
 */
public class Trie implements Serializable
{
	
	private static final long serialVersionUID = 5726894369803645467L;
	private char[] alfabeto;    // Alfabeto usado pela arvore
	private Node raiz;          // raiz da arvore
	
	/**
	 * Construtor
	 * @param alfabeto  alfabeto reconhecido pela arvore
	 */
	public Trie ( char[] alfabeto )
	{
		this.alfabeto = alfabeto;
		raiz = new Node( '#', false, alfabeto.length );
	}
	
	/**
	 * Busca uma palavra na arvore
	 * @param s  palavra buscada
	 * @return   true se palavra encontrada, false caso contrario.
	 * @throws TreeException Caso algum caracter nao seja reconhecido
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
	 * MÃ©todo auxiliar para buscar palavra na Ã¡rvore.
	 * @param s Chave a ser buscada.
	 * @param pt O ponteiro atual.
	 * @return Tamanho do prefixo e um ponteiro para o maior
	 * prefixo da chave na Ã¡rvore
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
				//Para o laço quando não há mais dí­gitos
				break;
			}

		}

		Pair<Integer, Node> pair = new Pair<Integer,Node>(l, pt);

		return pair;
	}
	
	/**
	 * Insere uma nova palavra na arvore
	 * @param s   A palavra
	 * @throws TreeException Caso um caractere não seja reconhecido
	 * pelo alfabeto
	 */
	public void insertWord ( String s, int linha, String arquivo ) throws TreeException
	{
		/**
		 * Verifica se ha uma palavra valida
		 */
		if (s.length() == 0) return;
		
		//Busca chave na arvore
		Node pt = raiz;
		Pair<Integer, Node> result = searchWord(s, pt);
		int length = result.getFirst();
		pt = result.getSecond();

		//Se a palavra ainda nao estiver na arvore, adiciona
		//Caso contrario, atualiza numero de ocorrencias
		if(length == s.length() && pt.getTerminal() ){

			//Se ja houver palavra naquele arquivo e naquela linha,
			//atualiza numero de ocorrencias
			Index index = pt.getIndice(arquivo, linha);
			if( index != null) {
				index.incrementaOcorrencia();
			} else {
				Index indice = new Index(linha, arquivo, 1);
				pt.addIndice(indice);
			}
			
		} else if( length <= s.length()) {
			//Insere chave a partir do final do prefixo encontrado
			int index;
			while(length < s.length()) {
				//Cria novo nó
				Node node = new Node(s.charAt(length), false, alfabeto.length);

				//Recupera posição do caractere no alfabeto
				index = searchIndexAlfa(s.charAt(length));
				pt.setPonteiro(index, node);
				
				//Continua inserindo
				pt = pt.getPonteiro(index);
				length++;
			}


			//Adiciona indice
			Index indice = new Index(linha, arquivo, 1);
			pt.addIndice(indice);
			
			//Ultimo nó terminal
			pt.setTerminal(true);
		}
		
	}


	/**
	 * Lista elementos da arvore.
	 * @param no No inicial.
	 * @param palavra Palavra guardada ate entao.
	 */
	public void listTree ( Node no, StringBuffer palavra )
	{		
		/**
		 * Quando a palavra é encontrada
		 */
		if( no != null ) {
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
			
	}
	
	/**
	 * Procura o i­ndice de um caractere no alfabeto por busca binatia
	 * @param letra   O caractere
	 * @return  O Ã­ndice caso tenha encontrado, -1 caso contrario
	 */
	public int searchIndexAlfa ( Character letra )
	{
		/**
		 * Inicializaça£o
		 */
		int half;
		int left = 0;
		int right = alfabeto.length-1;
		
		/**
		 * Busca binaria
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
	 * Remove palavra da arvore.
	 * @param s String a ser removida.
	 * @throws TreeException 
	 */
	public void remove(String s) throws TreeException {
		//Usa metodo auxiliar
		raiz = removeHelper(s, raiz, 0);
		
		if( raiz == null)
			raiz = new Node( '#', false, alfabeto.length );
	}

	/**
	 * Metodo que auxilia a remoçao na arvore
	 * @param s String a ser removida
	 * @param pt Ponteiro para o nó atual
	 * @param l indice atual da palavra
	 * @return No resultante da a tentativa de remoção
	 * @throws TreeException 
	 */
	private Node removeHelper(String s, Node pt, int l) throws TreeException {
		//Caso base 1 = null pt
		if(pt == null)
			return null;

		//Caso 2: chave eh prefixo de outra 
		//apenas desmarca como fim de palavra
		if(l == s.length() && pt.getTerminal() )
			pt.setTerminal(false);;

		// Nao eh fim de palavra => analisa nós filhos
		if(l < s.length() ) {
			int index = searchIndexAlfa(s.charAt(l));
			Node aux = pt.getPonteiro(index);
			pt.setPonteiro(index, removeHelper(s, aux, l+1));
		}
		
		//O ponteiro atual eh fim de palavra
		if(pt.getTerminal())
			return pt;

		//Verifica se ponteiro atual Ã© folha
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
