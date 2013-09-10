package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.UF;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


@Repository
@SuppressWarnings("unchecked")
public class UFDAO extends AbstractCrudDAO<UF>{
	
	@Override
	protected Class<UF> getEntityClass() {
		return UF.class;
	}
	
	
	@Transactional
	public List<UF> listaTodos() {
		return getSession().createQuery("from UF").list();
	}

	@Transactional
	public List<UF> procuraNomeContendo(String query) {
		return getSession().createQuery("from UF where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "sigla"};
	}
	
	@Transactional
	public List<UF> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from UF where lower(nome) like :q").setParameter("q", q).list();
	}

}
