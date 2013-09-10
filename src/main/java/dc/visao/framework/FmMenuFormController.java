package dc.visao.framework;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.FmMenu;
import dc.entidade.framework.FmModulo;
import dc.servicos.dao.framework.geral.FmMenuDAO;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FmMenuFormController extends CRUDFormController<FmMenu> {

	FmMenuFormView subView;

	@Autowired
	FmMenuDAO fmDAO;

	private FmMenu currentBean;

	@Override
	protected String getNome() {
		return "Menu";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setCaption(subView.getTxtCaption().getValue());
			currentBean.setUrlId(subView.getTxtURL().getValue());
			currentBean.setFmModulo(subView.getModulo());
			currentBean.setParent(subView.getMenu());
			currentBean.setControllerClass(subView.getTxtController()
					.getValue());
			fmDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		System.out.println("carregando menu, vai printar pai");
		currentBean = fmDAO.find(id);
		carregaCombos(currentBean.getFmModulo());
		subView.getTxtCaption().setValue(currentBean.getCaption());
		subView.getTxtURL().setValue(currentBean.getUrlId());
		subView.getComboModulos().setValue(currentBean.getFmModulo());
		subView.setParentMenu(currentBean.getParent());
		if (currentBean.getControllerClass() != null
				&& !"".equals(currentBean.getControllerClass())) {
			subView.getTxtController().setValue(
					currentBean.getControllerClass());
		}

	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {
		carregaCombos(null);
	}

	private void carregaCombos(FmModulo fmModulo) {
		carregaComboModulos();
		carregaComboMenus(fmModulo);
	}

	private void carregaComboModulos() {
		List<FmModulo> modulos = fmDAO.getAllModulos();
		subView.populaModulos(modulos);
	}

	public void carregaComboMenus(FmModulo module) {
		List<FmMenu> menus;
		if (module != null) {
			menus = fmDAO.getAllMenusByModuleId(module.getId());
		} else {
			menus = fmDAO.getAll(FmMenu.class);
		}

		subView.populaMenus(menus);
	}

	@Override
	protected void initSubView() {
		subView = new FmMenuFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new FmMenu();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		fmDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (estaVazio(subView.getTxtCaption())
				|| estaVazio(subView.getTxtURL())) {
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		fmDAO.deleteAll(objetos);
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "fmMenuForm";
	}

}
