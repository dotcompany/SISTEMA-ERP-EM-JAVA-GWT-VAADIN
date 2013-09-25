package dc.servicos.dao.diversos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.Almoxarifado;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class AlmoxarifadoDAO extends AbstractCrudDAO<Almoxarifado>{

	@Override
	public Class<Almoxarifado> getEntityClass() {
		return Almoxarifado.class;
	}

	@Transactional
	public List<Almoxarifado> listaTodos() {
		return getSession().createQuery("from Almoxarifado").list();
	}

	@Transactional
	public List<Almoxarifado> procuraNomeContendo(String query) {
		return getSession().createQuery("from Almoxarifado where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Almoxarifado> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Almoxarifado where lower(sigla) like :q").setParameter("q", q).list();
	}

}
