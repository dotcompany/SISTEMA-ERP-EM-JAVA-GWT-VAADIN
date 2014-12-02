package dc.controller.geral.tabela;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.SalarioMinimo;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.geral.tabela.SalarioMinimoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.SalarioMinimoFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class SalarioMinimoFormController extends
		CRUDFormController<SalarioMinimo> {

	private SalarioMinimoFormView subView;

	@Autowired
	private SalarioMinimoDAO salarioMinimoDAO;

	private SalarioMinimo currentBean;

	@Override
	protected String getNome() {
		return "Salário Mínimo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		String msgErro = "Erro ao realizar operação";
		try {

			Date vigencia = subView.getDataVigencia().getValue();
			String valorMensal = subView.getTxtValorMensal().getValue();
			String valorDiario = subView.getTxtValorDiario().getValue();
			String valorHora = subView.getTxtValorHora().getValue();
			String normaLegal = subView.getTxtNormaLegal().getValue();
			Date dou = subView.getDtDou().getValue();

			if (vigencia == null) {
				msgErro = "Informe a Data de Vigência";
				throw new ErroValidacaoException(msgErro);
			}

			if (valorMensal == null || valorMensal.isEmpty()) {
				msgErro = "Informe o Valor Mensal";
				throw new ErroValidacaoException(msgErro);
			}

			if (valorDiario == null || valorDiario.isEmpty()) {
				msgErro = "Informe o Valor Diário";
				throw new ErroValidacaoException(msgErro);
			}

			if (valorHora == null || valorHora.isEmpty()) {
				msgErro = "Informe o Valor Hora";
				throw new ErroValidacaoException(msgErro);
			}

			if (normaLegal == null) {
				msgErro = "Informe a Norma Legal";
				throw new ErroValidacaoException(msgErro);
			}

			if (dou == null) {
				msgErro = "Informe a Data DOU";
				throw new ErroValidacaoException(msgErro);
			}

			valorMensal = formataValor(valorMensal);
			valorDiario = formataValor(valorDiario);
			valorHora = formataValor(valorHora);

			currentBean.setVigencia(vigencia);
			currentBean.setValorMensal(new BigDecimal(valorMensal));
			currentBean.setValorDiario(new BigDecimal(valorDiario));
			currentBean.setValorHora(new BigDecimal(valorHora));
			currentBean.setNormaLegal(normaLegal);
			currentBean.setDou(dou);

			salarioMinimoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);

		} catch (ErroValidacaoException e) {
			mensagemErro(msgErro);
		} catch (Exception e) {
			mensagemErro(msgErro);
			e.printStackTrace();
		}

	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = salarioMinimoDAO.find(id);

		subView.getDataVigencia().setValue(currentBean.getVigencia());
		subView.getTxtValorMensal().setValue(
				currentBean.getValorMensal().toString());
		subView.getTxtValorDiario().setValue(
				currentBean.getValorDiario().toString());
		subView.getTxtValorHora().setValue(
				currentBean.getValorHora().toString());
		subView.getTxtNormaLegal().setValue(currentBean.getNormaLegal());
		subView.getDtDou().setValue(currentBean.getDou());
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
		subView = new SalarioMinimoFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new SalarioMinimo();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		salarioMinimoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
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
		return "salarioMinimoForm";
	}

	@Override
	public SalarioMinimo getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}