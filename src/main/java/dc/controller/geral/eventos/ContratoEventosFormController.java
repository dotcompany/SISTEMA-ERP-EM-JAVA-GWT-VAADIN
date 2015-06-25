package dc.controller.geral.eventos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.TipoSemestre;
import dc.control.util.ClassUtils;
import dc.control.util.eventos.ContratoEventosUtil;
import dc.control.validator.DotErpException;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.model.business.eventos.ContratoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.ContratoEventosFormView;

@Controller
@Scope("prototype")
public class ContratoEventosFormController extends CRUDFormController<ContratoEventosEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private ContratoEventosFormView subView;
	
	@Autowired
	private ContratoBusiness<ContratoEventosEntity> business;
	
	/**
	 * ENTITY
	 */

	private ContratoEventosEntity entity;
	
	public ContratoEventosFormController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}
	
	@Override
	protected String getNome() {
		return "Contrato Eventos";
	}
	
	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected boolean validaSalvar() {
		
		try {
			ContratoEventosUtil.validateRequiredFields(this.subView);
			//PessoaUtils.validateFieldValue(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}

	}

	@Override
	protected void criarNovoBean() {
		
		try {
			this.entity = new ContratoEventosEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

		
	}

	@Override
	protected void initSubView() {
		
		/*try {
			this.subView = new ContratoEventosFormView(this);
			
			DefaultManyToOneComboModel<CerimonialEntity> modelCerimonial = new DefaultManyToOneComboModel<CerimonialEntity>(
					CerimonialListController.class, super.getMainController(),
					null, this.cerimonialBusiness);

			this.subView.getMocNomeCerimonial().setModel(modelCerimonial);*/


		
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void actionSalvar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void quandoNovo() {
		
		try {
			this.entity = new ContratoEventosEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

		
	}

	@Override
	protected Component getSubView() {
		// TODO Auto-generated method stub
		return subView;
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

		
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

		
	}

	@Override
	public ContratoEventosEntity getModelBean() {
		// TODO Auto-generated method stub
		return entity;
	}
	
	public void carregarTipoSemestre() {
		for (TipoSemestre s : TipoSemestre.values()) {
			this.subView.getCbTipoSemestre().addItem(s);
		}
	}

	

}
