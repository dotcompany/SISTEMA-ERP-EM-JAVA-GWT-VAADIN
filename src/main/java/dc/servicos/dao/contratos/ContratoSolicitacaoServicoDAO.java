package dc.servicos.dao.contratos;

import org.springframework.stereotype.Repository;

import dc.entidade.contratos.ContratoSolicitacaoServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContratoSolicitacaoServicoDAO extends
		AbstractCrudDAO<ContratoSolicitacaoServico> {

	@Override
	public Class<ContratoSolicitacaoServico> getEntityClass() {
		return ContratoSolicitacaoServico.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		
		
		//teste
		 //return null;
		
		return new String[] { "urgente", "descricao", "statusSolicitacao" };
	}

}
