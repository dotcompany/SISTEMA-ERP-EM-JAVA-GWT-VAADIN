package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaFisica;
import dc.entidade.geral.PessoaJuridica;
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
public class PessoaDAO extends AbstractCrudDAO<Pessoa> {

	@Override
	public Class<Pessoa> getEntityClass() {
		return Pessoa.class;
	}

	@Transactional
	public List<Pessoa> listaTodos() {
		return getSession().createQuery("from Pessoa").list();
	}

	@Transactional
	public PessoaFisica getPessoaFisica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaFisica> list = getSession().createCriteria(PessoaFisica.class).add(Restrictions.eq("pessoa.id", idPessoa)).list();
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Transactional
	public PessoaJuridica getPessoaJuridica(Integer idPessoa) {
		if (idPessoa == null) {
			return null;
		}

		List<PessoaJuridica> list = getSession().createCriteria(PessoaJuridica.class).add(Restrictions.eq("pessoa.id", idPessoa)).list();
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
	public List<Pessoa> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession().createQuery("from Pessoa where lower(nome) like :q").setParameter("q", q).list();
	}

}
