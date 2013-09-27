package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.entidade.tributario.GrupoTributario;
import dc.entidade.tributario.OperacaoFiscal;
import dc.servicos.dao.tributario.ConfiguracaoTributariaDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.ConfiguracaoTributariaFormView;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ConfiguracaoTributariaFormController extends
		CRUDFormController<ConfiguracaoTributaria> {

	ConfiguracaoTributariaFormView subView;

	@Autowired
	ConfiguracaoTributariaDAO dao;

	@Autowired
	GrupoTributarioDAO grupoTributarioDAO;

	@Autowired
	OperacaoFiscalDAO operacaoFiscalDAO;

	ConfiguracaoTributaria currentBean;

	String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Override
	public String getViewIdentifier() {
		return "ConfiguracaoTributariaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ConfiguracaoTributaria();
	}

	@Override
	protected void initSubView() {
		subView = new ConfiguracaoTributariaFormView(this);

		DefaultManyToOneComboModel<GrupoTributario> model = new DefaultManyToOneComboModel<GrupoTributario>(
				GrupoTributarioListController.class, this.grupoTributarioDAO,
				super.getMainController());

		this.subView.getCmbGrupoTributario().setModel(model);

		DefaultManyToOneComboModel<OperacaoFiscal> model1 = new DefaultManyToOneComboModel<OperacaoFiscal>(
				OperacaoFiscalListController.class, this.operacaoFiscalDAO,
				super.getMainController());

		this.subView.getCmbOperacaoFiscal().setModel(model1);
	}

	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);

		DefaultManyToOneComboModel<GrupoTributario> model = new DefaultManyToOneComboModel<GrupoTributario>(
				GrupoTributarioListController.class, this.grupoTributarioDAO,
				super.getMainController());

		this.subView.getCmbGrupoTributario().setModel(model);
		this.subView.getCmbGrupoTributario().setValue(
				this.currentBean.getGrupoTributario());

		DefaultManyToOneComboModel<OperacaoFiscal> model1 = new DefaultManyToOneComboModel<OperacaoFiscal>(
				OperacaoFiscalListController.class, this.operacaoFiscalDAO,
				super.getMainController());

		this.subView.getCmbOperacaoFiscal().setModel(model1);
		this.subView.getCmbOperacaoFiscal().setValue(
				this.currentBean.getOperacaoFiscal());
	}

	public Empresa empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setEmpresa(empresaAtual());
			currentBean.setGrupoTributario((GrupoTributario) subView
					.getCmbGrupoTributario().getValue());
			currentBean.setOperacaoFiscal((OperacaoFiscal) subView
					.getCmbOperacaoFiscal().getValue());
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			mensagemErro("Erro!!");
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			// subView.filContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Configuracao Tributaria";
	}

	@Override
	protected void remover(List<Serializable> ids) {

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<GrupoTributario> trazerGrupos() {
		return grupoTributarioDAO.listaTodos();
	}

	public List<OperacaoFiscal> trazerOperacoes() {
		return operacaoFiscalDAO.listaTodos();
	}

}