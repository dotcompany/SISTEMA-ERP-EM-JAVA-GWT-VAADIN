package dc.servicos.dao.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.produto.Produto;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * 
 * @author Wesley Jr
 *
 */

@Repository
@SuppressWarnings("unchecked")
public class ProdutoDAO extends AbstractCrudDAO<Produto> {

	@Override
	protected Class<Produto> getEntityClass() {
		return Produto.class;
	}
	
	@Transactional
	public List<Produto> listaTodos() {
		return getSession().createQuery("from Produto").list();
	}

	@Transactional
	public List<Produto> procuraNomeContendo(String query) {
		return getSession().createQuery("from Produto where nome like :q").setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[]{"gtin", "codigoInterno","nome","descricao","descricaoPdv"};
	}

}
