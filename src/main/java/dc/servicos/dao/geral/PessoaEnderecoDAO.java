package dc.servicos.dao.geral;

import java.util.List;

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
						"from PessoaEnderecoEntity c where c.empresa = :emp ")
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

}