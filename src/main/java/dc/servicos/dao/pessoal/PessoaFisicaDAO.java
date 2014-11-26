package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaFisicaDAO extends AbstractCrudDAO<PessoaFisicaEntity> {

	@Override
	public Class<PessoaFisicaEntity> getEntityClass() {
		return PessoaFisicaEntity.class;
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("from PessoaFisica").list();
	}

	@Transactional
	public PessoaFisicaEntity getPessoaFisica(int idPessoa) {
		return (PessoaFisicaEntity) getSession().createCriteria(PessoaFisicaEntity.class).add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}

}
