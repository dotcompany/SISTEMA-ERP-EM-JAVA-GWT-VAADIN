package dc.servicos.dao.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaEnderecoDAO extends AbstractCrudDAO<PessoaEnderecoEntity> {

	@Override
	public Class<PessoaEnderecoEntity> getEntityClass() {
		return PessoaEnderecoEntity.class;
	}

	@Transactional
	public List<PessoaEnderecoEntity> listaTodos() {
		return getSession().createQuery("from PessoaEnderecoEntity").list();
	}

	@Transactional
	public List<PessoaEnderecoEntity> listaPorEmpresa(EmpresaEntity empresa) {
		return getSession()
				.createQuery(
						"from PessoaEnderecoEntity c where c.empresa = :emp")
				.setParameter("emp", empresa).list();
	}

	@Transactional
	public List<PessoaEnderecoEntity> listaPorPessoa(PessoaEntity pessoa) {
		// TODO Verificar pq só funciona na segunda vez
		try {
			getSession()
					.createQuery(
							"from PessoaEnderecoEntity c where c.pessoa = :pessoa ")
					.setParameter("pessoa", pessoa).list();
		} catch (Exception e) {

		}

		return getSession()
				.createQuery(
						"from PessoaEnderecoEntity c where c.pessoa = :pessoa ")
				.setParameter("pessoa", pessoa).list();
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "email" };
	}

	@Transactional
	public void deletePessoaEnderecoList(List<Serializable> auxLista) {
		try {
			String sql = "DELETE FROM :entity WHERE empresa.id IN (:auxLista)";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameterList("auxLista", auxLista);

			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<PessoaEnderecoEntity> getPessoaEnderecoList(
			EmpresaEntity empresa) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.empresa.id = :id";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", empresa.getId());

			List<PessoaEnderecoEntity> auxLista = query.list();

			if (auxLista == null) {
				auxLista = new ArrayList<PessoaEnderecoEntity>();
			}

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}