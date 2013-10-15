package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoProgramadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoProgramadoDAO extends
		AbstractCrudDAO<LancamentoProgramadoEntity> {

	@Override
	public Class<LancamentoProgramadoEntity> getEntityClass() {
		return LancamentoProgramadoEntity.class;
	}

	@Transactional
	public List<LancamentoProgramadoEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoProgramadoEntity ent WHERE (1 = 1)";

			List<LancamentoProgramadoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoProgramadoEntity>();
		}
	}

	@Transactional
	public List<LancamentoProgramadoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoProgramadoEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LancamentoProgramadoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoProgramadoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}