package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.TalonarioCheque;
import dc.servicos.dao.financeiro.TalonarioChequeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TalonarioChequeListController extends CRUDListController<TalonarioCheque> {
	@Autowired
	TalonarioChequeDAO dao;

	@Autowired
	TalonarioChequeFormController talonarioChequeFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "talao", "statusTalao", "numero" };
	}

	@Override
	public Class<? super TalonarioCheque> getEntityClass() {
		return TalonarioCheque.class;
	}

	@Override
	protected String getTitulo() {
		return "Talonário Cheque";
	}

	@Override
	protected List<TalonarioCheque> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TalonarioCheque> getFormController() {
		return talonarioChequeFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTalonarioCheque";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<TalonarioCheque> pesquisaDefault() {
		return (List<TalonarioCheque>) dao.getAll(getEntityClass());
	}

}
