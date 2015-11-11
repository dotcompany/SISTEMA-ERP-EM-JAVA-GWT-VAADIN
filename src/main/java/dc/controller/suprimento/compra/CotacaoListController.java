package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.CotacaoEntity;
import dc.servicos.dao.suprimentos.compra.ICotacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CotacaoListController extends CRUDListController<CotacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CotacaoFormController cotacaoFormController;

	@Autowired
	private ICotacaoDAO dao;

	/**
	 * CONSTRUTOR
	 */

	public CotacaoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<CotacaoEntity> getFormController() {
		return cotacaoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {  "dataCotacao", "descricao", "situacao"};
	}

	@Override
	public Class<? super CotacaoEntity> getEntityClass() {
		return CotacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<CotacaoEntity> pesquisa(String valor) {
		try {
			List<CotacaoEntity> auxLista = (List<CotacaoEntity>) this.dao
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<CotacaoEntity> pesquisaDefault() {
		try {
			List<CotacaoEntity> auxLista = (List<CotacaoEntity>) this.dao
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}