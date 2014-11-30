package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.NcmEntity;
import dc.servicos.dao.geral.produto.NcmDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class NcmListController extends CRUDListController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	NcmDAO dao;

	@Autowired
	NcmFormController ncmFormController;

	@Override
	protected CRUDFormController<NcmEntity> getFormController() {
		return ncmFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super NcmEntity> getEntityClass() {
		return NcmEntity.class;
	}

	@Override
	protected List<NcmEntity> pesquisa(String valor) {
		try {
			return (List<NcmEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NcmEntity>();
		}
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
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
	protected List<NcmEntity> pesquisaDefault() {
		try {
			return (List<NcmEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NcmEntity>();
		}
	}

}