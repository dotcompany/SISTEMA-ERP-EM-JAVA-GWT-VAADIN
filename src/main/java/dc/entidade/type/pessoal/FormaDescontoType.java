package dc.entidade.type.pessoal;

public enum FormaDescontoType {
	
	PRODUTO,
	FIMPEDIDO;
	
	public String getNome(){
		switch (this) {
		case PRODUTO: return "Produto";
		case FIMPEDIDO: return "Fim de Pedido";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
