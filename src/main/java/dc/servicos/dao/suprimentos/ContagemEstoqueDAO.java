package dc.servicos.dao.suprimentos;


import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ContagemEstoqueDAO extends AbstractCrudDAO<ContagemEstoque> {

	@Override
	protected Class<ContagemEstoque> getEntityClass() {
		return ContagemEstoque.class;
	}

//	@Override
//	public ContagemEstoque find(Serializable id) {
//		 ContagemEstoque contagemEstoque = super.find(id);
//		// workaround para lazy initialization exception
//		//contagemEstoque.getContagemDetalhes().size();
//		return contagemEstoque;
//	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"data"};
	}

}
 