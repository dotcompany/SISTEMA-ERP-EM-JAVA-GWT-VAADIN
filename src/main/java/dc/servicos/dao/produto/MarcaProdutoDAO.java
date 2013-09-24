package dc.servicos.dao.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.produto.MarcaProduto;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class MarcaProdutoDAO extends AbstractCrudDAO<MarcaProduto>{

	@Override
	public Class<MarcaProduto> getEntityClass() {
		return MarcaProduto.class;
	}

	@Transactional
	public List<MarcaProduto> listaTodos() {
		return getSession().createQuery("from MarcaProduto").list();
	}

	@Transactional
	public List<MarcaProduto> procuraNomeContendo(String query) {
		return getSession().createQuery("from MarcaProduto where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}
	
	@Transactional
	public List<MarcaProduto> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from MarcaProduto where lower(nome) like :q").setParameter("q", q).list();
	}

}
