package dc.controller.geral.eventos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.enums.TipoSemestre;
import dc.control.util.ClassUtils;
import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.servicos.dao.geral.eventos.CerimonialEventosDAO;
import dc.servicos.dao.geral.eventos.ContratoEventosDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.eventos.ContratoEventosFormView;

@Controller
@Scope("prototype")
public class ContratoEventosFormController extends
		CRUDFormController<ContratoEventosEntity> {

	private static final long serialVersionUID = 1L;

	private ContratoEventosFormView subView;

	// @Autowired
	// private ContratoBusiness<ContratoEventosEntity> business;

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

	/*
	 * public ContratoBusiness<ContratoEventosEntity> getBusiness() { return
	 * business; }
	 */

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
			// Commit tenta transferir os dados do View para a entidade ,
			// levando em conta os critérios de validação.
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {
			return false;
		}

	}

	@Override
	protected void criarNovoBean() {

		try {
			this.entity = new ContratoEventosEntity();
			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void initSubView() {

		try {
			this.subView = new ContratoEventosFormView(this);

			this.subView.InitCbs(getTipoSemestre());
			
            this.fieldGroup = new DCFieldGroup<>(ContratoEventosEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTxtCurso(),"curso");
			fieldGroup.bind(this.subView.getTxtUnidade(),"unidade");
			fieldGroup.bind(this.subView.getTxtAnoFormatura(),"anoFormatura");
			fieldGroup.bind(this.subView.getTxtQuantidadeFormandos(),"quantidadeFormandos");
			
			this.subView.getMocNomeCerimonial().configuraCombo(
					"nome", CerimonialEventosListController.class, this.cerimonialEventosDAO, this.getMainController());

			// carregarTipoSemestre();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {

		try {
			// this.entity = this.business.find(id);

			this.entity = contratoEventosDAO.find(id);

			fieldGroup.setItemDataSource(this.entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** COMBO */
	public List<String> getTipoSemestre() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (TipoSemestre en : TipoSemestre.values()) {
				siLista.add(en.ordinal(), en.toString());
			}

			return siLista;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected void actionSalvar() {

		try {

			contratoEventosDAO.saveOrUpdate(this.entity);
			// this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	/*
	 * public void carregarTipoSemestre() { for (TipoSemestre s :
	 * TipoSemestre.values()) { this.subView.getCbTipoSemestre().addItem(s); } }
	 */

	@Override
	protected Component getSubView() {
		// TODO Auto-generated method stub
		return subView;
	}

	@Override
	protected void remover(List<Serializable> ids) {

		try {
			contratoEventosDAO.deleteAll(ids);
			// this.business.deleteAll(ids);

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
