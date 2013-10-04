package dc.servicos.dao.tabelas;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CodigoApuracaoEfd;
import dc.entidade.tabelas.CstPis;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
 *
 * @author Wesley Jr
 *
 */


@Repository
@SuppressWarnings("unchecked")
public class CodigoApuracaoEfdDAO extends AbstractCrudDAO<CodigoApuracaoEfd>{

	@Override
	public Class<CodigoApuracaoEfd> getEntityClass() {
		return CodigoApuracaoEfd.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

	@Transactional
	public CodigoApuracaoEfd procuraPorCodigo(String codigo){
		CodigoApuracaoEfd cod = null;
		Criteria c = getSession().createCriteria(CodigoApuracaoEfd.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cod = (CodigoApuracaoEfd)c.uniqueResult();
		return cod;
	}
}

