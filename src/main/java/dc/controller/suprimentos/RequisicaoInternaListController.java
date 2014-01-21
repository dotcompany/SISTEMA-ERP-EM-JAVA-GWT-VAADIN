package dc.controller.suprimentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.suprimentos.RequisicaoInterna;
import dc.servicos.dao.suprimentos.RequisicaoInternaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class RequisicaoInternaListController extends CRUDListController<RequisicaoInterna> {

	@Autowired
	RequisicaoInternaDAO dao;

	@Autowired
	RequisicaoInternaFormController formController;

	public RequisicaoInternaListController() {

		// Empresa empresa = usuario.getConta().getEmpresa();
		// System.out.println("");
	}

	@Override
	public String[] getColunas() {
		return new String[] { "colaborador", "dataRequisicao" };
	}

	@Override
	protected String getTitulo() {
		return "Requisição Interna";
	}

	@Override
	protected List<RequisicaoInterna> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaRequisicaoInterna";
	}

	@Override
	protected CRUDFormController<RequisicaoInterna> getFormController() {
		return formController;
	}

	@Override
	public Class<? super RequisicaoInterna> getEntityClass() {
		return RequisicaoInterna.class;
	}

	@Override
	protected List<RequisicaoInterna> pesquisaDefault() {
		return dao.getAll(RequisicaoInterna.class);
		// //return dao.findBySetor();
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}
