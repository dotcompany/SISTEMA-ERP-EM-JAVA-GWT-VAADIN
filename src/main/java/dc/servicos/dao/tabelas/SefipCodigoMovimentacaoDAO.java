package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SefipCodigoMovimentacao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SefipCodigoMovimentacaoDAO extends AbstractCrudDAO<SefipCodigoMovimentacao>{

	@Override
	public Class<SefipCodigoMovimentacao> getEntityClass() {
		return SefipCodigoMovimentacao.class;
	}
	
	@Transactional
	public List<SefipCodigoMovimentacao> listaTodos() {
		return getSession().createQuery("from SefipCodigoMovimentacao").list();
	}

	@Transactional
	public List<SefipCodigoMovimentacao> procuraNomeContendo(String query) {
		return getSession().createQuery("from SefipCodigoMovimentacao where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "aplicacao"};
	}


}
