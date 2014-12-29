package dc.model.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.PessoaFisicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaFisicaDAOImpl extends AbstractCrudDAO<PessoaFisicaEntity>
		implements PessoaFisicaDAO<PessoaFisicaEntity> {

	private static Logger logger = Logger.getLogger(PessoaFisicaDAOImpl.class);

	@Override
	public Class<PessoaFisicaEntity> getEntityClass() {
		return PessoaFisicaEntity.class;
	}

	public List<PessoaFisicaEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			// sql = sql.replace("-", "new PaisEntity(ent.id, ent.nomePtbr)");

			List<PessoaFisicaEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<PessoaFisicaEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaFisicaEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<PessoaFisicaEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nomeEn LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<PessoaFisicaEntity> auxLista = super.getSession()
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

}