package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.ParcelaPagar;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



@Repository
@SuppressWarnings("unchecked")
public class ParcelaPagarDAO extends AbstractCrudDAO<ParcelaPagar>{

	@Override
	public Class<ParcelaPagar> getEntityClass() {
		return ParcelaPagar.class;
	}

	@Transactional
	public List<ParcelaPagar> listaTodos() {
		return getSession().createQuery("from ParcelaPagar").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}
	
	@Transactional
	public List<ParcelaPagar> buscaPorParcelaPagar(ParcelaPagar parcelaPagar){
		 Session session = getSession();
         Criteria criteria = session.createCriteria(ParcelaPagar.class);
         criteria.add(Restrictions.eq("parcelaPagar", parcelaPagar));

         List<ParcelaPagar> parcelaPaga = criteria.list();
         
         return parcelaPaga;
	}
	

}
