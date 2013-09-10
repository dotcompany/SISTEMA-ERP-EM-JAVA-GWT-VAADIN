package dc.entidade.type.produto;

public enum PodeFracionarType {
	
	SIM,
	NAO;
	
	public String getNome(){
		switch (this) {
		case SIM: return "Sim";
		case NAO: return "não";
		}
		return null;
 	}
 	
 	@Override
 	public String toString(){
 		return getNome();
 	}

}
