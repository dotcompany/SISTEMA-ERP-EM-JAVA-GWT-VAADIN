package dc.servicos.dao.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.demonstrativo.DreCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class DreCabecalhoDAO extends AbstractCrudDAO<DreCabecalhoEntity> {

	@Override
	public Class<DreCabecalhoEntity> getEntityClass() {
		return DreCabecalhoEntity.class;
	}

	@Transactional
	public List<DreCabecalhoEntity> listarTodos() {
		try {
			String sql = "FROM DreCabecalhoEntity ent WHERE (1 = 1)";

			List<DreCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreCabecalhoEntity>();
		}
	}

	@Transactional
	public List<DreCabecalhoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM DreCabecalhoEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<DreCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DreCabecalhoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}