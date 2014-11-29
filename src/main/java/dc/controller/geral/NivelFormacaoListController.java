package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.NivelFormacaoEntity;
import dc.servicos.dao.geral.NivelFormacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * **/

@Controller
@Scope("prototype")
public class NivelFormacaoListController extends CRUDListController<NivelFormacaoEntity> {

	@Autowired
	NivelFormacaoDAO dao;

	@Autowired
	NivelFormacaoFormController nivelFormacaoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super NivelFormacaoEntity> getEntityClass() {
		return NivelFormacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Nivel Formação";
	}

	@Override
	protected List<NivelFormacaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<NivelFormacaoEntity> getFormController() {
		return nivelFormacaoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaNivelFormacao";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<NivelFormacaoEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<NivelFormacaoEntity>) dao.getAll(getEntityClass());
	}

}
