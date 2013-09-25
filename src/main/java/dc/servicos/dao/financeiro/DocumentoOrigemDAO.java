package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoPagar;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class DocumentoOrigemDAO extends AbstractCrudDAO<DocumentoOrigem>{

	@Override
	public Class<DocumentoOrigem> getEntityClass() {
		return DocumentoOrigem.class;
	}
	
	
	@Transactional
	public List<LancamentoPagar> listLancamentos(DocumentoOrigem documento) {
		return getSession().createQuery("from LancamentoPagar where documento.id = :bid").setParameter("bid", documento.getId()).list();
	}


	@Transactional
	public List<DocumentoOrigem> listaTodos() {
		return getSession().createQuery("from DocumentoOrigem").list();
	}

	@Transactional
	public List<DocumentoOrigem> procuraNomeContendo(String query) {
		return getSession().createQuery("from DocumentoOrigem where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
}
