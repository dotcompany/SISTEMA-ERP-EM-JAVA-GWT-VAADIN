package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.OperacaoFiscal;
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
public class OperacaoFiscalDAO extends AbstractCrudDAO<OperacaoFiscal> {
	
	@Override
	protected Class<OperacaoFiscal> getEntityClass() {
		return OperacaoFiscal.class;
	}


	@Transactional
	public List<OperacaoFiscal> listaTodos() {
		return getSession().createQuery("from OperacaoFiscal").list();
	}
	
	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao", "descricaoNaNf"};
	}
	
	@Transactional
	public List<OperacaoFiscal> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from OperacaoFiscal where lower(descricao) like :q").setParameter("q", q).list();
	}

}
