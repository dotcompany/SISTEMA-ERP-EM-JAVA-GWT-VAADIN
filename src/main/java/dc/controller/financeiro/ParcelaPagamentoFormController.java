package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.enums.TipoBaixaEn;
import dc.control.util.ClassUtils;
import dc.entidade.financeiro.Cheque;
import dc.entidade.financeiro.ChequeEmitido;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.entidade.financeiro.ParcelaPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.financeiro.ChequeEmitidoDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.ParcelaPagamentoDAO;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
import dc.visao.financeiro.ParcelaPagamentoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParcelaPagamentoFormController extends CRUDFormController<ParcelaPagar>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParcelaPagamentoFormView subView;
	
	@Autowired
    private ParcelaPagarDAO parcelaPagarDAO;

	@Autowired
	private ParcelaPagamentoDAO parcelaPagamentoDAO;
	@Autowired
	private StatusParcelaDAO statusParcelaDAO;
	@Autowired
	private TipoPagamentoDAO tipoPagamentoDAO;
	@Autowired
	private ContaCaixaDAO contaCaixaDAO;
	@Autowired
	private ChequeEmitidoDAO chequeEmitidoDAO;

	private ParcelaPagar currentBean;
	private ParcelaPagamento parcela;


@Override
protected boolean validaSalvar() {
	try {
       // Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
        fieldGroup.commit();
        return true;
    } catch (FieldGroup.CommitException ce) {
        return false;
    }
}

