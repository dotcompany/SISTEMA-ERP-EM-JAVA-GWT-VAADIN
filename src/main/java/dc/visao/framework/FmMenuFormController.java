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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			this.currentBean
					.setCaption(this.subView.getTxtCaption().getValue());
			this.currentBean.setUrlId(this.subView.getTxtURL().getValue());
			this.currentBean.setFmModulo(this.subView.getModulo());
			this.currentBean.setParent(this.subView.getMenu());
			this.currentBean.setControllerClass(this.subView.getTxtController()
					.getValue());

			boolean b1 = this.subView.getCkbPermissaoOperacao().getValue();

			this.currentBean.setPermissaoOperacao((b1 == true ? 1 : 0));

			boolean b2 = this.subView.getCkbConsultaMultiempresa().getValue();

			this.currentBean.setConsultaMultiempresa((b2 == true ? 1 : 0));

			this.fmDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		System.out.println("carregando menu, vai printar pai");
		this.currentBean = this.fmDAO.find(id);

		carregaCombos(this.currentBean.getFmModulo());

		this.subView.getTxtCaption().setValue(this.currentBean.getCaption());
		this.subView.getTxtURL().setValue(this.currentBean.getUrlId());
		this.subView.getComboModulos().setValue(this.currentBean.getFmModulo());
		this.subView.setParentMenu(this.currentBean.getParent());
		this.subView.getCkbPermissaoOperacao().setValue(
				(this.currentBean.getPermissaoOperacao() == 1 ? true : false));
		this.subView.getCkbConsultaMultiempresa()
				.setValue(
						(this.currentBean.getConsultaMultiempresa() == 1 ? true
								: false));

		if (this.currentBean.getControllerClass() != null
				&& !"".equals(this.currentBean.getControllerClass())) {
			this.subView.getTxtController().setValue(
					this.currentBean.getControllerClass());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
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
		List<FmModulo> modulos = this.fmDAO.getAllModulos();

		this.subView.populaModulos(modulos);
	}

	public void carregaComboMenus(FmModulo module) {
		List<FmMenu> menus;

		if (module != null) {
			menus = this.fmDAO.getAllMenusByModuleId(module.getId());
		} else {
			menus = this.fmDAO.getAll(FmMenu.class);
		}

		this.subView.populaMenus(menus);
	}

	@Override
	protected void initSubView() {
		this.subView = new FmMenuFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.currentBean = new FmMenu();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		this.fmDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (estaVazio(this.subView.getTxtCaption())
				|| estaVazio(this.subView.getTxtURL())) {
			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		this.fmDAO.deleteAll(objetos);

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "fmMenuForm";
	}

}