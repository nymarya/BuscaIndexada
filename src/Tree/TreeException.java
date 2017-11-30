package Tree;

/**
 * Exceções lançadas pela árvore
 * @author gabriel
 *
 */
public class TreeException extends Exception
{

	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor com mensagem padrão
	 */
	public TreeException()
	{
		message = "Palavra com caracter não reconhecido pelo alfabeto definido!";
	}
	
	/**
	 * Construtor com caracter de error
	 */
	public TreeException( char c)
	{
		message = "Palavra com caracter não reconhecido pelo alfabeto definido!" + c;
	}
	
	/**
	 * Construtor com mensagem definida
	 * @param message A mensagem
	 */
	public TreeException( String message)
	{
		this.message = message;
	}
	
	/**
	 * Retorna a mensagem de erro.
	 */
	@Override
	public String toString()
	{
		return message;
	}
}
