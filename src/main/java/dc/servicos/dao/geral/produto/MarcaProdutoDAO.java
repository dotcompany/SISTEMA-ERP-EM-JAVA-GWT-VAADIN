package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.MarcaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class MarcaProdutoDAO extends AbstractCrudDAO<MarcaEntity> {

	@Override
	public Class<MarcaEntity> getEntityClass() {
		return MarcaEntity.class;
	}

	@Transactional
	public List<MarcaEntity> listaTodos() {
		return getSession().createQuery("from MarcaProduto").list();
	}

	@Transactional
	public List<MarcaEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from MarcaProduto where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<MarcaEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession()
				.createQuery("from MarcaProduto where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}