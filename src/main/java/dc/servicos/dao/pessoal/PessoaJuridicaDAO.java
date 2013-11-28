package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaJuridica;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaJuridicaDAO extends AbstractCrudDAO<PessoaJuridica> {

	@Override
	public Class<PessoaJuridica> getEntityClass() {
		return PessoaJuridica.class;
	}

	@Transactional
	public List<Pessoa> listaTodos() {
		return getSession().createQuery("from PessoaJuridica").list();
	}

	@Transactional
	public PessoaJuridica getPessoaJuridica(int idPessoa) {
		return (PessoaJuridica) getSession().createCriteria(PessoaJuridica.class).add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}

}
