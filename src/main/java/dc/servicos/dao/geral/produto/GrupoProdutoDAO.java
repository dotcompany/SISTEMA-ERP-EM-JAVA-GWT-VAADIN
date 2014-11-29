package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.GrupoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class GrupoProdutoDAO extends AbstractCrudDAO<GrupoEntity> {

	@Override
	public Class<GrupoEntity> getEntityClass() {
		return GrupoEntity.class;
	}

	@Transactional
	public List<GrupoEntity> listaTodos() {
		return getSession().createQuery("from GrupoProduto").list();
	}

	@Transactional
	public List<GrupoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from GrupoProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}