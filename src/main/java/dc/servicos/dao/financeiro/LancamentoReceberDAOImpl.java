package dc.servicos.dao.financeiro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.financeiro.LancamentoReceber;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
@Repository
public class LancamentoReceberDAOImpl extends AbstractCrudDAO<LancamentoReceber> implements LancamentoReceberDAOf<LancamentoReceber> {
	
	
	private static Logger logger = Logger.getLogger(LancamentoReceberDAOImpl.class);

	@Override
	public Class<LancamentoReceber> getEntityClass() {
		return LancamentoReceber.class;
	}

	public List<LancamentoReceber> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
	    	List<LancamentoReceber> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<LancamentoReceber> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<LancamentoReceber> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<LancamentoReceber> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);
			List<LancamentoReceber> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "valorTotal" };
	}

}
