package dc.servicos.dao.comercial;

import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class TipoNotaFiscalDAO extends AbstractCrudDAO<TipoNotaFiscal> {

	@Override
	protected Class<TipoNotaFiscal> getEntityClass() {
		return TipoNotaFiscal.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	
}
