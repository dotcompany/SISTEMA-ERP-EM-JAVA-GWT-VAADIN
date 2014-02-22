package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetEspecificoCombustivelDAO extends
		AbstractCrudDAO<NfeDetEspecificoCombustivelEntity> {

	@Override
	public Class<NfeDetEspecificoCombustivelEntity> getEntityClass() {
		return NfeDetEspecificoCombustivelEntity.class;
	}

	@Transactional
	public List<NfeDetEspecificoCombustivelEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetEspecificoCombustivelEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoCombustivelEntity>();
		}
	}

	@Transactional
	public List<NfeDetEspecificoCombustivelEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetEspecificoCombustivelEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetEspecificoCombustivelEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "CST PIS" };
	}

}