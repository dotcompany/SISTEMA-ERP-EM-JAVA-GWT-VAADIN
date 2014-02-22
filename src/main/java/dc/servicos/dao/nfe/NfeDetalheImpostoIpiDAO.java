package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoIpiEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheImpostoIpiDAO extends
		AbstractCrudDAO<NfeDetalheImpostoIpiEntity> {

	@Override
	public Class<NfeDetalheImpostoIpiEntity> getEntityClass() {
		return NfeDetalheImpostoIpiEntity.class;
	}

	@Transactional
	public List<NfeDetalheImpostoIpiEntity> listarTodos() {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheImpostoIpiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheImpostoIpiEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheImpostoIpiEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheImpostoIpiEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Enquadramento do IPI" };
	}

}