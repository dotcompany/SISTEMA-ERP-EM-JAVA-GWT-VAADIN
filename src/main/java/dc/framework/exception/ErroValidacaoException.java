package dc.framework.exception;

public class ErroValidacaoException extends Exception {

	String msgErro;
	
	public ErroValidacaoException(String msgErro ){
		this.msgErro = msgErro;
	}
	
	public String montaMensagemErro(){
		return msgErro;
	}
	
}
