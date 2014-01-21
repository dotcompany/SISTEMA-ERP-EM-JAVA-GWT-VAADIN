package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.NivelFormacao;
import dc.servicos.dao.geral.NivelFormacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * **/

@Controller
@Scope("prototype")
public class NivelFormacaoListController extends CRUDListController<NivelFormacao> {

	@Autowired
	NivelFormacaoDAO dao;

	@Autowired
	NivelFormacaoFormController nivelFormacaoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super NivelFormacao> getEntityClass() {
		return NivelFormacao.class;
	}

	@Override
	protected String getTitulo() {
		return "Nivel Formação";
	}

	@Override
	protected List<NivelFormacao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<NivelFormacao> getFormController() {
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
	protected List<NivelFormacao> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<NivelFormacao>) dao.getAll(getEntityClass());
	}

}
