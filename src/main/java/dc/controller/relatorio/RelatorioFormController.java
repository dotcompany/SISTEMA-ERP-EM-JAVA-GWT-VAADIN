package dc.controller.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.FmMenu;
import dc.entidade.relatorio.Relatorio;
import dc.servicos.dao.framework.geral.FmMenuDAO;
import dc.servicos.dao.relatorio.RelatorioDAO;
import dc.servicos.util.Util;
import dc.servicos.util.Validator;
import dc.visao.framework.FmMenuListController;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.relatorio.RelatorioFormView;

@Controller
@Scope("prototype")
public class RelatorioFormController extends CRUDFormController<Relatorio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelatorioFormView subView;

	@Autowired
	private RelatorioDAO relatorioDAO;

	private Relatorio currentBean;

	@Autowired
	private FmMenuDAO fmMenuDAO;

	@Override
	protected String getNome() {
		return "Relatório";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			// currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			gravarAnexo();
			relatorioDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = relatorioDAO.find(id);
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new RelatorioFormView();

		DefaultManyToOneComboModel<FmMenu> menusModel = new DefaultManyToOneComboModel<FmMenu>(FmMenuListController.class, this.fmMenuDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "caption";
			}
		};

		subView.getComboMenus().setModel(menusModel);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new dc.entidade.relatorio.Relatorio();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		relatorioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;
		if (!Validator.validateString(subView.getTxNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxNome(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "relatorioForm";
	}

	private void gravarAnexo() throws IOException {
		File relatorioForm = (File) subView.getRelatorioUpload().getValue();

		String homePath = System.getProperty("user.home");
		String customCompanyBaseFolder = "dc-erp/reports";
		String arqOriginal = subView.getNomeRelatorio();

		String caminho = homePath + "/" + customCompanyBaseFolder + "/" + currentBean.getMenu().getUrlId() + "/" + arqOriginal;

		Util.copyFile(relatorioForm, new File(caminho));
		currentBean.setJasperPath(caminho);

		relatorioForm.delete();
	}

	@Override
	public Relatorio getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}
