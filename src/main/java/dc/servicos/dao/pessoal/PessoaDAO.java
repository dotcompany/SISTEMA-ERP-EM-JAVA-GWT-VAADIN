package dc.servicos.dao.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PessoaDAO extends AbstractCrudDAO<PessoaEntity> {

	/**
	 * DAOS
	 */

	@Autowired
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Autowired
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	/**
	 * 
	 */

	@Override
	public Class<PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("FROM PessoaEntity").list();
	}

	@Transactional
	public PessoaFisicaEntity getPessoaFisica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaFisicaEntity> list = getSession()
				.createCriteria(PessoaFisicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list();

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Transactional
	public PessoaJuridicaEntity getPessoaJuridica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaJuridicaEntity> list = getSession()
				.createCriteria(PessoaJuridicaEntity.class)
				.add(Restrictions.eq("pessoa.id", idPessoa)).list();

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Transactional
	public List<PessoaEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";

		return getSession()
				.createQuery("from Pessoa where lower(nome) like :q")
				.setParameter("q", q).list();
	}

	@Transactional
	public void deletePessoa(List<Serializable> ids) throws Exception {
		try {
			for (Serializable id : ids) {
				PessoaEntity ent = this.find(id);

				super.delete(ent);
			}
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public void saveOrUpdatePessoa(PessoaEntity entity) throws Exception {
		try {
			if (entity.getTipo().equals("F")) {
				if (entity.getPessoaJuridica() != null
						&& entity.getPessoaJuridica().getId() != null) {
					this.pessoaJuridicaDAO.delete(entity.getPessoaJuridica());
				}

				entity.setPessoaJuridica(null);
			} else if (entity.getTipo().equals("J")) {
				if (entity.getPessoaFisica() != null
						&& entity.getPessoaFisica().getId() != null) {
					this.pessoaFisicaDAO.delete(entity.getPessoaFisica());
				}

				entity.setPessoaFisica(null);
			}

			super.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "tipo", "email", "site" };
	}

}