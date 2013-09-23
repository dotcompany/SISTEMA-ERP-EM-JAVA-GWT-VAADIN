package dc.servicos.dao.comercial;

import org.springframework.stereotype.Repository;

import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TipoNotaFiscalDAO extends AbstractCrudDAO<TipoNotaFiscal> {

	@Override
	public Class<TipoNotaFiscal> getEntityClass() {
		return TipoNotaFiscal.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	
}
