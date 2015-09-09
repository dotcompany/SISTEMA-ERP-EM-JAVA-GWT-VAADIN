package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.SalarioMinimoEntity;
import dc.servicos.dao.geral.tabela.SalarioMinimoDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.SalarioMinimoFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class SalarioMinimoFormController extends
		CRUDFormController<SalarioMinimoEntity> {

	private SalarioMinimoFormView subView;

	@Autowired
	private SalarioMinimoDAO salarioMinimoDAO;

	private SalarioMinimoEntity currentBean;

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

		try {

			salarioMinimoDAO.saveOrUpdate(currentBean);
			
			//currentBean.setValorMensal(((BigDecimal) this.subView.getTxtValorMensal().getConvertedValue()).setScale(2, RoundingMode.HALF_EVEN));
			notifiyFrameworkSaveOK(this.currentBean);

		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		try {
			        this.currentBean = this.salarioMinimoDAO.find(id);
			        
			        //this.subView.getTxtValorMensal().setConvertedValue(currentBean.getValorMensal());
			
			        // Atribui a entidade carregada como origem de dados dos campos do formulario no FieldGroup
			        fieldGroup.setItemDataSource(this.currentBean);
			
			    } catch (Exception e) {
			        e.printStackTrace();
			    }

	}

	@Override
	protected void initSubView() {
		try {
			        this.subView = new SalarioMinimoFormView(this);
			
			        // Cria o DCFieldGroup
			        this.fieldGroup = new DCFieldGroup<>(SalarioMinimoEntity.class);
			
			        // Mapeia os campos
			        fieldGroup.bind(this.subView.getDataVigencia(),"vigencia");
			       // fieldGroup.bind(this.subView.getTxtValorMensal(),"valorMensal");
			        
			       /* subView.getTxtValorMensal().addBlurListener(new BlurListener() {

						/**
						 * 
						 */
						/*private static final long serialVersionUID = 1L;

						@Override
						public void blur(BlurEvent event) {
							subView.getTxtValorMensal().setConvertedValue(subView.getTxtValorMensal().getConvertedValue());

						}

					});*/
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			        this.currentBean = new SalarioMinimoEntity();
			
			        // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
			        fieldGroup.setItemDataSource(this.currentBean);
			
			    } catch (Exception e) {
			        e.printStackTrace();
			        mensagemErro(e.getMessage());
			    }
	}

	@Override
	protected void remover(List<Serializable> ids) {
		salarioMinimoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
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
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "salarioMinimoForm";
	}

	@Override
	public SalarioMinimoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}