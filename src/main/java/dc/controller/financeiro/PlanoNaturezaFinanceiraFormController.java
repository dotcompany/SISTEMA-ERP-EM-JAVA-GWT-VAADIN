package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.PlanoNaturezaFinanceira;
import dc.entidade.framework.EmpresaEntity;
import dc.entidade.geral.Usuario;
import dc.servicos.dao.financeiro.PlanoNaturezaFinanceiraDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.PlanoNaturezaFinanceiraFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class PlanoNaturezaFinanceiraFormController extends CRUDFormController<PlanoNaturezaFinanceira> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoNaturezaFinanceiraFormView subView;

	@Autowired
	private PlanoNaturezaFinanceiraDAO planoDAO;

	private PlanoNaturezaFinanceira currentBean;

	@Override
	protected String getNome() {
		return "Plano Natureza Financeira";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setMascara(subView.getTxtMascara().getValue());
		currentBean.setNiveis((BigDecimal) subView.getTxtNiveis().getConvertedValue());
		currentBean.setDataInclusao(subView.getDtInclusao().getValue());
		Usuario usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
		try {
			planoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = planoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtMascara().setValue(currentBean.getMascara());
		subView.getDtInclusao().setValue(currentBean.getDataInclusao());
		subView.getTxtNiveis().setValue(currentBean.getNiveis().toString());
	}

	@Override
	protected void initSubView() {
		subView = new PlanoNaturezaFinanceiraFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PlanoNaturezaFinanceira();
	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtInclusao().getValue())) {
			adicionarErroDeValidacao(subView.getDtInclusao(), "Não pode ficar em branco");
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
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "planoNaturezaFinanceiraForm";
	}

	@Override
	public PlanoNaturezaFinanceira getModelBean() {
		return currentBean;
	}

}