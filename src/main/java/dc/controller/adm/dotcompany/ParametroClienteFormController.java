package dc.controller.adm.dotcompany;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.servicos.dao.adm.dotcompany.ParametroClienteDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.servicos.util.Validator;
import dc.visao.adm.dotcompany.ParametroClienteFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParametroClienteFormController extends CRUDFormController<ParametroCliente> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ParametroCliente currentBean;
	
	ParametroClienteFormView subView;
	
	@Autowired
	private ParametroClienteDAO parametroClienteDAO;
	
	@Autowired
	private ParcelaPagarDAO parcelaPagarDAO;

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {

		boolean valido = true;

		if (!Validator.validateObject(subView.getTxtValorEntrada().getValue())) {
			adicionarErroDeValidacao(subView.getTxtValorEntrada(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtDataEntrada().getValue())) {
			adicionarErroDeValidacao(subView.getDtDataEntrada(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtVencimentoPromocao().getValue())) {
			adicionarErroDeValidacao(subView.getDtVencimentoPromocao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtValorMensPromocional().getValue())) {
			adicionarErroDeValidacao(subView.getTxtValorMensPromocional(), "Não pode ficar em branco");
			valido = false;
		/*} else if (verificaSeFoiParcelado() && !Validator.validateNumber(subView.getTxIntervaloParcela().getValue())) {
			adicionarErroDeValidacao(subView.getTxIntervaloParcela(), "Não pode ficar em branco");
			valido = false;
		}*/

		}
		return valido;
	}

	/*private boolean verificaSeFoiParcelado() {
		return ((Integer) subView.getTxtValorMensPromocional().getConvertedValue()) > 1
				&& TipoVencimento.DIARIO.equals(subView.getCmbTipoFatura().getValue());
	}

	private BigDecimal getTotalParcelaPagar(List<ParcelaPagar> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValor());
		}
		return total;
	}

	private BigDecimal getTotalNaturezaFinanceira(List<LctoPagarNtFinanceira> naturezasFinanceiras) {
		BigDecimal total = BigDecimal.ZERO;
		if (naturezasFinanceiras != null) {
			for (int i = 0; i < naturezasFinanceiras.size(); i++) {
				total = total.add(naturezasFinanceiras.get(i).getValor());
			}
		}
		return total;
	}*/

	@Override
	protected void criarNovoBean() {
		currentBean = new ParametroCliente(); 
		
	} 

	@Override
	protected void initSubView() {
		subView = new ParametroClienteFormView(this);
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = parametroClienteDAO.find(id);
		
		/*subView.getBtnGerarParcelas().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			/*private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					gerarParcelas();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					mensagemErro(e.getMessage());
				}

			}
		});*/
		
				subView.getTxtValorEntrada().setConvertedValue(subView.getTxtValorEntrada().getConvertedValue());
				subView.getTxtValorMensPromocional().setConvertedValue(subView.getTxtValorMensPromocional().getConvertedValue());
				subView.getTxtValorMensPromocional().setConvertedValue(subView.getTxtValorMensPromocional().getConvertedValue());

		
	}

	@Override
	protected void actionSalvar() {
		try {
		        parametroClienteDAO.saveOrUpdate(currentBean);
		        notifiyFrameworkSaveOK(this.currentBean);	
	}catch (Exception e){
		mensagemErro(e.getMessage());
		e.printStackTrace();
	}
		
	}
	
	/*public void gerarParcelas() throws Exception {

		if (validaCampos()) {
			final ContaCaixa contaCaixa = (ContaCaixa) subView.getCmbTipoFatura().getValue();
			final List<ParcelaPagar> parcelasPagar = new ArrayList<ParcelaPagar>();
			List<ParcelaPagar> dados = subView.getParcelasSubForm().getDados();
			if (dados != null) {
				parcelasPagar.addAll(subView.getParcelasSubForm().getDados());
			}

			if (parcelasPagar != null && !parcelasPagar.isEmpty()) {
				ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {

							/**
							 * 
							 */
						/*	private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasPagar);
									geraParcelas(contaCaixa, parcelasPagar);
								}
							}
						});
			} else {
				geraParcelas(contaCaixa, parcelasPagar);
			}

		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}

	}*/
	
	/*private void setIntervaloParcelaByTipoVencimento() {
		if (TipoVencimento.MENSAL.equals(subView.getCmbTipoFatura().getValue())) {
		}
	}
	
	private void geraParcelas(ContaCaixa contaCaixa, final List<ParcelaPagar> parcelasPagar) {
		subView.getParcelasSubForm().removeAllItems();

		subView.preencheBean(currentBean);

		setIntervaloParcelaByTipoVencimento();

		ParametroCliente parametroCliente = currentBean;
		ParcelaPagar parcelaPagar;
		Date dataEmissao = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(parametroCliente.getDiaVencimento());
		BigDecimal valorParcela = parametroCliente.getValorEntrada().divide(BigDecimal.valueOf(parametroCliente.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		for (int i = 0; i < parametroCliente.getQuantidadeParcela(); i++) {
			parcelaPagar = new ParcelaPagar();
			parcelaPagar.setContaCaixa(contaCaixa);
			parcelaPagar.setNumeroParcela(i + 1);
			parcelaPagar.setDataEmissao(dataEmissao);
			if (i > 0) {
			}
			parcelaPagar.setDataVencimento(primeiroVencimento.getTime());
			parcelaPagar.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);
			if (i == (parametroCliente.getQuantidadeParcela() - 1)) {
				residuo = parametroCliente.getValorEntrada().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaPagar.setValor(valorParcela);
			}

			parcelasPagar.add(parcelaPagar);
			//novoParcelaPagar(parcelaPagar);
		}

		subView.getParcelasSubForm().fillWith(parcelasPagar);
	}

	private void excluiParcelas(List<ParcelaPagar> parcelasPagar) {
		List<ParcelaPagar> persistentObjects = subView.getParcelasSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			parcelaPagarDAO.delete(persistentObjects.get(i));
		}
		parcelasPagar.clear();
	}

	/*public ParcelaPagar novoParcelaPagar() {
		ParcelaPagar parcela = new ParcelaPagar();
		return novoParcelaPagar(parcela);
	}

	public ParcelaPagar novoParcelaPagar(ParcelaPagar parcela) {

		currentBean.addParcelaPagar(parcela);

		return parcela;
	}

	public void removerParcelaPagar(List<ParcelaPagar> values) {
		for (ParcelaPagar value : values) {
			currentBean.removeParcelaPagar(value);
		}

	}*/

	/*public LctoPagarNtFinanceira novoLctoPagarNtFinanceira() {
		LctoPagarNtFinanceira lctoPagarNtFinanceira = currentBean.addLctoPagarNtFinanceira();
		return lctoPagarNtFinanceira;
	}

	public void removerLctoPagarNtFinanceira(List<LctoPagarNtFinanceira> values) {
		for (LctoPagarNtFinanceira value : values) {
			currentBean.removeLctoPagarNtFinanceira(value);
		}

	}*/

	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Parâmetro Cliente";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		parametroClienteDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
		
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		parametroClienteDAO.deleteAll(objetos);
		 mensagemRemovidoOK();
	}
	
	@Override
	public String getViewIdentifier() {
		return "parametroClienteForm";
	}
	
	@Override
	public boolean isFullSized() {
		return true;
	}
	
}
