package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.TipoServico;
import dc.servicos.dao.ordemservico.TipoServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoServicoListController extends CRUDListController<TipoServico> {

	private static final long serialVersionUID = 1L;

	@Autowired
	TipoServicoDAO dao;
	
	@Autowired
	TipoServicoFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Tipo de Serviço";
	}

	@Override
	protected List<TipoServico> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaTipoServico";
	}

	@Override
	protected CRUDFormController<TipoServico> getFormController() {
		return formController;
	}

	@Override
	public Class<? super TipoServico> getEntityClass() {
		return TipoServico.class;
	}

	@Override
	protected List<TipoServico> pesquisaDefault() {
		return dao.getAll(TipoServico.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
