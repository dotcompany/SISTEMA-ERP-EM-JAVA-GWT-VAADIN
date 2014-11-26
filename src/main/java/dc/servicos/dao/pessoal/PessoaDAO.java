package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão a classe principal
 *         abstractCrudDao e dela herdamos alguns métodos, fazemos uma Conexão
 *         com o Banco, uma listagem E aqui herdamos também o Método do
 *         pesquisar, onde nela colocamos os campos que colocamos as anotações
 *         lá no TO (ENTIDADE), que vai ser pesquisado na Tela quando rodar o
 *         projeto.
 * 
 */

@Repository
@SuppressWarnings("unchecked")
public class PessoaDAO extends AbstractCrudDAO<PessoaEntity> {

	@Override
	public Class<PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Transactional
	public List<PessoaEntity> listaTodos() {
		return getSession().createQuery("from Pessoa").list();
	}

	@Transactional
	public PessoaFisicaEntity getPessoaFisica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaFisicaEntity> list = getSession().createCriteria(PessoaFisicaEntity.class).add(Restrictions.eq("pessoa.id", idPessoa)).list();
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

		List<PessoaJuridicaEntity> list = getSession().createCriteria(PessoaJuridicaEntity.class).add(Restrictions.eq("pessoa.id", idPessoa)).list();
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "tipo", "email", "site" };
	}

	@Transactional
	public List<PessoaEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession().createQuery("from Pessoa where lower(nome) like :q").setParameter("q", q).list();
	}

}
