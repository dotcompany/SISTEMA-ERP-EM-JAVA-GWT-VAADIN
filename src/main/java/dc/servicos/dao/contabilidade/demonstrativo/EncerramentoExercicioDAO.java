package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.EncerramentoExercicioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class EncerramentoExercicioDAO extends
		AbstractCrudDAO<EncerramentoExercicioEntity> {

	@Override
	public Class<EncerramentoExercicioEntity> getEntityClass() {
		return EncerramentoExercicioEntity.class;
	}

	@Transactional
	public List<EncerramentoExercicioEntity> listarTodos() {
		try {
			String sql = "FROM EncerramentoExercicioEntity ent WHERE (1 = 1)";

			List<EncerramentoExercicioEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EncerramentoExercicioEntity>();
		}
	}

	@Transactional
	public List<EncerramentoExercicioEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM EncerramentoExercicioEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<EncerramentoExercicioEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<EncerramentoExercicioEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}