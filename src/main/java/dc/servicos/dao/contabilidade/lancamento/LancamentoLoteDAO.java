package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoLoteEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoLoteDAO extends AbstractCrudDAO<LancamentoLoteEntity> {

	@Override
	public Class<LancamentoLoteEntity> getEntityClass() {
		return LancamentoLoteEntity.class;
	}

	@Transactional
	public List<LancamentoLoteEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoLoteEntity ent WHERE (1 = 1)";

			List<LancamentoLoteEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoLoteEntity>();
		}
	}

	@Transactional
	public List<LancamentoLoteEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoLoteEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LancamentoLoteEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoLoteEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Descrição", "Data da inclusão",
				"Data da liberacao" };
	}

}