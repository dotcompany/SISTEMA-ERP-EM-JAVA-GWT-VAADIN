package dc.servicos.util;


/**
 * Classe utilit�ria com m�dotos de valida��o.
 * @author douglasrehem
 *
 */
public class Validator {

	private Validator() {
		
	}
	
	/**
	 * M�todo utilit�rio de valida��o de String.
	 * @param value Valor para ser validado.
	 * @return Retorna <code>true</code> se estiver preenchido ou <code>false</code> se for null ou vazio
	 */
	public static Boolean validateString(String value)
	{
		boolean valido = true;
		
		if(value == null  || value.isEmpty())
		{
			valido = false;
		}
		
		return valido;
	}
	
	/**
	 * M�todo utilit�rio de valida��o de Números.
	 * @param value Valor para ser validado.
	 * @return Retorna <code>true</code> se for um Número v�lido ou <code>false</code> se 
	 * for inválido ou em branco
	 */
	public static Boolean validateNumber(String value)
	{
		boolean valido = validateString(value);
		
		if(valido)
		{
			try
			{
				Double.parseDouble(value);				
			}
			catch(NumberFormatException e)
			{
				valido = false;
			}
		}
		
		return valido;
	}
	
	/**
	 * M�todo utilit�rio de valida��o de Objetos.
	 * @param value Valor para ser validado.
	 * @return Retorna <code>true</code> se Não for null ou <code>false</code> se 
	 * for null.
	 */
	public static boolean validateObject(Object value) {
		return value != null;
	}
}
