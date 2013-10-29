package dc.servicos.dao.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.contabilidade.planoconta.PlanoContaRefSpedEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository(value = "contabilidadePlanoContaRefSpedDAO")
@SuppressWarnings("unchecked")
public class PlanoContaRefSpedDAO extends
		AbstractCrudDAO<PlanoContaRefSpedEntity> {

	@Override
	public Class<PlanoContaRefSpedEntity> getEntityClass() {
		return PlanoContaRefSpedEntity.class;
	}

	@Transactional
	public List<PlanoContaRefSpedEntity> listarTodos() {
		try {
			String sql = "FROM PlanoContaRefSpedEntity ent WHERE (1 = 1)";

			List<PlanoContaRefSpedEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaRefSpedEntity>();
		}
	}

	@Transactional
	public List<PlanoContaRefSpedEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM PlanoContaRefSpedEntity ent WHERE (1 = 1) AND ent.descricao LIKE :q";

			List<PlanoContaRefSpedEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<PlanoContaRefSpedEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "Descrição", "Orientações", "Início da validade",
				"Término da validade" };
	}

}