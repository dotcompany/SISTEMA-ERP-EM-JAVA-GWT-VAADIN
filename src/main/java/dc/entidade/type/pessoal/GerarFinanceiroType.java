package dc.entidade.type.pessoal;

public enum GerarFinanceiroType {
	
	SIM,
	NAO;
	
	public String getNome(){
		switch (this) {
		case SIM: return "Sim";
		case NAO: return "Nao";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
