package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.SituacaoForCli;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class SituacaoForCliDAO extends AbstractCrudDAO<SituacaoForCli>{

	@Override
	protected Class<SituacaoForCli> getEntityClass() {
		return SituacaoForCli.class;
	}

	@Transactional
	public List<SituacaoForCli> listaTodos() {
		return getSession().createQuery("from SituacaoForCli").list();
	}

	@Transactional
	public List<SituacaoForCli> procuraNomeContendo(String query) {
		return getSession().createQuery("from SituacaoForCli where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}
	
	@Transactional
	public List<SituacaoForCli> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from SituacaoForCli where lower(nome) like :q").setParameter("q", q).list();
	}

}
