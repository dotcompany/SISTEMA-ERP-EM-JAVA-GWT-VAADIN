package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.LaudoTecnicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LaudoTecnicoDAO extends AbstractCrudDAO<LaudoTecnicoEntity> {

	@Override
	public Class<LaudoTecnicoEntity> getEntityClass() {
		return LaudoTecnicoEntity.class;
	}

	@Transactional
	public List<LaudoTecnicoEntity> listarTodos() {
		try {
			String sql = "FROM LaudoTecnicoEntity ent WHERE (1 = 1)";

			List<LaudoTecnicoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LaudoTecnicoEntity>();
		}
	}

	@Transactional
	public List<LaudoTecnicoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LaudoTecnicoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<LaudoTecnicoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LaudoTecnicoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Dias afastado", "Data início",
				"Data término" };
	}

}