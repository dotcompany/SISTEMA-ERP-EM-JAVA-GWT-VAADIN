package dc.servicos.dao.tributario;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import dc.entidade.tributario.ICMSCustomizado;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ICMSCustomizadoDAO extends AbstractCrudDAO<ICMSCustomizado> {

	@Override
	protected Class<ICMSCustomizado> getEntityClass() {
		return ICMSCustomizado.class;
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
 