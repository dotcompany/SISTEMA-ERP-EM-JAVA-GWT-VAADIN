package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.framework.EmpresaEntity;
import dc.servicos.dao.framework.geral.EmpresaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão do CrudListController,
 *         tendo alguns métodos herdados, como o pesquisar, e pegamos também
 *         algumas informações da classe FormController, herdando algumas
 *         informações. Temos a configuração das unas.
 * 
 */

@Controller
@Scope("prototype")
public class EmpresaListController extends CRUDListController<EmpresaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpresaDAO dao;

	@Autowired
	private EmpresaFormController empresaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nomeFantasia", "razaoSocial" };
	}

	@Override
	public Class<? super EmpresaEntity> getEntityClass() {
		return EmpresaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Empresa";
	}

	@Override
	protected List<EmpresaEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EmpresaEntity> getFormController() {
		return empresaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaEmpresa";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<EmpresaEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		List<EmpresaEntity> auxLista = (List<EmpresaEntity>) this.dao.getAll(getEntityClass());

		return (List<EmpresaEntity>) this.dao.getAll(getEntityClass());
	}

}