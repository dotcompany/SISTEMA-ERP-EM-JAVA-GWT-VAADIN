package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.ParametroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository(value = "parametroDAO1")
@SuppressWarnings("unchecked")
public class ParametroDAO extends AbstractCrudDAO<ParametroEntity> {

	@Override
	public Class<ParametroEntity> getEntityClass() {
		return ParametroEntity.class;
	}

	@Transactional
	public List<ParametroEntity> listarTodos() {
		try {
			String sql = "FROM ParametroEntity ent WHERE (1 = 1)";

			List<ParametroEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametroEntity>();
		}
	}

	@Transactional
	public List<ParametroEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM ParametroEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<ParametroEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametroEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}