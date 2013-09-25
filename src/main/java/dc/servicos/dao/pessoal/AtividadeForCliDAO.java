package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.AtividadeForCli;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class AtividadeForCliDAO extends AbstractCrudDAO<AtividadeForCli>{

	@Override
	public Class<AtividadeForCli> getEntityClass() {
		return AtividadeForCli.class;
	}

	@Transactional
	public List<AtividadeForCli> listaTodos() {
		return getSession().createQuery("from AtividadeForCli").list();
	}

	@Transactional
	public List<AtividadeForCli> procuraNomeContendo(String query) {
		return getSession().createQuery("from AtividadeForCli where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}
	
	@Transactional
	public List<AtividadeForCli> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from AtividadeForCli where lower(nome) like :q").setParameter("q", q).list();
	}

}
