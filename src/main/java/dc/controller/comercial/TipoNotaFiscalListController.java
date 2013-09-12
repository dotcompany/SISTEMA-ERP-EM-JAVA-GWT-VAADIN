package dc.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.comercial.TipoNotaFiscal;
import dc.servicos.dao.comercial.TipoNotaFiscalDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoNotaFiscalListController
extends
CRUDListController<TipoNotaFiscal>{
	
	@Autowired
	TipoNotaFiscalDAO dao;
	
	@Autowired
	TipoNotaFiscalFormController formController;
	
	@Override
	protected String[] getColunas() {
		return new String[] {"modelo"};
	}

	@Override
	protected String getTitulo() {
		return "Tipo Nota Fiscal";
	}
	
	@Override
	protected CRUDFormController<TipoNotaFiscal> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super TipoNotaFiscal> getEntityClass() {
		return TipoNotaFiscal.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Tipo Nota Fiscal";
	}

	@Override
	protected List<TipoNotaFiscal> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<TipoNotaFiscal> pesquisaDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
