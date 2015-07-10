package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.servicos.dao.financeiro.LancamentoPagarDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class LancamentoPagarListController extends CRUDListController<LancamentoPagarEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	LancamentoPagarDAO dao;

	@Autowired
	private LancamentoPagarFormController lancamentoPagarFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "pagamentoCompartilhado", "valorTotal", "valorAPagar", "dataLancamento", "imagemDocumento", "documentoOrigem",
				"fornecedor" };
	}

	@Override
	public Class<? super LancamentoPagarEntity> getEntityClass() {
		return LancamentoPagarEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento à Pagar";
	}

	@Override
	protected List<LancamentoPagarEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<LancamentoPagarEntity> getFormController() {
		return lancamentoPagarFormController;
	}

	@Override
	public String getViewIdentifier() {
		return "listaLancamentoPagar";
	}
	
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<LancamentoPagarEntity> pesquisaDefault() {
		return dao.getAll(LancamentoPagarEntity.class);
	}

}