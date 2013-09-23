package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.ParcelaPagar;
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
	
	@Transactional
	public List<ParcelaPagamento> buscaPorParcelaPagar(ParcelaPagar parcelaPagar){
		 Session session = getSession();
         Criteria criteria = session.createCriteria(ParcelaPagamento.class);
         criteria.add(Restrictions.eq("parcelaPagar", parcelaPagar));

         List<ParcelaPagamento> parcelaPagamento = criteria.list();
         
         return parcelaPagamento;
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "" };
	}

}
