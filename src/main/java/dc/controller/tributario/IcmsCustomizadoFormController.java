package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.model.business.tributario.IcmsCustomizadoBusiness;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tributario.ICMSCustomizadoFormView;

@Controller
@Scope("prototype")
public class IcmsCustomizadoFormController extends CRUDFormController<IcmsCustomizadoCabecalhoEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private IcmsCustomizadoCabecalhoEntity entity;
	private ICMSCustomizadoFormView subView;
	
	@Autowired
    private IcmsCustomizadoBusiness<IcmsCustomizadoCabecalhoEntity> business;

	public IcmsCustomizadoFormController() {
	}

	@Override
	public String getViewIdentifier() {
	    return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		try {
            fieldGroup.commit();

            return true;
        } catch (FieldGroup.CommitException ce) {

            return false;
        }
	}

	@Override
	protected void criarNovoBean() {
		try {
            this.entity = new IcmsCustomizadoCabecalhoEntity();

            fieldGroup.setItemDataSource(this.entity);

        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }
		
	}

	@Override
	protected void initSubView() {
		try {
            this.subView = new ICMSCustomizadoFormView(this);
            
            this.fieldGroup = new DCFieldGroup<>(IcmsCustomizadoCabecalhoEntity.class);
            
            fieldGroup.bind(this.subView.getCmbOrigemMercadoria(), "origemMercadoria");
            fieldGroup.bind(this.subView.getTxaDescricao(), "descricao");


        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
            this.entity = this.business.find(id);

            fieldGroup.setItemDataSource(this.entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	protected void actionSalvar() {
		try {
            this.business.saveOrUpdate(this.entity);
            
            notifiyFrameworkSaveOK(this.entity);
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
		return "Icms Customizado";
	}

	 @Override
	    public boolean isFullSized() {
	        return true;
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
	public IcmsCustomizadoCabecalhoEntity getModelBean() {
		return entity;
	}
	
	public IcmsCustomizadoBusiness<IcmsCustomizadoCabecalhoEntity> getBusiness() {
        return business;
    }

}
