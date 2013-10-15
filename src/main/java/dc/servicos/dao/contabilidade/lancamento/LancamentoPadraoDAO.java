package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoPadraoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class LancamentoPadraoDAO extends
		AbstractCrudDAO<LancamentoPadraoEntity> {

	@Override
	public Class<LancamentoPadraoEntity> getEntityClass() {
		return LancamentoPadraoEntity.class;
	}

	@Transactional
	public List<LancamentoPadraoEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoPadraoEntity ent WHERE (1 = 1)";

			List<LancamentoPadraoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoPadraoEntity>();
		}
	}

	@Transactional
	public List<LancamentoPadraoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoPadraoEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LancamentoPadraoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoPadraoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}