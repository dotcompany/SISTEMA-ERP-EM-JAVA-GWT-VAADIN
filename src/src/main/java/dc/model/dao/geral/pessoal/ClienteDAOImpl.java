package dc.model.dao.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ClienteDAOImpl extends AbstractCrudDAO<ClienteEntity> implements
		ClienteDAO<ClienteEntity> {

	private static Logger logger = Logger.getLogger(ClienteDAOImpl.class);

	@Override
	public Class<ClienteEntity> getEntityClass() {
		return ClienteEntity.class;
	}

	public List<ClienteEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<ClienteEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ClienteEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<ClienteEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + query + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ClienteEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<ClienteEntity> auxLista = super.getSession().createQuery(sql)
					.setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public ClienteEntity findById(ClienteEntity cliente) {
		try {

			String sql = "SELECT distinct ent FROM # AS ent INNER JOIN FETCH ent.pessoa p LEFT JOIN FETCH p.pessoaEnderecoList AS pe WHERE (1 = 1) AND ent.id = :id";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", cliente.getId());

			ClienteEntity cli = new ClienteEntity();
			
			cli = (ClienteEntity) query.uniqueResult();

			return cli;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}
	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}