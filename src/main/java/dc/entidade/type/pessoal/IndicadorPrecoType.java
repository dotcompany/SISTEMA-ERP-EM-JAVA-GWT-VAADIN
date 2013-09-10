package dc.entidade.type.pessoal;

public enum IndicadorPrecoType {
	
	TABELA,
	ULTIMOPEDIDO;
	
	public String getNome(){
		switch (this) {
		case TABELA: return "Tabela";
		case ULTIMOPEDIDO: return "Último Pedido";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
