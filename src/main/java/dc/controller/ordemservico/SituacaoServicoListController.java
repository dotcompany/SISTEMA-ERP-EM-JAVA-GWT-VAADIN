package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.SituacaoServico;
import dc.servicos.dao.ordemservico.SituacaoServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class SituacaoServicoListController extends CRUDListController<SituacaoServico> {

	private static final long serialVersionUID = 1L;

	@Autowired
	SituacaoServicoDAO dao;
	
	@Autowired
	SituacaoServicoFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Situação de Serviço";
	}

	@Override
	protected List<SituacaoServico> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaSituacaoServico";
	}

	@Override
	protected CRUDFormController<SituacaoServico> getFormController() {
		return formController;
	}

	@Override
	public Class<? super SituacaoServico> getEntityClass() {
		return SituacaoServico.class;
	}

	@Override
	protected List<SituacaoServico> pesquisaDefault() {
		return dao.getAll(SituacaoServico.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
