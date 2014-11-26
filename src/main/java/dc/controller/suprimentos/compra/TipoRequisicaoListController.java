package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.compra.TipoRequisicaoEntity;
import dc.servicos.dao.suprimentos.compra.TipoRequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoRequisicaoListController extends
		CRUDListController<TipoRequisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoRequisicaoDAO dao;

	@Autowired
	private TipoRequisicaoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "id", "codigo", "nome", "descricao" };
	}

	@Override
	protected String getTitulo() {
		return "Tipo Requisição";
	}

	@Override
	protected List<TipoRequisicaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected CRUDFormController<TipoRequisicaoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super TipoRequisicaoEntity> getEntityClass() {
		return TipoRequisicaoEntity.class;
	}

	@Override
	protected List<TipoRequisicaoEntity> pesquisaDefault() {
		return dao.getAll(TipoRequisicaoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}