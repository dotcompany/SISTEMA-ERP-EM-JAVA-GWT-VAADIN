package dc.controller.comercial;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import dc.entidade.comercial.Orcamento;
import dc.servicos.dao.comercial.OrcamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class OrcamentoListController extends CRUDListController<Orcamento>{
	
	@Autowired
	OrcamentoDAO dao;
	
	@Autowired
	OrcamentoFormController formController;
	
	@Override
	protected String[] getColunas() {
		return new String[] {"id"};
	}

	@Override
	protected String getTitulo() {
		return "Orçamento";
	}
	
	@Override
	protected CRUDFormController<Orcamento> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Orcamento> getEntityClass() {
		return Orcamento.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Orçamento";
	}

	@Override
	protected List<Orcamento> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Orcamento> pesquisaDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
