package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.SituacaoColaborador;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class SituacaoColaboradorDAO extends AbstractCrudDAO<SituacaoColaborador>{

	@Override
	protected Class<SituacaoColaborador> getEntityClass() {
		return SituacaoColaborador.class;
	}

	@Transactional
	public List<SituacaoColaborador> listaTodos() {
		return getSession().createQuery("from SituacaoColaborador").list();
	}

	@Transactional
	public List<SituacaoColaborador> procuraNomeContendo(String query) {
		return getSession().createQuery("from SituacaoColaborador where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo", "nome", "descricao"};
	}
	
	@Transactional
	public List<SituacaoColaborador> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from SituacaoColaborador where lower(nome) like :q").setParameter("q", q).list();
	}

}
