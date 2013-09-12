package dc.servicos.dao.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.produto.SubGrupoProduto;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class SubGrupoProdutoDAO extends AbstractCrudDAO<SubGrupoProduto>{

	@Override
	protected Class<SubGrupoProduto> getEntityClass() {
		return SubGrupoProduto.class;
	}

	@Transactional
	public List<SubGrupoProduto> listaTodos() {
		return getSession().createQuery("from SubGrupoProduto").list();
	}

	@Transactional
	public List<SubGrupoProduto> procuraNomeContendo(String query) {
		return getSession().createQuery("from SubGrupoProduto where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}
	
	@Transactional
	public List<SubGrupoProduto> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from SubGrupoProduto where lower(nome) like :q").setParameter("q", q).list();
	}

}
