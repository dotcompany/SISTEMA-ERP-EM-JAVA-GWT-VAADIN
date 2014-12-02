package dc.servicos.dao.geral.tabela;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstPisEntity;
import dc.entidade.geral.tabela.EfdTabela435Entity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
 *
 * @author Wesley Jr
 *
 */


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela435DAO extends AbstractCrudDAO<EfdTabela435Entity>{

	@Override
	public Class<EfdTabela435Entity> getEntityClass() {
		return EfdTabela435Entity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

	@Transactional
	public EfdTabela435Entity procuraPorCodigo(String codigo){
		EfdTabela435Entity cod = null;
		Criteria c = getSession().createCriteria(EfdTabela435Entity.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cod = (EfdTabela435Entity)c.uniqueResult();
		return cod;
	}
}

