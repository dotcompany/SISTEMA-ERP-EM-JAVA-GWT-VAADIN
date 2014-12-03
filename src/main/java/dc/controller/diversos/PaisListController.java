package dc.controller.diversos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.PaisEntity;
import dc.servicos.dao.diversos.PaisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PaisListController extends CRUDListController<PaisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PaisDAO dao;

	@Autowired
	private PaisFormController paisFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nomeEn", "nomePtbr", "sigla2",
				"sigla3" };
	}

	@Override
	public Class<? super PaisEntity> getEntityClass() {
		return PaisEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Pais";
	}

	@Override
	protected List<PaisEntity> pesquisa(String valor) {
		try {
			List<PaisEntity> auxLista = (List<PaisEntity>) dao
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	@Override
	protected CRUDFormController<PaisEntity> getFormController() {
		return paisFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPais";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PaisEntity> pesquisaDefault() {
		try {
			List<PaisEntity> auxLista = (List<PaisEntity>) dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PaisEntity>();
		}
	}

	/**
	 * **************************************
	 */

}