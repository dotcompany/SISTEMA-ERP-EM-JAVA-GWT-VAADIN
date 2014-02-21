package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDeclaracaoImportacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDeclaracaoImportacaoDAO extends
		AbstractCrudDAO<NfeDeclaracaoImportacaoEntity> {

	@Override
	public Class<NfeDeclaracaoImportacaoEntity> getEntityClass() {
		return NfeDeclaracaoImportacaoEntity.class;
	}

	@Transactional
	public List<NfeDeclaracaoImportacaoEntity> listarTodos() {
		try {
			String sql = "FROM NfeDeclaracaoImportacaoEntity ent WHERE (1 = 1)";

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());

			List<NfeDeclaracaoImportacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDeclaracaoImportacaoEntity>();
		}
	}

	@Transactional
	public List<NfeDeclaracaoImportacaoEntity> procuraNomeContendo(String s) {
		try {
			String sql = "FROM NfeDeclaracaoImportacaoEntity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";

			Query query = super.getSession().createQuery(sql);
			// query.setParameter("entity", getEntityClass());
			query.setParameter("q", "%" + s + "%");

			List<NfeDeclaracaoImportacaoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDeclaracaoImportacaoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Número do documento" };
	}

}