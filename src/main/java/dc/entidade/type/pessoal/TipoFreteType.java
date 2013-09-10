package dc.entidade.type.pessoal;

public enum TipoFreteType {
	
	EMITENTE,
	DESTINATARIO,
	SEMFRETE,
	TERCEIROS;
	
	public String getNome(){
		switch (this) {
		case EMITENTE: return "Emitente";
		case DESTINATARIO: return "Destinatário";
		case SEMFRETE: return "Sem Frete";
		case TERCEIROS: return "Terceiros";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
