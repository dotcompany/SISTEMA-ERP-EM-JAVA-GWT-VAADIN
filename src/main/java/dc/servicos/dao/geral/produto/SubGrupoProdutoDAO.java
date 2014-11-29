package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.SubGrupoProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class SubGrupoProdutoDAO extends AbstractCrudDAO<SubGrupoProdutoEntity> {

	@Override
	public Class<SubGrupoProdutoEntity> getEntityClass() {
		return SubGrupoProdutoEntity.class;
	}

	@Transactional
	public List<SubGrupoProdutoEntity> listaTodos() {
		return getSession().createQuery("from SubGrupoProduto").list();
	}

	@Transactional
	public List<SubGrupoProdutoEntity> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from SubGrupoProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<SubGrupoProdutoEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from SubGrupoProduto where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}