package dc.servicos.dao.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.produto.GrupoProduto;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class GrupoProdutoDAO extends AbstractCrudDAO<GrupoProduto>{

	@Override
	protected Class<GrupoProduto> getEntityClass() {
		return GrupoProduto.class;
	}

	@Transactional
	public List<GrupoProduto> listaTodos() {
		return getSession().createQuery("from GrupoProduto").list();
	}

	@Transactional
	public List<GrupoProduto> procuraNomeContendo(String query) {
		return getSession().createQuery("from GrupoProduto where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}

}
