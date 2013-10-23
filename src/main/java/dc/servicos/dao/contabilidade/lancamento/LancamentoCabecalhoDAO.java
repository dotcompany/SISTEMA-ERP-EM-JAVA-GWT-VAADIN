package dc.servicos.dao.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.lancamento.LancamentoCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository(value = "contabilidadeLancamentoCabecalhoDAO")
@SuppressWarnings("unchecked")
public class LancamentoCabecalhoDAO extends
		AbstractCrudDAO<LancamentoCabecalhoEntity> {

	@Override
	public Class<LancamentoCabecalhoEntity> getEntityClass() {
		return LancamentoCabecalhoEntity.class;
	}

	@Transactional
	public List<LancamentoCabecalhoEntity> listarTodos() {
		try {
			String sql = "FROM LancamentoCabecalhoEntity ent WHERE (1 = 1)";

			List<LancamentoCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoCabecalhoEntity>();
		}
	}

	@Transactional
	public List<LancamentoCabecalhoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM LancamentoCabecalhoEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<LancamentoCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<LancamentoCabecalhoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}