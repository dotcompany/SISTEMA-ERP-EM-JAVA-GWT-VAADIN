package dc.servicos.dao.ordemservico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class InformacaoGeralDAO extends AbstractCrudDAO<InformacaoGeralEntity> {

	@Override
	protected Class<InformacaoGeralEntity> getEntityClass() {
		return InformacaoGeralEntity.class;
	}

	@Transactional
	public List<InformacaoGeralEntity> listarTodos() {
		try {
			String sql = "FROM InformacaoGeralEntity ent WHERE (1 = 1)";

			List<InformacaoGeralEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	@Transactional
	public List<InformacaoGeralEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM InformacaoGeralEntity ent WHERE (1 = 1) AND ent.nome LIKE :q";

			List<InformacaoGeralEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<InformacaoGeralEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Colaborador", "Dias afastado", "Data início",
				"Data término" };
	}

}