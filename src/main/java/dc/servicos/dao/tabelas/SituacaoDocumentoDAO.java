package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SituacaoDocumento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SituacaoDocumentoDAO extends AbstractCrudDAO<SituacaoDocumento>{

	@Override
	protected Class<SituacaoDocumento> getEntityClass() {
		return SituacaoDocumento.class;
	}
	
	@Transactional
	public List<SituacaoDocumento> listaTodos() {
		return getSession().createQuery("from SituacaoDocumento").list();
	}

	@Transactional
	public List<SituacaoDocumento> procuraNomeContendo(String query) {
		return getSession().createQuery("from SituacaoDocumento where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}

}
