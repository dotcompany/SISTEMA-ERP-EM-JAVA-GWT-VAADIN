package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.Equipamento;
import dc.servicos.dao.ordemservico.EquipamentoDAO;
import dc.visao.framework.geral.CRUDFormController; 
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class EquipamentoListController extends CRUDListController<Equipamento> {

	private static final long serialVersionUID = 1L;

	@Autowired
	EquipamentoDAO dao;
	
	@Autowired
	EquipamentoFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"equipamento","descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Equipamento";
	}

	@Override
	protected List<Equipamento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaEquipamento";
	}

	@Override
	protected CRUDFormController<Equipamento> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Equipamento> getEntityClass() {
		return Equipamento.class;
	}

	@Override
	protected List<Equipamento> pesquisaDefault() {
		return dao.getAll(Equipamento.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
