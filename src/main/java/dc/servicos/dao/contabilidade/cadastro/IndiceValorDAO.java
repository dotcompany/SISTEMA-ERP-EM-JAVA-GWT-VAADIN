package dc.servicos.dao.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.cadastro.IndiceValorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class IndiceValorDAO extends AbstractCrudDAO<IndiceValorEntity> {

	@Override
	public Class<IndiceValorEntity> getEntityClass() {
		return IndiceValorEntity.class;
	}

	@Transactional
	public List<IndiceValorEntity> listarTodos() {
		try {
			String sql = "FROM IndiceValorEntity ent WHERE (1 = 1)";

			List<IndiceValorEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceValorEntity>();
		}
	}

	@Transactional
	public List<IndiceValorEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM IndiceValorEntity ent WHERE (1 = 1) AND ent.competencia LIKE :q";

			List<IndiceValorEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceValorEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Competência" };
	}

}