package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaEnderecoDAOImpl extends
		AbstractCrudDAO<PessoaEnderecoEntity> implements
		PessoaEnderecoDAO<PessoaEnderecoEntity> {

	private static Logger logger = Logger
			.getLogger(PessoaEnderecoDAOImpl.class);

	@Override
	public Class<PessoaEnderecoEntity> getEntityClass() {
		return PessoaEnderecoEntity.class;
	}

	public List<PessoaEnderecoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			// sql = sql.replace("-", "new PaisEntity(ent.id, ent.nomePtbr)");

			List<PessoaEnderecoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<PessoaEnderecoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaEnderecoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<PessoaEnderecoEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaEnderecoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nomeIngles", "nomePtbr", "sigla2", "sigla3" };
	}

	/**
	 * 
	 */

	public List<PessoaEnderecoEntity> list(PessoaEntity entity) {
		try {
			String sql = "select ent FROM :entity ent WHERE (1 = 1) AND ent.pessoa.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", entity.getId());

			List<PessoaEnderecoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}