package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.FluxoCaixaPeriodoEntity;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FluxoCaixaPeriodoFormController extends CRUDFormController<FluxoCaixaPeriodoEntity> {
	
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			private FluxoCaixaPeriodoEntity currentBean;
			
			@Override
			protected String getNome() {
				return "Fluxo Caixa de Período";
			}

			@Override
			protected Component getSubView() {
				return null;
			}

			@Override
			protected void actionSalvar() {

				//subView.preencheBean(currentBean);

			}

			@Override
			protected void carregar(Serializable id) {

				try {
				
					//currentBean = this.business.find((Integer) id);
					
					//fieldGroup.setItemDataSource(this.currentBean);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void initSubView() {
				
				try {
					//subView = new FluxoCaixaFormView(this);
					
					
					//this.fieldGroup = new DCFieldGroup<>(FluxoCaixaPeriodoEntity.class);
					
					// Mapeia os campos
					//fieldGroup.bind(this.subView.getTxNomeFluxo(),"nome");
					/*fieldGroup.bind(this.subView.getTxQuantidadeParcela(),"quantidadeParcela");
					fieldGroup.bind(this.subView.getCbPagamentoCompartilhado(),"pagamentoCompartilhado");
					fieldGroup.bind(this.subView.getDtPrimeiroVencimento(),"primeiroVencimento");
					
					fieldGroup.bind(this.subView.getCbDocumentoOrigem(),"documentoOrigem");
					//fieldGroup.bind(this.subView.getCbPessoa(),"pessoa");
					fieldGroup.bind(this.subView.getCbFornecedor(),"fornecedor");*/
					
					
					/*this.subView.getCbPeriodo().configuraCombo(
							"nome", FluxoCaixaPeriodoListController.class, this.fluxoCaixaPeriodoDAO, this.getMainController());*/
					
				}catch (Exception e) {
				    e.printStackTrace();
				}
				
			}

			@Override
			protected void criarNovoBean() {
				
				
				try {
					currentBean = new FluxoCaixaPeriodoEntity();
					//fieldGroup.setItemDataSource(this.currentBean);
				}catch (Exception e) {
					e.printStackTrace();
					mensagemErro(e.getMessage());
			    }
			}

			@Override
			protected void remover(List<Serializable> ids) {
				try {
					//this.business.deleteAll(ids);

					mensagemRemovidoOK();
				} catch (Exception e) {
					e.printStackTrace();

					mensagemErro(e.getMessage());
				}
			}

			/* Implementar validacao de campos antes de salvar. */
			@Override
			protected boolean validaSalvar() {
				/*try {

					 //fieldGroup.commit();
					return true;
				} catch (FieldGroup.CommitException ce) {*/
					return false;
				//}
			}

			@Override
			protected void removerEmCascata(List<Serializable> ids) {
				mensagemRemovidoOK();
			}

			@Override
			public String getViewIdentifier() {
				return ClassUtils.getUrl(this);
			}

			@Override
			protected boolean isFullSized() {

				return true;
			}

			@Override
			public FluxoCaixaPeriodoEntity getModelBean() {
				return currentBean;
			}

}
