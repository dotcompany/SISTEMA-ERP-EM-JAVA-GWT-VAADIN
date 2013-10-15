package dc.servicos.dao.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.livrocontabil.EmissaoLivroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class EmissaoLivroDAO extends AbstractCrudDAO<EmissaoLivroEntity> {

	@Override
	public Class<EmissaoLivroEntity> getEntityClass() {
		return EmissaoLivroEntity.class;
	}

	@Transactional
	public List<EmissaoLivroEntity> listarTodos() {
		try {
			String sql = "FROM EmissaoLivroEntity ent WHERE (1 = 1)";

			List<EmissaoLivroEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EmissaoLivroEntity>();
		}
	}

	@Transactional
	public List<EmissaoLivroEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM EmissaoLivroEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<EmissaoLivroEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EmissaoLivroEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}