package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaEndereco;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaEnderecoDAO extends AbstractCrudDAO<PessoaEndereco> {

	@Override
	public Class<PessoaEndereco> getEntityClass() {
		return PessoaEndereco.class;
	}

	@Transactional
	public List<PessoaEndereco> listaTodos() {
		return getSession().createQuery("from PessoaEndereco").list();
	}

	@Transactional
	public List<PessoaEndereco> listaPorEmpresa(Empresa empresa) {
		return getSession().createQuery("from PessoaEndereco c where c.empresa = :emp ").setParameter("emp", empresa).list();
	}

	@Transactional
	public List<PessoaEndereco> listaPorPessoa(Pessoa pessoa) {

		// TODO Verificar pq só funciona na segunda vez
		try {
			getSession().createQuery("from PessoaEndereco c where c.pessoa = :pessoa ").setParameter("pessoa", pessoa).list();
		} catch (Exception e) {

		}

		return getSession().createQuery("from PessoaEndereco c where c.pessoa = :pessoa ").setParameter("pessoa", pessoa).list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "nome", "email" };
	}

}