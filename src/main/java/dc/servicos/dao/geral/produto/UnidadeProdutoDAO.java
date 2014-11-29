package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class UnidadeProdutoDAO extends AbstractCrudDAO<UnidadeProdutoEntity> {

	@Override
	public Class<UnidadeProdutoEntity> getEntityClass() {
		return UnidadeProdutoEntity.class;
	}

	@Transactional
	public List<UnidadeProdutoEntity> listaTodos() {
		return getSession().createQuery("from UnidadeProduto").list();
	}

	@Transactional
	public List<UnidadeProdutoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from UnidadeProduto where sigla like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "sigla", "descricao" };
	}

	@Transactional
	public List<UnidadeProdutoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from UnidadeProduto where lower(sigla) like :q")
				.setParameter("q", q).list();
	}

}