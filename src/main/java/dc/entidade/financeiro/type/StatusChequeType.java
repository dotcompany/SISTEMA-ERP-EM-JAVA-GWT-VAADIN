package dc.entidade.financeiro.type;

public enum StatusChequeType {
	
	EMSER,
	BAIXADO,
	UTILIZADO,
	COMPENSADO,
	CANCELADO;
	
	public String getNome(){
		switch (this) {
		case EMSER: return "Em ser";
		case BAIXADO: return "Baixado";
		case UTILIZADO: return "Utilziado";
		case COMPENSADO: return "Compensado";
		case CANCELADO: return "Cancelado";
		}
		
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
