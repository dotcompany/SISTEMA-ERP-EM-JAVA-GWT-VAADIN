package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class NaturezaFinanceiraDAO extends AbstractCrudDAO<NaturezaFinanceira>{

	@Override
	public Class<NaturezaFinanceira> getEntityClass() {
		return NaturezaFinanceira.class;
	}
		
	@Transactional
	public List<NaturezaFinanceira> listaTodos() {
		return getSession().createQuery("from NaturezaFinanceira").list();
	}

	@Transactional
	public List<NaturezaFinanceira> procuraNomeContendo(String query) {
		return getSession().createQuery("from NaturezaFinanceira where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"tipo", "descricao","contas_receber","contas_pagar"};
	}	
}
