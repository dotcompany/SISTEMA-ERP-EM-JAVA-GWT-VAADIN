package dc.servicos.dao.diversos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.OperadoraPlanoSaude;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Wesley Jr
 */

@Repository
@SuppressWarnings("unchecked")
public class OperadoraPlanoSaudeDAO extends
		AbstractCrudDAO<OperadoraPlanoSaude> {

	@Override
	public Class<OperadoraPlanoSaude> getEntityClass() {
		return OperadoraPlanoSaude.class;
	}

	@Transactional
	public List<OperadoraPlanoSaude> listaTodos() {
		return getSession().createQuery("from OperadoraPlanoSaude").list();
	}

	@Transactional
	public List<OperadoraPlanoSaude> procuraNomeContendo(String query) {
		return getSession()
				.createQuery("from OperadoraPlanoSaude where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "registroAns", "nome" };
	}

	/**
	 * ********************************************************
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@Transactional
	public List<OperadoraPlanoSaude> operadoraPlanoSaudeLista() {
		try {
			String sql = "SELECT new OperadoraPlanoSaude(ent.id, ent.nome) FROM OperadoraPlanoSaude ent"
					+ " WHERE (1 = 1)";

			List<OperadoraPlanoSaude> auxLista = getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<OperadoraPlanoSaude>();
		}
	}

}