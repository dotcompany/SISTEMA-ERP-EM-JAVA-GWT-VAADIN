package dc.controller.ged;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ged.TipoDocumento;
import dc.servicos.dao.ged.TipoDocumentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TipoDocumentoListController extends CRUDListController<TipoDocumento>{
	@Autowired
	TipoDocumentoDAO dao;
	
	@Autowired
	TipoDocumentoFormController tipoDocumentoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"nome", "tamanhoMaximo"};
	}

	@Override
	protected Class<? super TipoDocumento > getEntityClass() {
		return TipoDocumento.class;
	}


	@Override
	protected String getTitulo() {
		return "Tipo Documento";
	}

	@Override
	protected List<TipoDocumento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<TipoDocumento> getFormController() {
		return tipoDocumentoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTipoDocumento";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected List<TipoDocumento> pesquisaDefault() {
		return (List<TipoDocumento>) dao.getAll(getEntityClass());
	}

}