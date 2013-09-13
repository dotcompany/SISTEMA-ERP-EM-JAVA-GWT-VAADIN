package dc.visao.framework.component.manytoonecombo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.porotype.iconfont.FontAwesome.Icon;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ManyToOneCombo<T> extends CustomComponent {


	private HorizontalLayout mainLayout;
	private Button btnEdit;
	private ComboBox cmbResult;
	private ItemValue createItemValue;

	private ManyToOneComboModel<T> model;
	private String filterString;

	public static int ITEM_TYPE_BEAN = 0;
	public static int ITEM_TYPE_CREATE = 1;

	public ManyToOneCombo() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		setupActions();
	}

	@SuppressWarnings("serial")
	class FilteredBeanItemContainer extends BeanItemContainer<ItemValue> {

		public FilteredBeanItemContainer() throws IllegalArgumentException {
			super(ItemValue.class);
		}

		@Override
		public void addContainerFilter(Filter filter) {
			String q = filterString;
			if (q != null && q.length() >= 2) {
				removeAllItems();

				// Adiciona os itens filtrados
				if(model != null){
					List l = model.getResultado(q);
					if(l!= null && l.size() > 0){
						addAll(wrapValues(l));
					}
				}

				// Adiciona "criar novo"
				createItemValue = new ItemValue();
				createItemValue.setType(ITEM_TYPE_CREATE);
				createItemValue.setCaption("Criar novo: " + q);
				createItemValue.setFilter(q);
				addItem(createItemValue);
			}
		}

	}

	@SuppressWarnings("serial")
	private void setupActions() {

		createItemValue = new ItemValue();
		createItemValue.setType(ITEM_TYPE_CREATE);

		cmbResult.setContainerDataSource(new FilteredBeanItemContainer());
		cmbResult.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		cmbResult.setItemCaptionPropertyId("caption");

		cmbResult.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				selectSpecialItem();
			}
		});


		btnEdit.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if(getValue() != null)
					model.onEditar(getValue());
			}
		});


	}

	@SuppressWarnings("serial")
	public class ItemValue implements Serializable {
		private T bean;
		private int type = ITEM_TYPE_BEAN;
		private String caption;
		private String filter;

		public String getCaption() {
			try {
				if (type == ITEM_TYPE_BEAN) {
					return BeanUtils.getProperty(bean, model.getCaptionProperty()).toString();
				} else {
					return this.caption;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public T getBean() {
			return bean;
		}
		public void setBean(T bean) {
			this.bean = bean;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}

		public void setCaption(String caption) {
			this.caption = caption;
		}

		public String getFilter() {
			return filter;
		}

		public void setFilter(String filter) {
			this.filter = filter;
		}
	}

	@SuppressWarnings("unchecked")
	protected void selectSpecialItem() {
		ItemValue val = (ItemValue) cmbResult.getValue();
		if (val == null) return;
		if (val.getType() == ITEM_TYPE_BEAN) return;

		if (val.getType() == ITEM_TYPE_CREATE) {
			model.onCriarNovo(val.getFilter());
		}
	}


	private List<ItemValue> wrapValues(List<T> resultado) {
		List<ItemValue> lst = new ArrayList<>();
		for (T val : resultado) {
			ItemValue item = new ItemValue();
			item.setBean(val);
			lst.add(item);
		}
		return lst;
	}

	@SuppressWarnings("unchecked")
	public T getValue() {
		if(cmbResult != null && cmbResult.getValue() != null){
			return ((ItemValue)cmbResult.getValue()).getBean();	
		}else{
			return null;
		}
		
	}

	private HorizontalLayout buildMainLayout() {
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// cmbResult
		cmbResult = new ComboBox(){
			@Override
			public void changeVariables(Object source, Map<String, Object> variables) {
				String newFilter;
				if ((newFilter = (String) variables.get("filter")) != null) {
					filterString = newFilter;
				}
				super.changeVariables(source, variables);
			}
		};

		cmbResult.setImmediate(true);
		cmbResult.setWidth("100.0%");
		mainLayout.addComponent(cmbResult);

		// lblEdit
		btnEdit = new Button();
		btnEdit.setCaption(Icon.edit.toString());
		btnEdit.setHtmlContentAllowed(true);
		btnEdit.setId("lblEdit");
		mainLayout.addComponent(btnEdit);
		mainLayout.setComponentAlignment(btnEdit, Alignment.BOTTOM_LEFT);
		UI.getCurrent().getPage().getStyles().add("#lblEdit i { font-size: 12pt; } #lblEdit { padding: 5px 1px 3px 4px; margin-left:-8PX}");
		return mainLayout;
	}


	public void setCaption(String caption) {
		cmbResult.setCaption(caption);
	}

	public String getCaption() {
		return cmbResult.getCaption();
	}

	public ManyToOneComboModel<T> getModel() {
		return model;
	}

	public void setModel(ManyToOneComboModel<T> model) {
		this.model = model;
	}


	public void setValue(T bean) {
		ItemValue item = new ItemValue();
		item.setBean(bean);
		cmbResult.getContainerDataSource().removeAllItems();
		cmbResult.getContainerDataSource().addItem(item);
		cmbResult.setValue(item);
	}

}