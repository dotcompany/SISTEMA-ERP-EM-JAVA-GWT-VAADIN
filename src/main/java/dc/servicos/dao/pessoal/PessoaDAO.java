package dc.servicos.dao.pessoal;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaFisica;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
/*
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o Método do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações lá no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
*/


@Repository
@SuppressWarnings("unchecked")
public class PessoaDAO extends AbstractCrudDAO<Pessoa> {
	
	@Override
	protected Class<Pessoa> getEntityClass() {
		return Pessoa.class;
	}


	@Transactional
	public List<Pessoa> listaTodos() {
		return getSession().createQuery("from Pessoa").list();
	}
	
	@Transactional
	public PessoaFisica getPessoaFisica(int idPessoa) {
		return (PessoaFisica) getSession().createCriteria(PessoaFisica.class).add(Restrictions.eq("pessoa.id", idPessoa)).list().get(0);
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "email"};
	}
	
	@Transactional
	public List<Pessoa> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Pessoa where lower(nome) like :q").setParameter("q", q).list();
	}

}
