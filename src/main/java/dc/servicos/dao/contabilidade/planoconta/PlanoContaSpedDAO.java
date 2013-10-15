package dc.servicos.dao.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.planoconta.PlanoContaSpedEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PlanoContaSpedDAO extends AbstractCrudDAO<PlanoContaSpedEntity> {

	@Override
	public Class<PlanoContaSpedEntity> getEntityClass() {
		return PlanoContaSpedEntity.class;
	}

	@Transactional
	public List<PlanoContaSpedEntity> listarTodos() {
		try {
			String sql = "FROM PlanoContaSpedEntity ent WHERE (1 = 1)";

			List<PlanoContaSpedEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaSpedEntity>();
		}
	}

	@Transactional
	public List<PlanoContaSpedEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PlanoContaSpedEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<PlanoContaSpedEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaSpedEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}