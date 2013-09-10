package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.Transportadora;
import dc.servicos.dao.pessoal.TransportadoraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class TransportadoraListController extends CRUDListController<Transportadora> {

	@Autowired
	TransportadoraDAO dao;

	@Autowired
	TransportadoraFormController transportadoraFormController;

	@Override
	protected CRUDFormController<Transportadora> getFormController() {
		return transportadoraFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"observacao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaTransportadora";
	}

	@Override
	protected Class<? super Transportadora> getEntityClass() {
		return Transportadora.class;
	}
	

	@Override
	protected List<Transportadora> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Transportadora";
	}
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
		
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	
	@Override
	protected List<Transportadora> pesquisaDefault() {
		return (List<Transportadora>) dao.getAll(getEntityClass());
	}

}
