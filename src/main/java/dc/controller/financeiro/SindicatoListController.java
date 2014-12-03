package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.SindicatoEntity;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SindicatoListController extends CRUDListController<SindicatoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SindicatoDAO dao;

	@Autowired
	private SindicatoFormController sindicatoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "logradouro" };
	}

	@Override
	public Class<? super SindicatoEntity> getEntityClass() {
		return SindicatoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<SindicatoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SindicatoEntity> getFormController() {
		return sindicatoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<SindicatoEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<SindicatoEntity>) dao.getAll(getEntityClass());
	}

}