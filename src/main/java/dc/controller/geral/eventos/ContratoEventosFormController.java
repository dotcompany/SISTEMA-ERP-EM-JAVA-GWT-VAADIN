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
import dc.controller.geral.pessoal.TipoColaboradorListController;
import dc.entidade.geral.eventos.CerimonialEventosEntity;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.servicos.dao.geral.eventos.CerimonialEventosDAO;
import dc.servicos.dao.geral.eventos.ContratoEventosDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.ContratoEventosFormView;

@Controller
@Scope("prototype")
public class ContratoEventosFormController extends CRUDFormController<ContratoEventosEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private ContratoEventosFormView subView;
	
	//@Autowired
	//private ContratoBusiness<ContratoEventosEntity> business;
	
	@Autowired
	private CerimonialEventosDAO cerimonialEventosDAO;
	
	@Autowired
	private ContratoEventosDAO contratoEventosDAO;
	
	/**
	 * ENTITY
	 */

	private ContratoEventosEntity entity;
	
	public ContratoEventosFormController() {
		// TODO Auto-generated constructor stub
	}
	
	/*public ContratoBusiness<ContratoEventosEntity> getBusiness() {
		return business;
	}*/

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
		
		try {
			this.subView = new ContratoEventosFormView(this);
			
			DefaultManyToOneComboModel<CerimonialEventosEntity> modelCerimonialEventos = new DefaultManyToOneComboModel<CerimonialEventosEntity>(
					TipoColaboradorListController.class,
					this.cerimonialEventosDAO, super.getMainController());

			this.subView.getMocNomeCerimonial().setModel(modelCerimonialEventos);
			
			carregarTipoSemestre();
			
			// Valores iniciais

			this.subView.getCbTipoSemestre().setValue(TipoSemestre.S);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			//this.entity = this.business.find(id);
			
			this.entity = contratoEventosDAO.find(id);
			
			this.subView.getCbTipoSemestre().setValue(this.entity.getTipoSemestre());
			
			this.subView.getTxtCurso().setValue(this.entity.getCurso());
			this.subView.getTxtUnidade().setValue(this.entity.getUnidade());
			this.subView.getTxtAnoFormatura().setValue(this.entity.getAnoFormatura());
			
			this.entity.setQuantidadeFormandos((Integer) this.subView.getTxtQuantidadeFormandos().getConvertedValue());
			this.entity.setDataContrato(this.subView.getPdfDataContrato().getValue());
			this.entity.setDataPrimeiroEvento(this.subView.getPdfDataPrimeiroEvento().getValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void actionSalvar() {
		
		try {
			//TipoSemestre tipoSemestre = (TipoSemestre) this.subView.getCbTipoSemestre().getValue();
			
			TipoSemestre tipoSemestre = (TipoSemestre) this.subView	.getCbTipoSemestre().getValue();
			this.entity.setTipoSemestre(tipoSemestre);

			//this.entity.setTipoSemestre(tipoSemestre);

			this.entity.setCurso(this.subView.getTxtCurso().getValue());
			this.entity.setUnidade(this.subView.getTxtUnidade().getValue());
			this.entity.setAnoFormatura(this.subView.getTxtAnoFormatura().getValue());
			
			this.entity.setQuantidadeFormandos((Integer) this.subView.getTxtQuantidadeFormandos().getConvertedValue());
			this.entity.setDataContrato(this.subView.getPdfDataContrato().getValue());
			this.entity.setDataPrimeiroEvento(this.subView.getPdfDataPrimeiroEvento().getValue());
			
			
			contratoEventosDAO.saveOrUpdate(this.entity);
			//this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}


		
	}
	
	public void carregarTipoSemestre() {
		for (TipoSemestre s : TipoSemestre.values()) {
			this.subView.getCbTipoSemestre().addItem(s);
		}
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
			contratoEventosDAO.deleteAll(ids);
			//this.business.deleteAll(ids);

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
}
