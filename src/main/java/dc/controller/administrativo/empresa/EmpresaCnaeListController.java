package dc.controller.administrativo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.servicos.dao.administrativo.empresa.EmpresaCnaeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class EmpresaCnaeListController extends
		CRUDListController<EmpresaCnaeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpresaCnaeDAO dao;

	@Autowired
	private EmpresaCnaeFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "cnae", "principalStr", "ramoAtividade",
				"objetoSocial" };
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<EmpresaCnaeEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<EmpresaCnaeEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super EmpresaCnaeEntity> getEntityClass() {
		return EmpresaCnaeEntity.class;
	}

	@Override
	protected List<EmpresaCnaeEntity> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}