@Override
protected void criarNovoBean() {
		
	try {
        this.currentBean = new ParcelaPagar();

        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.currentBean);

    } catch (Exception e) {
        e.printStackTrace();
        mensagemErro(e.getMessage());
    }
}

	@Override
	protected void initSubView() {
		
		try {
			subView = new ParcelaPagamentoFormView(this);
			
			this.fieldGroup = new DCFieldGroup<>(ParcelaPagar.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getDtDataPagamento(),"dataEmissao");
			//fieldGroup.bind(this.subView.getTxTaxaJuro(),"taxaJuro");
			fieldGroup.bind(this.subView.getCbContaCaixa(),"contaCaixa");
			//fieldGroup.bind(this.subView.getCbTipoPagamento(),"tipoPagamento");
			
			// Configura os ManyToOneComboFields
			this.subView.getCbContaCaixa().configuraCombo(
			         "nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			
			this.subView.getCbTipoPagamento().configuraCombo(
			         "descricao", TipoPagamentoListController.class, this.tipoPagamentoDAO, this.getMainController());
			
			
			subView.getBtnEfetuaPagamento().addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						efetuaPagamento();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			subView.getBtnExcluiPagamento().addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					excluiPagamento();
				}
			});

			subView.getCbTipoBaixa().addBlurListener(new BlurListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void blur(BlurEvent event) {

					TipoBaixaEn tipoBaixa = (TipoBaixaEn) subView.getCbTipoBaixa().getValue();

					switch (tipoBaixa) {
					
					   case P: {
						      subView.getTxValorPago().setEnabled(true);
						      break;
					   }
					   
					   case T: {
						      subView.getTxValorPago().setEnabled(false);
						      new CalculaTotalPagoBlurListener();

						      break;
						      
					   }				

					   default: {
						      break;
					   }
					
					}
					

				}
			});
			
			subView.getTxTaxaJuro().addBlurListener(new BlurListener() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void blur(BlurEvent event) {
					subView.preencheBean(currentBean);
					BigDecimal valorMulta = BigDecimal.ZERO;
					if (currentBean.getTaxaMulta() != null) {
						currentBean.setValorMulta(parcela.getParcelaPagar().getValor().multiply(currentBean.getTaxaMulta()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
						valorMulta = currentBean.getValorMulta();
					} else {
						currentBean.setValorMulta(valorMulta);
					}
				
				}
			});
			
            subView.getTxTaxaDesconto().addBlurListener(new BlurListener() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void blur(BlurEvent event) {
					subView.preencheBean(currentBean);
					BigDecimal valorDesconto = BigDecimal.ZERO;
					if (currentBean.getTaxaDesconto() != null) {
						currentBean.setValorDesconto((parcela.getParcelaPagar().getValor()).multiply(currentBean.getTaxaDesconto())
								.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
						valorDesconto = currentBean.getValorDesconto();
					} else {
						currentBean.setValorDesconto(valorDesconto);
					}
				
				}
			});
            
            subView.getTxTaxaJuro().addBlurListener(new BlurListener() {
            	
            	/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				@Override
				public void blur(BlurEvent event) {
					subView.preencheBean(currentBean);
					BigDecimal valorJuro = BigDecimal.ZERO;
					
					if (currentBean.getTaxaJuro() != null && currentBean.getDataEmissao() != null) {
						
						Calendar dataPagamento = Calendar.getInstance();
						dataPagamento.setTime(currentBean.getDataEmissao());
						
						Calendar dataVencimento = Calendar.getInstance();
						dataVencimento.setTime(parcela.getParcelaPagar().getDataVencimento());
						
						if (dataVencimento.before(dataPagamento)) {
							long diasAtraso = (dataPagamento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;

							currentBean.setValorJuro(calculaJuros(parcela, diasAtraso));
							valorJuro = currentBean.getValorJuro();
						}
					}
					currentBean.setValorJuro(valorJuro);
					
				}
			});

			//subView.getTxTaxaJuro().addBlurListener(new CalculaTotalPagoBlurListener());
			//subView.getTxTaxaMulta().addBlurListener(new CalculaTotalPagoBlurListener());
			//subView.getTxTaxaDesconto().addBlurListener(new CalculaTotalPagoBlurListener());
			//subView.getDtDataPagamento().addBlurListener(new CalculaTotalPagoBlurListener());

			//subView.getTxValorJuro().setEnabled(false);
			//subView.getTxValorMulta().setEnabled(false);
			//subView.getTxValorDesconto().setEnabled(false);
			//subView.getTxValorPago().setEnabled(false);
			//subView.getDtDataVencimento().setEnabled(false);
			//subView.getTxValorPagar().setEnabled(false);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			currentBean = parcelaPagarDAO.find(id);
			parcela = parcelaPagamentoDAO.find(id);
			subView.fillParcelaPagamentosSubForm(parcelaPagamentoDAO.buscaPorParcelaPagar(currentBean));
			fieldGroup.setItemDataSource(this.currentBean);
		}catch (Exception e) {
			        e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {
		try {
			parcelaPagamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Pagamento Parcela";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		try {
			//this.business.deleteAll(ids);
			this.parcelaPagamentoDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/*public void calculaTotalPago() {
		subView.preencheBean(currentBean);
		
		ParcelaPagamento pagamento = currentBean;
		BigDecimal valorJuro = BigDecimal.ZERO;
		BigDecimal valorMulta = BigDecimal.ZERO;
		BigDecimal valorDesconto = BigDecimal.ZERO;
		
		if (pagamento.getTaxaJuro() != null && pagamento.getDataPagamento() != null) {
			
			Calendar dataPagamento = Calendar.getInstance();
			dataPagamento.setTime(pagamento.getDataPagamento());
			
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(pagamento.getParcelaPagar().getDataVencimento());
			
			if (dataVencimento.before(dataPagamento)) {
				long diasAtraso = (dataPagamento.getTimeInMillis() - dataVencimento.getTimeInMillis()) / 86400000l;

				pagamento.setValorJuro(calculaJuros(pagamento, diasAtraso));
				valorJuro = pagamento.getValorJuro();
			}
		}
		pagamento.setValorJuro(valorJuro);

		if (pagamento.getTaxaMulta() != null) {
			pagamento.setValorMulta((pagamento.getParcelaPagar().getValor()).multiply(pagamento.getTaxaMulta()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			valorMulta = pagamento.getValorMulta();
		} else {
			pagamento.setValorMulta(valorMulta);
		}

		if (pagamento.getTaxaDesconto() != null) {
			pagamento.setValorDesconto((pagamento.getParcelaPagar().getValor()).multiply(pagamento.getTaxaDesconto())
					.divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
			valorDesconto = pagamento.getValorDesconto();
		} else {
			pagamento.setValorDesconto(valorDesconto);
		}

		pagamento.setValorPago((pagamento.getValorPago().add(valorJuro)).add(pagamento.getValorPago().add(valorMulta)).subtract(valorDesconto));
		//pagamento.setValorPago(pagamento.getValorPago().add(valorJuro).add(valorMulta).subtract(valorDesconto));


		subView.preencheForm(currentBean);
	}*/

	private BigDecimal calculaJuros(ParcelaPagamento pagamento, long diasAtraso) {
		// valorJuro = valor * ((taxaJuro / 30) / 100) * diasAtraso
		BigDecimal juros = pagamento.getParcelaPagar().getValor().multiply(pagamento.getTaxaJuro().setScale(5).divide(BigDecimal.valueOf(30.0), 5))
				.divide(BigDecimal.valueOf(100), 5).multiply(BigDecimal.valueOf(diasAtraso));

		return juros.setScale(2, RoundingMode.HALF_DOWN);
	}

	public void efetuaPagamento() throws Exception {

		if (validaSalvar()) {
			new CalculaTotalPagoBlurListener();
			ParcelaPagamento pagamento = parcela;
			ChequeEmitido chequeEmitido = pagamento.getChequeEmitido();
			Cheque cheque = pagamento.getChequeEmitido().getCheque();

			pagamento.setChequeEmitido(null);
			if (pagamento.getTipoPagamento().getTipo().equals("02")) {
				
				if (pagamento.getChequeEmitido() != null) {
                    //-------
                    if (pagamento.getChequeEmitido().getCheque() != null) {
                        if (pagamento.getChequeEmitido().getCheque().getNumero() != null) {
                            if (pagamento.getChequeEmitido().getValor() != null) {
                                if (pagamento.getChequeEmitido().getValor().compareTo(pagamento.getValorPago()) != 0) {
                                    throw new Exception("Valor cheque diferente do valor total!");
                                }
                                chequeEmitido.setCheque(cheque);
                                chequeEmitidoDAO.save(chequeEmitido);

                                pagamento.setChequeEmitido(chequeEmitido);
                            } else {
                                //valor do cheque nao informado
                                throw new Exception("Informe o valor do cheque!");
                            }
                            
                        } else {
                            //numero do cheque nao informado
                            throw new Exception("Número do cheque inválido!");
                        }
                    }
                }
            } else {
                //tipo pagamento nÃ£o Ã© cheque
            	pagamento.setChequeEmitido(null);
            }

				// FinSelecionaChequeGrid chequeGrid = new
				// FinSelecionaChequeGrid(MDIFrame.getInstance(), true, true);
				// chequeGrid.setVisible(true);
				// if (!chequeGrid.cancelado) {
				// pagamento.setFinChequeEmitido(chequeGrid.getChequeEmitido());
				// } else {
				// JOptionPane.showMessageDialog(finParcelaPagamentoDetalhe,
				// "É necessário informar um cheque para este tipo de pagamento.",
				// "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
				// return;
				// }
			}

			try {
				salvaPagamento();
			} catch (Exception e) {
				mensagemErro(e.getMessage());
				e.printStackTrace();
			}
		

	}

	@Transactional
	private void salvaPagamento() throws Exception {
		ParcelaPagamento parcelaPagamento = parcela;
		String tipoBaixa = ((TipoBaixaEn) subView.getCbTipoBaixa().getValue()).getKey();
		if (parcelaPagamento.getChequeEmitido() != null) {
			// session.save(parcelaPagamento.getFinChequeEmitido());
			// ChequeVO cheque =
			// parcelaPagamento.getFinChequeEmitido().getCheque();
			// cheque.setStatusCheque("U");
			// session.update(cheque);
		}

		parcelaPagamentoDAO.save(parcelaPagamento);

		StatusParcela statusParcela = null;
		if ("T".equalsIgnoreCase(tipoBaixa)) {
			statusParcela = statusParcelaDAO.findBySituacao("Quitado");
		} else {
			statusParcela = statusParcelaDAO.findBySituacao("Quitado Parcial");
		}

		if (statusParcela == null) {
			throw new Exception("Status de parcela não cadastrado. Entre em contato com a Software House");
		}

		ParcelaPagar parcelaPagar = parcelaPagamento.getParcelaPagar();
		parcelaPagar.setStatusParcela(statusParcela);
		parcelaPagamentoDAO.saveOrUpdate(parcelaPagar);

		List<ParcelaPagamento> dados = subView.getPagamentosSubForm().getDados();
		dados.add(parcelaPagamento);
		subView.getPagamentosSubForm().removeAllItems();
		subView.fillParcelaPagamentosSubForm(dados);
	}

	public void excluiPagamento() {
		Collection<ParcelaPagamento> selectedItens = subView.getPagamentosSubForm().getSelectedItens();

		Collection<ParcelaPagamento> deletedItens = new ArrayList<ParcelaPagamento>();

		for (ParcelaPagamento parcelaPagamento : selectedItens) {
			StatusParcela statusParcela = statusParcelaDAO.findBySituacao("Em Aberto");

			if (statusParcela == null) {
				mensagemErro("Status de parcela não cadastrado. Entre em contato com a Software House");
			} else {
				ParcelaPagar parcelaPagar = parcelaPagamento.getParcelaPagar();
				parcelaPagar.setStatusParcela(statusParcela);

				parcelaPagamentoDAO.saveOrUpdate(parcelaPagar);
				parcelaPagamentoDAO.delete(parcelaPagamento);
				deletedItens.add(parcelaPagamento);
			}
		}

		if (!deletedItens.isEmpty()) {
			mensagemAtencao("Pagamento excluído com sucesso!");
			subView.getPagamentosSubForm().removeItens(deletedItens);
		}
	}

	private class CalculaTotalPagoBlurListener implements BlurListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void blur(BlurEvent event) {
			subView.preencheBean(currentBean);
			
			//ParcelaPagamento parcelasPagamento = parcela;
			//ParcelaPagar pagamento = currentBean;
			//ParcelaPagar parcelasPagar = parcelaPagar;
		    BigDecimal valorJuro = BigDecimal.ZERO;
			BigDecimal valorMulta = BigDecimal.ZERO;
			BigDecimal valorDesconto = BigDecimal.ZERO;
			
			//pagamento.setValorPago((pagamento.getValorPago().add(valorJuro)).add(valorMulta).subtract(valorDesconto));
			parcela.setValorPago(parcela.getValorPago().add(valorJuro).add(valorMulta).subtract(valorDesconto));
			//parcela.setValorPago(valorJuro.add(pagamento.getValorJuro()).add(valorMulta.add(pagamento.getValorMulta())).subtract(pagamento.getValorDesconto()));

			subView.preencheForm(currentBean);
		}
	}

	@Override
	public ParcelaPagar getModelBean() {
		return currentBean;
	}
}