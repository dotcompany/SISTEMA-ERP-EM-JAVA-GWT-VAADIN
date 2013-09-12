package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class ParcelaPagarDAO extends AbstractCrudDAO<ParcelaPagar>{

	@Override
	protected Class<ParcelaPagar> getEntityClass() {
		return ParcelaPagar.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from ParcelaPagar").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}
	

}
