package dc.servicos.dao.tributario;

import org.springframework.stereotype.Repository;

import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class IcmsCustomizadoDAO extends AbstractCrudDAO<IcmsCustomizadoCabecalhoEntity> {

	@Override
	public Class<IcmsCustomizadoCabecalhoEntity> getEntityClass() {
		return IcmsCustomizadoCabecalhoEntity.class;
	}

//	@Override
//	public ContagemEstoque find(Serializable id) {
//		 ContagemEstoque contagemEstoque = super.find(id);
//		// workaround para lazy initialization exception
//		//contagemEstoque.getContagemDetalhes().size();
//		return contagemEstoque;
//	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome","origemMercadoria"};
	}

}
 