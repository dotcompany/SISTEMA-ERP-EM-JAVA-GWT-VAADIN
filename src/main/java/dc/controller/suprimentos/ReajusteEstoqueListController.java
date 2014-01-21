package dc.controller.suprimentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.suprimentos.ReajusteEstoque;
import dc.servicos.dao.suprimentos.ReajusteEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ReajusteEstoqueListController extends CRUDListController<ReajusteEstoque> {

	@Autowired
	ReajusteEstoqueDAO dao;

	@Autowired
	ReajusteEstoqueFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "colaborador", "dataReajuste", "tipoString" };
	}

	@Override
	protected String getTitulo() {
		return "Reajuste de Preço";
	}

	@Override
	protected List<ReajusteEstoque> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaRequisicaoInterna";
	}

	@Override
	protected CRUDFormController<ReajusteEstoque> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ReajusteEstoque> getEntityClass() {
		return ReajusteEstoque.class;
	}

	@Override
	protected List<ReajusteEstoque> pesquisaDefault() {
		return dao.getAll(ReajusteEstoque.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

}
