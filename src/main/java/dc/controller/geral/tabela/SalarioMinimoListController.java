package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.SalarioMinimo;
import dc.servicos.dao.geral.tabela.SalarioMinimoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SalarioMinimoListController extends CRUDListController<SalarioMinimo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SalarioMinimoDAO dao;

	@Autowired
	private SalarioMinimoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "vigencia", "valorMensal", "valorDiario", "valorHora" };
	}

	@Override
	public Class<? super SalarioMinimo> getEntityClass() {
		return SalarioMinimo.class;
	}

	@Override
	protected String getTitulo() {
		return "Salário Mínimo";
	}

	@Override
	protected List<SalarioMinimo> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SalarioMinimo> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSalarioMinimo";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SalarioMinimo> pesquisaDefault() {
		return (List<SalarioMinimo>) dao.getAll(getEntityClass());
	}

}