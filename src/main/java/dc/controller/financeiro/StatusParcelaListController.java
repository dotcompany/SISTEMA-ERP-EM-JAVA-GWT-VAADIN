package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class StatusParcelaListController extends CRUDListController<StatusParcela>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StatusParcelaDAO dao;
	
	@Autowired
	private StatusParcelaFormController statusParcelaFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"situacao", "descricao", "procedimento"};
	}

	@Override
	protected Class<? super StatusParcela> getEntityClass() {
		return StatusParcela.class;
	}


	@Override
	protected String getTitulo() {
		return "Status Parcela";
	}

	@Override
	protected List<StatusParcela> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<StatusParcela> getFormController() {
		return statusParcelaFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaStatusParcelas";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<StatusParcela> pesquisaDefault() {
		return (List<StatusParcela>) dao.getAll(getEntityClass());
	}

}	
