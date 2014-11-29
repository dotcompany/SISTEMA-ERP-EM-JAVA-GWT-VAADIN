package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.produto.NcmEntity;
import dc.servicos.dao.geral.produto.NCMDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class NCMListController extends CRUDListController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	NCMDAO dao;

	@Autowired
	NCMFormController ncmFormController;

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
		return "listaNCM";
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
		return "NCM";
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