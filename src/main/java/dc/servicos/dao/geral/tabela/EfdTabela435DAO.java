package dc.servicos.dao.geral.tabela;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstPis;
import dc.entidade.geral.tabela.EfdTabela435;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
 *
 * @author Wesley Jr
 *
 */


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela435DAO extends AbstractCrudDAO<EfdTabela435>{

	@Override
	public Class<EfdTabela435> getEntityClass() {
		return EfdTabela435.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

	@Transactional
	public EfdTabela435 procuraPorCodigo(String codigo){
		EfdTabela435 cod = null;
		Criteria c = getSession().createCriteria(EfdTabela435.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cod = (EfdTabela435)c.uniqueResult();
		return cod;
	}
}

