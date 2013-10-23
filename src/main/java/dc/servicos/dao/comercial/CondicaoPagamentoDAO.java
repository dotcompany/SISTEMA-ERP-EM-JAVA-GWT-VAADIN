package dc.servicos.dao.comercial;

import org.springframework.stereotype.Repository;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class CondicaoPagamentoDAO extends AbstractCrudDAO<CondicaoPagamento> {

	@Override
	public Class<CondicaoPagamento> getEntityClass() {
		return CondicaoPagamento.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	
}
