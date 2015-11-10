package dc.controller.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.comercial.ParcelaCondicaoPagamento;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.ICondicaoPagamentoDAO;
import dc.servicos.dao.comercial.IParcelaCondicaoPagamentoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.CondicaoPagamentoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class CondicaoPagamentoFormController extends CRUDFormController<CondicaoPagamento> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CondicaoPagamento currentBean;

	private CondicaoPagamentoFormView subView;

	@Autowired
	private ICondicaoPagamentoDAO dao;

	@Autowired
	private IParcelaCondicaoPagamentoDAO parcelaDAO;

	@Override
	public String getViewIdentifier() {
		return "tipoNotaForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new CondicaoPagamento();

	}

	@Override
	protected void initSubView() {
		subView = new CondicaoPagamentoFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);
		BigDecimal faturamentoMinimo = currentBean.getFaturamentoMinimo();
		BigDecimal faturamentoMaximo = currentBean.getFaturamentoMaximo();
		BigDecimal indiceCorrecao = currentBean.getIndiceCorrecao();
		Integer diasTolerancia = currentBean.getDiasTolerancia();
		BigDecimal valorTolerancia = currentBean.getValorTolerancia();
		Integer prazoMedio = currentBean.getPrazoMedio();

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());

		if (faturamentoMinimo != null) {
			subView.getTxtFaturamentoMinimo().setValue(faturamentoMinimo.toString());
		}

		if (faturamentoMaximo != null) {
			subView.getTxtFaturamentoMaximo().setValue(faturamentoMaximo.toString());
		}

		if (indiceCorrecao != null) {
			subView.getTxtIndiceCorrecao().setValue(indiceCorrecao.toString());
		}

		if (diasTolerancia != null) {
			subView.getTxtDiasTolerancia().setValue(diasTolerancia.toString());
		}

		if (valorTolerancia != null) {
			subView.getTxtValorTolerancia().setValue(valorTolerancia.toString());
		}

		if (prazoMedio != null) {
			subView.getTxtPrazoMedio().setValue(prazoMedio.toString());
		}

		List<ParcelaCondicaoPagamento> parcelas = currentBean.getParcelas();
		if (parcelas != null) {
			subView.preencherSubForm(parcelas);
		}

	}

	@Override
	protected void actionSalvar() {

		String nome = subView.getTxtNome().getValue();
		String descricao = subView.getTxtDescricao().getValue();
		String faturamentoMinimo = subView.getTxtFaturamentoMinimo().getValue();
		String faturamentoMaximo = subView.getTxtFaturamentoMaximo().getValue();
		String indiceCorrecao = subView.getTxtIndiceCorrecao().getValue();
		String diasTolerancia = subView.getTxtDiasTolerancia().getValue();
		String valorTolerancia = subView.getTxtValorTolerancia().getValue();
		String prazoMedio = subView.getTxtPrazoMedio().getValue();

		try {

			if (!Validator.validateString(nome)) {
				throw new ErroValidacaoException("Informe o Nome!");
			}

			currentBean.setNome(nome);
			currentBean.setDescricao(descricao);

			if (Validator.validateString(faturamentoMinimo)) {
				currentBean.setFaturamentoMinimo(new BigDecimal(formataValor(faturamentoMinimo)));
			}

			if (Validator.validateString(faturamentoMaximo)) {
				currentBean.setFaturamentoMaximo(new BigDecimal(formataValor(faturamentoMaximo)));
			}

			if (Validator.validateString(indiceCorrecao)) {
				indiceCorrecao = indiceCorrecao.replace(",", ".");
				currentBean.setIndiceCorrecao(new BigDecimal(indiceCorrecao));
			}

			if (Validator.validateString(diasTolerancia)) {
				currentBean.setDiasTolerancia(new Integer(diasTolerancia));
			}

			if (Validator.validateString(valorTolerancia)) {
				currentBean.setValorTolerancia(new BigDecimal(formataValor(valorTolerancia)));
			}

			if (Validator.validateString(prazoMedio)) {
				currentBean.setPrazoMedio(new Integer(prazoMedio));
			}

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Condição de Pagamento";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		try {
			for (Serializable id : ids) {
				CondicaoPagamento condicao = dao.find(id);
				for (ParcelaCondicaoPagamento parcela : condicao.getParcelas()) {
					parcelaDAO.delete(parcela);
				}
			}

			dao.deleteAllByIds(ids);
			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro("Problema ao remover");
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	public ParcelaCondicaoPagamento adicionarParcela() {
		ParcelaCondicaoPagamento parcela = new ParcelaCondicaoPagamento();
		currentBean.adicionarParcela(parcela);
		return parcela;
	}

	@Override
	public CondicaoPagamento getModelBean() {
		return currentBean;
	}

}
