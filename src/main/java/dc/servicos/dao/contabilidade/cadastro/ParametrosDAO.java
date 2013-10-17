package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.ParametrosEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository(value = "contabilidadeParametrosDAO")
@SuppressWarnings("unchecked")
public class ParametrosDAO extends AbstractCrudDAO<ParametrosEntity> {

	@Override
	public Class<ParametrosEntity> getEntityClass() {
		return ParametrosEntity.class;
	}

	@Transactional
	public List<ParametrosEntity> listarTodos() {
		try {
			String sql = "FROM ParametrosEntity ent WHERE (1 = 1)";

			List<ParametrosEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametrosEntity>();
		}
	}

	@Transactional
	public List<ParametrosEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ParametrosEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<ParametrosEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametrosEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}