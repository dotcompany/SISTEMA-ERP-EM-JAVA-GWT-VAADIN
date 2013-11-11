package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class StatusParcelaDAO extends AbstractCrudDAO<StatusParcela>{

	@Override
	public Class<StatusParcela> getEntityClass() {
		return StatusParcela.class;
	}

	@Transactional
	public List<ParcelaPagamento> listaTodos() {
		return getSession().createQuery("from StatusParcela").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[]{"situacao", "descricao"};
	}

	@Transactional
	public StatusParcela findBySituacao(String situacao) {
		Criteria criteria = getSession().createCriteria(StatusParcela.class);
        criteria.add(Restrictions.eq("situacao", situacao));
        StatusParcela statusParcela = (StatusParcela) criteria.uniqueResult();
        
        return statusParcela;
		
	}
	

}
