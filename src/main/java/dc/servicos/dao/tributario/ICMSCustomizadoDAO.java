package dc.servicos.dao.tributario;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import dc.entidade.tributario.IcmsCustomizadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ICMSCustomizadoDAO extends AbstractCrudDAO<IcmsCustomizadoEntity> {

	@Override
	public Class<IcmsCustomizadoEntity> getEntityClass() {
		return IcmsCustomizadoEntity.class;
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
 