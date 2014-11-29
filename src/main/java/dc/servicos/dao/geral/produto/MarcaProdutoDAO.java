package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.MarcaProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class MarcaProdutoDAO extends AbstractCrudDAO<MarcaProdutoEntity> {

	@Override
	public Class<MarcaProdutoEntity> getEntityClass() {
		return MarcaProdutoEntity.class;
	}

	@Transactional
	public List<MarcaProdutoEntity> listaTodos() {
		return getSession().createQuery("from MarcaProduto").list();
	}

	@Transactional
	public List<MarcaProdutoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from MarcaProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<MarcaProdutoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from MarcaProduto where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}