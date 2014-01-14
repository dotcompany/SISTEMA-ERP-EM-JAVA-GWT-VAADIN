package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.Feriados;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class FeriadosDAO extends AbstractCrudDAO<Feriados>{

	@Override
	public Class<Feriados> getEntityClass() {
		return Feriados.class;
	}
	
	@Transactional
	public List<Feriados> listaTodos() {
		return getSession().createQuery("from Feriados").list();
	}

	@Transactional
	public List<Feriados> procuraNomeContendo(String query) {
		return getSession().createQuery("from Feriados where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"ano","nome"};
	}


}
