package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.ConfiguracaoBoleto;
import dc.entidade.financeiro.ContaCaixa;
import dc.servicos.dao.financeiro.ConfiguracaoBoletoDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.visao.financeiro.ConfiguracaoBoletoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ConfiguracaoBoletoFormController extends CRUDFormController<ConfiguracaoBoleto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConfiguracaoBoletoFormView subView;

	@Autowired
	private ConfiguracaoBoletoDAO configuracaoBoletoDAO;

	private ConfiguracaoBoleto currentBean;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Override
	protected String getNome() {
		return "Configuração Boleto";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			configuracaoBoletoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = configuracaoBoletoDAO.find(id);
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
		subView = new ConfiguracaoBoletoFormView();

		DefaultManyToOneComboModel<ContaCaixa> contaCaixaModel = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class,
				this.contaCaixaDAO, super.getMainController());

		this.subView.getCbContaCaixa().setModel(contaCaixaModel);

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new ConfiguracaoBoleto();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		configuracaoBoletoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "configuracaoBoletoForm";
	}
}
