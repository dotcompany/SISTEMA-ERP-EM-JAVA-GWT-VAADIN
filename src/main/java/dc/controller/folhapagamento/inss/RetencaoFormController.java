package dc.controller.folhapagamento.inss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.folhapagamento.inss.InssEntity;
import dc.entidade.folhapagamento.inss.RetencaoEntity;
import dc.entidade.folhapagamento.inss.ServicoEntity;
import dc.servicos.dao.folhapagamento.inss.InssDAO;
import dc.servicos.dao.folhapagamento.inss.RetencaoDAO;
import dc.servicos.dao.folhapagamento.inss.ServicoDAO;
import dc.visao.folhapagamento.inss.RetencaoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class RetencaoFormController extends CRUDFormController<RetencaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RetencaoFormView subView;

	/** DAO'S */

	@Autowired
	private RetencaoDAO pDAO;

	@Autowired
	private InssDAO iDAO;

	@Autowired
	private ServicoDAO sDAO;

	/** ENTITIES */

	private RetencaoEntity pEntity;

	/** CONSTRUTOR */

	public RetencaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new RetencaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Retenção";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			
			 this.pEntity = this.pDAO.find(id);
			fieldGroup.setItemDataSource(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
        try {
			
			this.subView = new RetencaoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(RetencaoEntity.class);
			
			fieldGroup.bind(this.subView.getTfValorMensal(),"valorMensal");
			fieldGroup.bind(this.subView.getTfValor13(),"valor13");
			
			 this.subView.getCbInss().configuraCombo(
					 "competencia", InssListController.class, this.iDAO, this.getMainController());
			 this.subView.getCbServico().configuraCombo(
					 "nome", ServicoListController.class, this.sDAO, this.getMainController());
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	/** COMBOS */

	public List<InssEntity> inssListarTodos() {
		List<InssEntity> auxLista = new ArrayList<InssEntity>();

		auxLista = this.iDAO.listarTodos();

		return auxLista;
	}

	public List<ServicoEntity> servicoListarTodos() {
		List<ServicoEntity> auxLista = new ArrayList<ServicoEntity>();

		auxLista = this.sDAO.listarTodos();

		return auxLista;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	@Override
	public RetencaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

@Override
protected void criarNovoBean() {
	try {
        this.pEntity = new RetencaoEntity();

       // Atribui a entidade nova como origem de dados dos campos do formulario no FieldGroup
        fieldGroup.setItemDataSource(this.pEntity);

    } catch (Exception e) {
        e.printStackTrace();
        mensagemErro(e.getMessage());
   }
		
}

}