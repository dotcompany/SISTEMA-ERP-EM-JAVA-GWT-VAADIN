package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.TipoColaborador;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoColaboradorDAO extends AbstractCrudDAO<TipoColaborador>{

	@Override
	public Class<TipoColaborador> getEntityClass() {
		return TipoColaborador.class;
	}

	@Transactional
	public List<TipoColaborador> listaTodos() {
		return getSession().createQuery("from TipoColaborador").list();
	}

	@Transactional
	public List<TipoColaborador> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoColaborador where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}
	
	/*@Transactional
	public List<TipoColaborador> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		List<TipoColaborador> listaTipos = getSession().createQuery("from TipoColaborador where lower(nome) like :q").setParameter("q", q).list();
		return listaTipos;
		
	}*/

}
