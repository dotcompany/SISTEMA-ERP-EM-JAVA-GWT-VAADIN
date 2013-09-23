package dc.servicos.dao.folhapagamento.ausencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class AfastamentoDAO extends AbstractCrudDAO<AfastamentoEntity> {

	@Override
	public Class<AfastamentoEntity> getEntityClass() {
		return AfastamentoEntity.class;
	}

	@Transactional
	public List<AfastamentoEntity> listarTodos() {
		try {
			String sql = "FROM AfastamentoEntity ent WHERE (1 = 1)";

			List<AfastamentoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<AfastamentoEntity>();
		}
	}

	@Transactional
	public List<AfastamentoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM AfastamentoEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<AfastamentoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<AfastamentoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Dias afastado", "Data início",
				"Data término" };
	}

}