package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.RegistroCartorioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class RegistroCartorioDAO extends
		AbstractCrudDAO<RegistroCartorioEntity> {

	@Override
	public Class<RegistroCartorioEntity> getEntityClass() {
		return RegistroCartorioEntity.class;
	}

	@Transactional
	public List<RegistroCartorioEntity> listarTodos() {
		try {
			String sql = "FROM RegistroCartorioEntity ent WHERE (1 = 1)";

			List<RegistroCartorioEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<RegistroCartorioEntity>();
		}
	}

	@Transactional
	public List<RegistroCartorioEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM RegistroCartorioEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<RegistroCartorioEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<RegistroCartorioEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}