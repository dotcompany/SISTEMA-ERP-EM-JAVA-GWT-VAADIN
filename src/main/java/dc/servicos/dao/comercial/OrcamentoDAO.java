package dc.servicos.dao.comercial;


import org.springframework.stereotype.Repository;

import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class OrcamentoDAO extends AbstractCrudDAO<Orcamento> {

	@Override
	public Class<Orcamento> getEntityClass() {
		return Orcamento.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	
}

