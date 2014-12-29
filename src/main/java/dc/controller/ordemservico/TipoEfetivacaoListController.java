package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.TipoEfetivacaoEntity;
import dc.servicos.dao.ordemservico.TipoEfetivacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoEfetivacaoListController extends CRUDListController<TipoEfetivacaoEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	TipoEfetivacaoDAO dao;
	
	@Autowired
	TipoEfetivacaoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"codigo","descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Descrição";
	}

	@Override
	protected List<TipoEfetivacaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaTipoEfetivacao";
	}

	@Override
	protected CRUDFormController<TipoEfetivacaoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super TipoEfetivacaoEntity> getEntityClass() {
		return TipoEfetivacaoEntity.class;
	}

	@Override
	protected List<TipoEfetivacaoEntity> pesquisaDefault() {
		return dao.getAll(TipoEfetivacaoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
