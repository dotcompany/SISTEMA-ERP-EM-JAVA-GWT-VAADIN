package dc.servicos.dao.tributario;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.OperacaoFiscal;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ConfiguracaoTributariaDAO extends AbstractCrudDAO<ConfiguracaoTributaria> {

	@Override
	protected Class<ConfiguracaoTributaria> getEntityClass() {
		return ConfiguracaoTributaria.class;
	}

//	@Override
//	public ContagemEstoque find(Serializable id) {
//		 ContagemEstoque contagemEstoque = super.find(id);
//		// workaround para lazy initialization exception
//		//contagemEstoque.getContagemDetalhes().size();
//		return contagemEstoque;
//	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	

}
 
