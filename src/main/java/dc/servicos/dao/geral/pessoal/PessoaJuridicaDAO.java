package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalPessoaJuridicaDAO")
public class PessoaJuridicaDAO extends AbstractCrudDAO<PessoaJuridicaEntity> {

	@Override
	public Class<PessoaJuridicaEntity> getEntityClass() {
		return PessoaJuridicaEntity.class;
	}

	public PessoaJuridicaEntity getEntity(PessoaEntity ent) {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.pessoa.id = :ent";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("ent", ent.getId());

			PessoaJuridicaEntity entity = (PessoaJuridicaEntity) query
					.uniqueResult();

			if (entity == null) {
				entity = new PessoaJuridicaEntity();
			}

			return entity;
		} catch (Exception e) {
			return new PessoaJuridicaEntity();
		}
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("FROM PessoaJuridica").list();
	}

	@Transactional
	public PessoaJuridicaEntity getPessoaJuridica(int idPessoa) {
		return (PessoaJuridicaEntity) getSession()
				.createCriteria(PessoaJuridicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {};
	}

}