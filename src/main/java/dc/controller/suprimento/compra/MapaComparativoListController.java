package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.CotacaoEntity;
import dc.servicos.dao.suprimentos.compra.CotacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class MapaComparativoListController extends CRUDListController<CotacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CotacaoDAO dao;

	@Autowired
	private MapaComparativoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataCotacao", "descricao", "situacao" };
	}

	@Override
	protected String getTitulo() {
		return "Mapa Comparativo";
	}

	@Override
	protected List<CotacaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<CotacaoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super CotacaoEntity> getEntityClass() {
		return CotacaoEntity.class;
	}

	@Override
	protected List<CotacaoEntity> pesquisaDefault() {
		return dao.getAll(CotacaoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}