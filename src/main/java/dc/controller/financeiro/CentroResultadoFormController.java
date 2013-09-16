package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.CentroResultado;
import dc.servicos.dao.financeiro.CentroResultadoDAO;
import dc.servicos.dao.financeiro.PlanoCentroResultadoDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.CentroResultadoFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe ela pega a classe principal que é o CRUD, que tem todos os controllers
 * da Tela, onde quando extendemos herdamos os métodos que temos na tela principal.
 * Temos o botão Novo que é para Criar uma nova Tela, para adicionar informações
 * novas, e dentro temos o Button Salvar que é para salvar as informações no CentroResultado de Dados
 * Temos o carregar também que é para pegar as informações que desejarmos quando
 * formos pesquisar na Tela.
 *
*/

@Controller
@Scope("prototype")
public class CentroResultadoFormController extends CRUDFormController<CentroResultado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CentroResultadoDAO centroresultadoDAO;

	private CentroResultado currentBean;

	@Autowired
	private PlanoCentroResultadoDAO planoresultadoDAO;

	private CentroResultadoFormView subView;

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			centroresultadoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = centroresultadoDAO.find(id);

		subView.preencheForm(currentBean);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new CentroResultado();
	}

	@Override
	protected String getNome() {
		return "Centro Resultado";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "centroresultadoForm";
	}

	@Override
	protected void initSubView() {
		subView = new CentroResultadoFormView();
		subView.InitCbPlanoCentroResultado(planoresultadoDAO.listaTodos());

	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void remover(List<Serializable> ids) {
		centroresultadoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtClassficacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtClassficacao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtPercentualRateio().getValue())) {
			adicionarErroDeValidacao(subView.getTxtPercentualRateio(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbPlanoCentroResultado().getValue())) {
			adicionarErroDeValidacao(subView.getCbPlanoCentroResultado(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}
}
