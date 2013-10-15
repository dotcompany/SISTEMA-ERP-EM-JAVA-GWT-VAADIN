package dc.servicos.dao.diversos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.Municipio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class MunicipioDAO extends AbstractCrudDAO<Municipio>{

	@Override
	public Class<Municipio> getEntityClass() {
		return Municipio.class;
	}

	@Transactional
	public List<Municipio> listaTodos() {
		return getSession().createQuery("from Municipio").list();
	}

	@Transactional
	public List<Municipio> procuraNomeContendo(String query) {
		return getSession().createQuery("from Municipio where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome","codigoIbge","codigoReceitaFederal"};
	}
	
	@Transactional
	public List<Municipio> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Municipio where lower(nome) like :q").setParameter("q", q).list();
	}

}
