package dc.servicos.dao.tributario;



import org.springframework.stereotype.Repository;

import dc.entidade.tributario.ICMSCustomizadoDetalhe;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ICMSCustomizadoDetalheDAO extends AbstractCrudDAO<ICMSCustomizadoDetalhe> {

	@Override
	public Class<ICMSCustomizadoDetalhe> getEntityClass() {
		return ICMSCustomizadoDetalhe.class;
	}

//	@Override
//	public ContagemEstoque find(Serializable id) {
//		 ContagemEstoque contagemEstoque = super.find(id);
//		// workaround para lazy initialization exception
//		//contagemEstoque.getContagemDetalhes().size();
//		return contagemEstoque;
//	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}

}
 
