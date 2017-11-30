package Tree;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represente um nó de uma árvore
 * chave = # indica que o nó é a raiz.
 * @author gabriel
 *
 */
public class Node implements Serializable
{
	//uis
	private static final long serialVersionUID = -5001536922471381984L;
	private Character chave;  // conteúdo do nó
	private Node ponteiros[]; // "ponteiros" para possíveis nós
	private boolean terminal; // indica se é o final de uma palavra
	private ArrayList<Index> indice;
	
	/**
	 * Construtor
	 * @param tamAlfa  tamanho do alfabeto que um nó representa
	 */
	public Node ( int tamAlfa )
	{
		chave = null;
		ponteiros = new Node[tamAlfa];
		terminal = false;
		indice = new ArrayList<Index>();
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
		indice = new ArrayList<Index>();
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
		if (index < 0 || index >= ponteiros.length)
		{
			System.out.println(index);
			throw new TreeException();
		}			
		
		if ( ponteiros[index] != null )
			return ponteiros[index];
		return null;
	}
	
	/**
	 * 
	 * @param index
	 * @param novo
	 * @throws TreeException
	 */
	public void setPonteiro(int index, Node novo) throws TreeException
	{
		if (index < 0 || index >= ponteiros.length)
		{
			System.out.println(index);
			throw new TreeException();
		}
		
		ponteiros[index] = novo;
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

	public ArrayList<Index> getIndices ()
	{
		return indice;
	}
	
	public Node[] getPonteiros ()
	{
		return ponteiros;
	}
	
	/**
	 * Adiciona um novo índice na tabela de índices
	 * @param indice   O novo índice
	 */
	public void addIndice ( Index ind )
	{
		indice.add(ind);
	}
	
	/**
	 * Remove um índice da tabela de índices
	 * @param indice  O índice a ser removido.
	 */
	public void removeIndice ( Index indice )
	{
		this.indice.remove(indice);
	}
	
	/**
	 * Retorna o número de ocorrências da chave 
	 * no arquivo e linha dados
	 * @param file 
	 * @param line
	 */
	public Index getIndice(String file, int line) {
		//Procura índice
		for(int i = 0; i < indice.size(); i++) {
			Index atual = indice.get(i);
			if(atual.getArquivo() == file && atual.getLinha() == line)
				return atual;
		}
		
		//Não há nenhuma ocorrencia da chave na linha 
		//do arquivo
		return null;
	}

}
