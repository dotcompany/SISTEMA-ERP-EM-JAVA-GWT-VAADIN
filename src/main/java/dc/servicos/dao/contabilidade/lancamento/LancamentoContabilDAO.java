package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoContabilEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoContabilDAO extends
		AbstractCrudDAO<LancamentoContabilEntity> {

	@Override
	public Class<LancamentoContabilEntity> getEntityClass() {
		return LancamentoContabilEntity.class;
	}

	@Transactional
	public List<LancamentoContabilEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoContabilEntity ent WHERE (1 = 1)";

			List<LancamentoContabilEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoContabilEntity>();
		}
	}

	@Transactional
	public List<LancamentoContabilEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoContabilEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LancamentoContabilEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoContabilEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}