package dc.servicos.dao.geral.diverso;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class AlmoxarifadoDAO extends AbstractCrudDAO<AlmoxarifadoEntity>{

	@Override
	public Class<AlmoxarifadoEntity> getEntityClass() {
		return AlmoxarifadoEntity.class;
	}

	@Transactional
	public List<AlmoxarifadoEntity> listaTodos() {
		return getSession().createQuery("from Almoxarifado").list();
	}

	@Transactional
	public List<AlmoxarifadoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Almoxarifado where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<AlmoxarifadoEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Almoxarifado where lower(nome) like :q").setParameter("q", q).list();
	}

}
