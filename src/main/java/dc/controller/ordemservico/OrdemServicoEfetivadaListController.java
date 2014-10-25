package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.OrdemServico;
import dc.servicos.dao.ordemservico.OrdemServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class OrdemServicoEfetivadaListController extends CRUDListController<OrdemServico> {

	private static final long serialVersionUID = 1L;

	@Autowired
	OrdemServicoDAO dao; 
	
	@Autowired
	private OrdemServicoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"dataCadastro","cliente","valorTotalOs"};
	}

	@Override
	protected String getTitulo() {
		return "Ordem de serviço Efetivada";
	}

	@Override
	protected List<OrdemServico> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	} 
	
	@Override
	public String getViewIdentifier() {
		return "listaOrdemServicoEfetivada";
	}

	@Override
	protected CRUDFormController<OrdemServico> getFormController() {
		return formController;
	}

	@Override
	public Class<? super OrdemServico> getEntityClass() {
		return OrdemServico.class;
	}

	@Override
	protected List<OrdemServico> pesquisaDefault() {
		return dao.getAll(OrdemServico.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}
