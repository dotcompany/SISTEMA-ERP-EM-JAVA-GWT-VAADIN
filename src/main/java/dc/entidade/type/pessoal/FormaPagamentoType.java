package dc.entidade.type.pessoal;

public enum FormaPagamentoType {
	
	DINHEIRO,
	CHEQUE,
	CONTA;
	
	public String getNome(){
		switch (this) {
		case DINHEIRO: return "Dinheiro";
		case CHEQUE: return "Cheque";
		case CONTA: return "Conta";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
