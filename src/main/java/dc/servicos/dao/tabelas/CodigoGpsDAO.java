package dc.servicos.dao.tabelas;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CodigoGps;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
 *
 * @author Wesley Jr
 *
 */


@Repository
@SuppressWarnings("unchecked")
public class CodigoGpsDAO extends AbstractCrudDAO<CodigoGps>{

	@Override
	public Class<CodigoGps> getEntityClass() {
		return CodigoGps.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

	@Transactional
	public CodigoGps procuraPorCodigo(String codigo){
		CodigoGps cod = null;
		Criteria c = getSession().createCriteria(CodigoGps.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cod = (CodigoGps)c.uniqueResult();
		return cod;
	}
	
	@Transactional
	public List<CodigoGps> listaTodos() {
		return getSession().createQuery("from CodigoGps").list();
	}
}

