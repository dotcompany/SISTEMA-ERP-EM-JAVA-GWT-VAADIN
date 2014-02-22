package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetEspecificoMedicamentoDAO extends
		AbstractCrudDAO<NfeDetEspecificoMedicamentoEntity> {

	@Override
	public Class<NfeDetEspecificoMedicamentoEntity> getEntityClass() {
		return NfeDetEspecificoMedicamentoEntity.class;
	}

	@Transactional
	public List<NfeDetEspecificoMedicamentoEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetEspecificoMedicamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoMedicamentoEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoMedicamentoEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetEspecificoMedicamentoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoMedicamentoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "CST PIS" };
	}

}