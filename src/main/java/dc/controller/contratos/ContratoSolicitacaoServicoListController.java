package dc.controller.contratos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contratos.ContratoSolicitacaoServico;
import dc.servicos.dao.contratos.ContratoSolicitacaoServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class ContratoSolicitacaoServicoListController extends CRUDListController<ContratoSolicitacaoServico> {
	@Autowired
	ContratoSolicitacaoServicoDAO dao;

	@Autowired
	ContratoSolicitacaoServicoFormController contratoSolicitacaoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "fornecedor", "cliente", "setor", "colaborador", "contratoTipoServico" };
	}

	@Override
	public Class<? super ContratoSolicitacaoServico> getEntityClass() {
		return ContratoSolicitacaoServico.class;
	}

	@Override
	protected String getTitulo() {
		return "Solicitação de serviço";
	}

	@Override
	protected List<ContratoSolicitacaoServico> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ContratoSolicitacaoServico> getFormController() {
		return contratoSolicitacaoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaContratoSolicitacaoServico";
	}

	@Override
	protected boolean deletaEmCascata() {

		return false;
	}

	@Override
	protected List<ContratoSolicitacaoServico> pesquisaDefault() {
		return (List<ContratoSolicitacaoServico>) dao.getAll(getEntityClass());
	}

}
