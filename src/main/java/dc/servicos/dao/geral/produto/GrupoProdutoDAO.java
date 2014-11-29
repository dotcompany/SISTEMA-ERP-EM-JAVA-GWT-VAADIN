package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.GrupoProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class GrupoProdutoDAO extends AbstractCrudDAO<GrupoProdutoEntity> {

	@Override
	public Class<GrupoProdutoEntity> getEntityClass() {
		return GrupoProdutoEntity.class;
	}

	@Transactional
	public List<GrupoProdutoEntity> listaTodos() {
		return getSession().createQuery("from GrupoProduto").list();
	}

	@Transactional
	public List<GrupoProdutoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from GrupoProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}