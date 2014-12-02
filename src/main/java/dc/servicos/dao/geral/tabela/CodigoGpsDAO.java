package dc.servicos.dao.geral.tabela;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CodigoGpsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
 *
 * @author Wesley Jr
 *
 */


@Repository
@SuppressWarnings("unchecked")
public class CodigoGpsDAO extends AbstractCrudDAO<CodigoGpsEntity>{

	@Override
	public Class<CodigoGpsEntity> getEntityClass() {
		return CodigoGpsEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

	@Transactional
	public CodigoGpsEntity procuraPorCodigo(String codigo){
		CodigoGpsEntity cod = null;
		Criteria c = getSession().createCriteria(CodigoGpsEntity.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cod = (CodigoGpsEntity)c.uniqueResult();
		return cod;
	}
	
	@Transactional
	public List<CodigoGpsEntity> listaTodos() {
		return getSession().createQuery("from CodigoGps").list();
	}
}

