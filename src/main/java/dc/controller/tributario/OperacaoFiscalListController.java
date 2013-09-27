package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.OperacaoFiscal;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class OperacaoFiscalListController extends
		CRUDListController<OperacaoFiscal> {

	@Autowired
	OperacaoFiscalDAO dao;

	@Autowired
	OperacaoFiscalFormController formController;

	@Override
	protected String[] getColunas() {
		return new String[] { "cfop" };
	}

	@Override
	protected String getTitulo() {
		return "Operação Fiscal";
	}

	@Override
	protected List<OperacaoFiscal> pesquisa(String valor) {
		return new ArrayList<>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaOperacaoFiscal";
	}

	@Override
	protected CRUDFormController<OperacaoFiscal> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super OperacaoFiscal> getEntityClass() {
		return OperacaoFiscal.class;
	}

	@Override
	protected List<OperacaoFiscal> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}