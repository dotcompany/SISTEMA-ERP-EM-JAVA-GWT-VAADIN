package dc.controller.contratos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contratos.TipoContrato;
import dc.servicos.dao.contratos.TipoContratoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class TipoContratoListController extends CRUDListController<TipoContrato>{
	@Autowired
	TipoContratoDAO dao;
	
	@Autowired
	TipoContratoFormController tipoContratoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"nome", "descricao"};
	}

	@Override
	protected Class<? super TipoContrato > getEntityClass() {
		return TipoContrato.class;
	}


	@Override
	protected String getTitulo() {
		return "Tipo Contrato";
	}

	@Override
	protected List<TipoContrato> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<TipoContrato> getFormController() {
		return tipoContratoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTipoContrato";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
	
	@Override
	protected List<TipoContrato> pesquisaDefault() {
		return (List<TipoContrato>) dao.getAll(getEntityClass());
	}
	

}
