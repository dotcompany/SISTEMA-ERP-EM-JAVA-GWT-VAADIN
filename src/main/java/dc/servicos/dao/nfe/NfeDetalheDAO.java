package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDetalheDAO extends AbstractCrudDAO<NfeDetalheEntity> {

	@Override
	public Class<NfeDetalheEntity> getEntityClass() {
		return NfeDetalheEntity.class;
	}

	@Transactional
	public List<NfeDetalheEntity> listarTodos() {
		try {
			String sql = "FROM NfeDetalheEntity ent WHERE (1 = 1)";

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDetalheEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheEntity>();
		}
	}

	@Transactional
	public List<NfeDetalheEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM NfeDetalheEntity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDetalheEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDetalheEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Número do item" };
	}

}