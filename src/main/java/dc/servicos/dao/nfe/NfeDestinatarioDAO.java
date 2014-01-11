package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeDestinatarioDAO extends AbstractCrudDAO<NfeDestinatarioEntity> {

	@Override
	public Class<NfeDestinatarioEntity> getEntityClass() {
		return NfeDestinatarioEntity.class;
	}

	@Transactional
	public List<NfeDestinatarioEntity> listarTodos() {
		try {
			String sql = "FROM NfeDestinatarioEntity ent WHERE (1 = 1)";

			List<NfeDestinatarioEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDestinatarioEntity>();
		}
	}

	@Transactional
	public List<NfeDestinatarioEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM NfeDestinatarioEntity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";

			List<NfeDestinatarioEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeDestinatarioEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "INSS", "Serviço", "Valor mensal", "Valor 13" };
	}

}