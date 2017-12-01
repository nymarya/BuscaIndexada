package sbi_project;

public class TesteAlfabeto
{

	public static void main(String[] args)
	{
		
		char[] alfa = { '-','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j', 'k', 'l', 'm', 
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y', 'z', 'à', 'á', 'â', 'ã',
				'ç', 'è', 'é', 'ê', 'ì', 'í', 'î', 'ò', 'ó', 'ô','õ', 'ù', 'ú', 'û'};

		for (int i=0; i < alfa.length; i++)
		{
			System.out.println(Character.hashCode(alfa[i]) + " - " + Character.valueOf(alfa[i]));
		}
	}

}
