package dc.controller.sistema;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.FormLayout;

import dc.entidade.pessoal.Colaborador;

public class ColaboradorField extends CustomField<Colaborador>{

	private static final long serialVersionUID = 1510031270980694979L;

	private BeanFieldGroup<Colaborador> binder = new BeanFieldGroup<>(Colaborador.class);

	
	@Override
	protected Component initContent() {
		FormLayout layout = new FormLayout();
		binder.setItemDataSource(this.getInternalValue());
		layout.addComponent(binder.buildAndBind("matricula"));
        return layout;
	}

	@Override
	public Class<? extends Colaborador> getType() {
		return Colaborador.class;
	}
	
	 
	

}
