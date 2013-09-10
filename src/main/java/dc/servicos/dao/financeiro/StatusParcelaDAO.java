package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.StatusParcela;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class StatusParcelaDAO extends AbstractCrudDAO<StatusParcela>{

	@Override
	protected Class<StatusParcela> getEntityClass() {
		return StatusParcela.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from StatusParcela").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}
	

}
