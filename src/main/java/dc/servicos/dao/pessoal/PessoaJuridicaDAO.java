package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaJuridicaDAO extends AbstractCrudDAO<PessoaJuridicaEntity> {

	@Override
	public Class<PessoaJuridicaEntity> getEntityClass() {
		return PessoaJuridicaEntity.class;
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("from PessoaJuridica").list();
	}

	@Transactional
	public PessoaJuridicaEntity getPessoaJuridica(int idPessoa) {
		return (PessoaJuridicaEntity) getSession().createCriteria(PessoaJuridicaEntity.class).add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}

}
