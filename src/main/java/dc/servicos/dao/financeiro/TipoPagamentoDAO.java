package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.TipoPagamento;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class TipoPagamentoDAO extends AbstractCrudDAO<TipoPagamento>{

	@Override
	protected Class<TipoPagamento> getEntityClass() {
		return TipoPagamento.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from TipoPagamento").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}
	

}
