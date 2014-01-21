package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SituacaoDocumento;
import dc.servicos.dao.tabelas.SituacaoDocumentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SituacaoDocumentoListController extends CRUDListController<SituacaoDocumento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SituacaoDocumentoDAO dao;

	@Autowired
	SituacaoDocumentoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super SituacaoDocumento> getEntityClass() {
		return SituacaoDocumento.class;
	}

	@Override
	protected String getTitulo() {
		return "Situação Documento";
	}

	@Override
	protected List<SituacaoDocumento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SituacaoDocumento> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSituacaoDocumento";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SituacaoDocumento> pesquisaDefault() {
		return (List<SituacaoDocumento>) dao.getAll(getEntityClass());
	}

}