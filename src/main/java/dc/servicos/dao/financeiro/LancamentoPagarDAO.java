package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.LancamentoPagar;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class LancamentoPagarDAO extends AbstractCrudDAO<LancamentoPagar>{

	@Override
	public Class<LancamentoPagar> getEntityClass() {
		return LancamentoPagar.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from LancamentoPagar").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}
	

}
