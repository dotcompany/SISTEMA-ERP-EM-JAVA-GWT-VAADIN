package dc.servicos.dao.folhapagamento.inss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.folhapagamento.inss.RetencaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class RetencaoDAO extends AbstractCrudDAO<RetencaoEntity> {

	@Override
	public Class<RetencaoEntity> getEntityClass() {
		return RetencaoEntity.class;
	}

	@Transactional
	public List<RetencaoEntity> listarTodos() {
		try {
			String sql = "FROM RetencaoEntity ent WHERE (1 = 1)";

			List<RetencaoEntity> auxLista = super.getSession().createQuery(sql)
					.list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<RetencaoEntity>();
		}
	}

	@Transactional
	public List<RetencaoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM RetencaoEntity ent WHERE (1 = 1) AND ent.servico.nome LIKE :q";

			List<RetencaoEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<RetencaoEntity>();
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "INSS", "Serviço", "Valor mensal", "Valor 13" };
	}

}