package dc.controller.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.NCM;
import dc.servicos.dao.produto.NCMDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class NCMListController extends CRUDListController<NCM> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	NCMDAO dao;

	@Autowired
	NCMFormController ncmFormController;

	@Override
	protected CRUDFormController<NCM> getFormController() {
		return ncmFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaNCM";
	}

	@Override
	protected Class<? super NCM> getEntityClass() {
		return NCM.class;
	}

	@Override
	protected List<NCM> pesquisa(String valor) {
		try {
			return (List<NCM>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NCM>();
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
	protected List<NCM> pesquisaDefault() {
		try {
			return (List<NCM>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NCM>();
		}
	}

}