package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class LancamentoReceberDAO extends AbstractCrudDAO<LancamentoReceber> {

	@Override
	public Class<LancamentoReceber> getEntityClass() {
		return LancamentoReceber.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from LancamentoReceber").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "pagamentoCompartilhado" };
	}

}
