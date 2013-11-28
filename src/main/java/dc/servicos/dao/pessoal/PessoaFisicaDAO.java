package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaFisica;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaFisicaDAO extends AbstractCrudDAO<PessoaFisica> {

	@Override
	public Class<PessoaFisica> getEntityClass() {
		return PessoaFisica.class;
	}

	@Transactional
	public List<Pessoa> listaTodos() {
		return getSession().createQuery("from PessoaFisica").list();
	}

	@Transactional
	public PessoaFisica getPessoaFisica(int idPessoa) {
		return (PessoaFisica) getSession().createCriteria(PessoaFisica.class).add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}

}
