package dc.servicos.dao.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.livrocontabil.LivroTermoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LivroTermoDAO extends AbstractCrudDAO<LivroTermoEntity> {

	@Override
	public Class<LivroTermoEntity> getEntityClass() {
		return LivroTermoEntity.class;
	}

	@Transactional
	public List<LivroTermoEntity> listarTodos() {
		try {
			String sql = "FROM LivroTermoEntity ent WHERE (1 = 1)";

			List<LivroTermoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LivroTermoEntity>();
		}
	}

	@Transactional
	public List<LivroTermoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LivroTermoEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LivroTermoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LivroTermoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}