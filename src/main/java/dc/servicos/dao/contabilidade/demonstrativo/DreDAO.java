package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.DreEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class DreDAO extends AbstractCrudDAO<DreEntity> {

	@Override
	public Class<DreEntity> getEntityClass() {
		return DreEntity.class;
	}

	@Transactional
	public List<DreEntity> listarTodos() {
		try {
			String sql = "FROM DreEntity ent WHERE (1 = 1)";

			List<DreEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreEntity>();
		}
	}

	@Transactional
	public List<DreEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DreEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<DreEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}