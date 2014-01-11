package dc.servicos.dao.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class NfeCabecalhoDAO extends AbstractCrudDAO<NfeCabecalhoEntity> {

	@Override
	public Class<NfeCabecalhoEntity> getEntityClass() {
		return NfeCabecalhoEntity.class;
	}

	@Transactional
	public List<NfeCabecalhoEntity> listarTodos() {
		try {
			String sql = "FROM NfeCabecalhoEntity ent WHERE (1 = 1)";

			List<NfeCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

	@Transactional
	public List<NfeCabecalhoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM NfeCabecalhoEntity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";

			List<NfeCabecalhoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<NfeCabecalhoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "INSS", "Serviço", "Valor mensal", "Valor 13" };
	}

}