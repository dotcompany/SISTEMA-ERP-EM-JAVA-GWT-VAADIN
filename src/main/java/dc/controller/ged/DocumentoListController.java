package dc.controller.ged;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ged.Documento;
import dc.servicos.dao.ged.DocumentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class DocumentoListController extends CRUDListController<Documento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	DocumentoDAO dao;

	@Autowired
	DocumentoFormController documentoFormController;

	@Override
	protected CRUDFormController<Documento> getFormController() {
		return documentoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"nome", "descricao", "palavraChave", "podeExcluir", "podeAlterar", "dataFimVigencia"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaDocumento";
	}

	@Override
	protected Class<? super Documento> getEntityClass() {
		return Documento.class;
	}

	@Override
	protected List<Documento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Documento";
	}
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
		
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	protected List<Documento> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Documento>) dao.getAll(getEntityClass());
	}

	
	
}
	
	

