package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.financeiro.PlanoCentroResultado;
import dc.entidade.geral.Usuario;
import dc.servicos.dao.financeiro.PlanoCentroResultadoDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.PlanoCentroResultadoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PlanoCentroResultadoFormController extends CRUDFormController<PlanoCentroResultado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoCentroResultadoFormView subView;

	@Autowired
	private PlanoCentroResultadoDAO planocentroresultadoDAO;

	private PlanoCentroResultado currentBean;

	@Autowired
	private PlanoCentroResultadoDAO planoresultadoDAO;

	@Override
	protected String getNome() {
		return "Plano Centro Resultado";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setMascara(subView.getTxtMascara().getValue().toString());
		currentBean.setNiveis((BigDecimal) subView.getTxtNiveis().getConvertedValue());
		currentBean.setDataInclusao(subView.getDtDataInclusao().getValue());
		Usuario usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
		try {
			planocentroresultadoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = planocentroresultadoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtMascara().setValue(currentBean.getMascara());
		subView.getTxtNiveis().setValue(currentBean.getNiveis().toString());
		subView.getDtDataInclusao().setValue(currentBean.getDataInclusao());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new PlanoCentroResultadoFormView();

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new PlanoCentroResultado();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		planocentroresultadoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtDataInclusao().getValue())) {
			adicionarErroDeValidacao(subView.getDtDataInclusao(), "Não pode ficar em branco");
			valido = false;
		}
		if (!Validator.validateString(subView.getTxtNiveis().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNiveis(), "Não pode ficar em branco");
			valido = false;
		}
		if (!Validator.validateString(subView.getTxtMascara().getValue())) {
			adicionarErroDeValidacao(subView.getTxtMascara(), "Não pode ficar em branco");
			valido = false;
		}
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "planocentroresultadoForm";
	}

	@Override
	public PlanoCentroResultado getModelBean() {
		return currentBean;
	}
}