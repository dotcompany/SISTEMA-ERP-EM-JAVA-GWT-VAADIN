package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParcelaPagamentoDAO extends AbstractCrudDAO<ParcelaPagamento> {

	@Override
	protected Class<ParcelaPagamento> getEntityClass() {
		return ParcelaPagamento.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from ParcelaPagamento").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

}
