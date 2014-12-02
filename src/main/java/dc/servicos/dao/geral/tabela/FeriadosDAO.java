package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.FeriadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class FeriadosDAO extends AbstractCrudDAO<FeriadoEntity>{

	@Override
	public Class<FeriadoEntity> getEntityClass() {
		return FeriadoEntity.class;
	}
	
	@Transactional
	public List<FeriadoEntity> listaTodos() {
		return getSession().createQuery("from Feriados").list();
	}

	@Transactional
	public List<FeriadoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Feriados where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"ano","nome"};
	}


}
