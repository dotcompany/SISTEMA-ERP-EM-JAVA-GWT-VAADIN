package dc.controller.suprimentos.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.estoque.ReajusteEstoque;
import dc.servicos.dao.suprimentos.estoque.ReajusteEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ReajusteEstoqueListController extends
		CRUDListController<ReajusteEstoque> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return ClasseUtil.getUrl(this);
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