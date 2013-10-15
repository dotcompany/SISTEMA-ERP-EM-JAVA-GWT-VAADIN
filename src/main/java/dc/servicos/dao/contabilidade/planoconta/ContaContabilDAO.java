package dc.servicos.dao.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.planoconta.ContaContabilEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class ContaContabilDAO extends AbstractCrudDAO<ContaContabilEntity> {

	@Override
	public Class<ContaContabilEntity> getEntityClass() {
		return ContaContabilEntity.class;
	}

	@Transactional
	public List<ContaContabilEntity> listarTodos() {
		try {
			String sql = "FROM ContaContabilEntity ent WHERE (1 = 1)";

			List<ContaContabilEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContaContabilEntity>();
		}
	}

	@Transactional
	public List<ContaContabilEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ContaContabilEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<ContaContabilEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ContaContabilEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}