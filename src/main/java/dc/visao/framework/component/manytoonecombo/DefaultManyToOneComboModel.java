package dc.visao.framework.component.manytoonecombo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Notification;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.FmMenu;
import dc.entidade.framework.PapelMenu;
import dc.model.business.AbstractBusiness;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.visao.framework.geral.CRUDListController;
import dc.visao.framework.geral.ControllerAcesso;
import dc.visao.framework.geral.MainController;
import dc.visao.spring.SecuritySessionProvider;

public class DefaultManyToOneComboModel<T> implements ManyToOneComboModel<T> {

	private AbstractCrudDAO<T> dao;
	private Class ctrlClass;
	private MainController mainController;
	private int modalSize = 1; // Alterado MarcosRibeiro
	private ManyToOneCombo<T> combo;
	private AbstractBusiness<T> business;
	private Boolean getAll;

	public static final int FULL_SIZE_MODAL = 1;
	public static final int MEDIUM_SIZE_MODAL = 2;
	public static final int SMALL_SIZE_MODAL = 3;

	public DefaultManyToOneComboModel(Class controllerClass,
			AbstractCrudDAO<T> dao, MainController mainController) {
		this(controllerClass, dao, mainController, false);
	}

	public DefaultManyToOneComboModel(Class controllerClass,
			AbstractCrudDAO<T> dao, MainController mainController,
			Boolean getAll, AbstractBusiness<T> business) {
		this.dao = dao;
		this.ctrlClass = controllerClass;
		this.mainController = mainController;
		this.getAll = getAll;
		this.business = business;
	}

	public DefaultManyToOneComboModel(Class controllerClass,
			AbstractCrudDAO<T> dao, MainController mainController,
			Boolean getAll) {
		this(controllerClass, dao, mainController, false, null);
	}
	
	public DefaultManyToOneComboModel(Class controllerClass, MainController mainController,
			Boolean getAll, AbstractBusiness<T> business) {
		this(controllerClass, null, mainController, getAll, business);
	}

	public void setModalSize(int modalSizeType) {
		this.modalSize = modalSizeType;
	}

	@Override
	public void onCriarNovo(String filter) {
		Notification.show("Selecionado Criar Novo: " + filter);

		CRUDListController ctrl = (CRUDListController) mainController
				.getEntityController(ctrlClass);

		UsuarioEntity u = SecuritySessionProvider.getUsuario();

		if (ctrl instanceof ControllerAcesso) {
			ControllerAcesso ctrlAcesso = (ControllerAcesso) ctrl;
			if (u.getAdministrador()) {
				ctrlAcesso.setAcessoLiberado();
			} else {
				PapelMenu pf = mainController.getDaoPapel()
						.getPapelMenuByPapelAndMenuControllerClass(
								u.getPapel().getId(), ctrlClass.toString());
				ctrlAcesso.setPapelMenu(pf);
			}

		}
		ctrl.setSaveListener(new ModalWindowSaveListener<T>() {

			@Override
			public void onSave(T object) {
				combo.setValue(object);
			}

		});
		ctrl.openOnNewWindow(modalSize, CRUDListController.WINDOW_FORM);
		ctrl.getPublicFormController().criarNovo();
	}

	@Override
	public List<T> getResultado(String q) {
		System.out.println("");

		CRUDListController ctrl = (CRUDListController) mainController
				.getEntityController(ctrlClass);

		FmMenu menu = ctrl.getMenu();

		if (business != null) {
			try {
				return business.fullTextSearch(q);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return dao.comboTextSearch(q, menu, getAll);
	}

	@Override
	public Class<T> getEntityClass() {
		if (business != null) {
			business.getEntityClass();
		}
		
		return dao.getEntityClass();
	}

	@Override
	public String getCaptionProperty() {
		return "nome";
	}

	public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass,
			Class<? extends T> childClass) {
		Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class) {
				// there is no useful information for us in raw types, so just
				// keep going.
				type = ((Class) type).getGenericSuperclass();
			} else {
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Class<?> rawType = (Class) parameterizedType.getRawType();

				Type[] actualTypeArguments = parameterizedType
						.getActualTypeArguments();
				TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++) {
					resolvedTypes
							.put(typeParameters[i], actualTypeArguments[i]);
				}

				if (!rawType.equals(baseClass)) {
					type = rawType.getGenericSuperclass();
				}
			}
		}

		// finally, for each actual type argument provided to baseClass,
		// determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class) {
			actualTypeArguments = ((Class) type).getTypeParameters();
		} else {
			actualTypeArguments = ((ParameterizedType) type)
					.getActualTypeArguments();
		}
		List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments) {
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}

	@Override
	public void onEditar(T value) {
		Notification.show("Selecionado Editar");
		CRUDListController ctrl = (CRUDListController) mainController
				.getEntityController(ctrlClass);

		UsuarioEntity u = SecuritySessionProvider.getUsuario();

		if (ctrl instanceof ControllerAcesso) {
			ControllerAcesso ctrlAcesso = (ControllerAcesso) ctrl;
			if (u.getAdministrador()) {
				ctrlAcesso.setAcessoLiberado();
			} else {
				PapelMenu pf = mainController.getDaoPapel()
						.getPapelMenuByPapelAndMenuControllerClass(
								u.getPapel().getId(), ctrlClass.toString());
				ctrlAcesso.setPapelMenu(pf);
			}

		}
		ctrl.setSaveListener(new ModalWindowSaveListener<T>() {

			@Override
			public void onSave(T object) {
				combo.setValue(object);
			}

		});
		ctrl.openOnNewWindow(modalSize, CRUDListController.WINDOW_FORM);
		ctrl.getPublicFormController()
				.load((AbstractModel<Serializable>) value);

	}

	private static Class<?> getClass(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			return getClass(((ParameterizedType) type).getRawType());
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type)
					.getGenericComponentType();
			Class<?> componentClass = getClass(componentType);
			if (componentClass != null) {
				return Array.newInstance(componentClass, 0).getClass();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public void setCombo(ManyToOneCombo<T> manyToOneCombo) {
		this.combo = manyToOneCombo;
	}

	@Override
	public List<T> getAll() {
		// CRUDListController ctrl = (CRUDListController)
		// mainController.getEntityController(ctrlClass);
		
		if(business != null){
			try {
				return business.findAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		FmMenu menu = dao.getMenu(this.ctrlClass.getName());
		return dao.getAllForCombo(this.getEntityClass(),
				SecuritySessionProvider.getUsuario().getConta().getEmpresa()
						.getId(), menu, this.getAll);
	}

	@Override
	public void onAdvancedSearch() {
		Notification.show("Selecionado Busca avançada");
		CRUDListController ctrl = (CRUDListController) mainController
				.getEntityController(ctrlClass);

		UsuarioEntity u = SecuritySessionProvider.getUsuario();

		if (ctrl instanceof ControllerAcesso) {
			ControllerAcesso ctrlAcesso = (ControllerAcesso) ctrl;
			if (u.getAdministrador()) {
				ctrlAcesso.setAcessoLiberado();
			} else {
				PapelMenu pf = mainController.getDaoPapel()
						.getPapelMenuByPapelAndMenuControllerClass(
								u.getPapel().getId(), ctrlClass.toString());
				ctrlAcesso.setPapelMenu(pf);
			}

		}

		ctrl.setSelectionListener(new ModalWindowSelectionListener<T>() {

			@Override
			public void onSelect(T object) {
				combo.setValue(object);
			}

		});

		ctrl.openOnNewWindow(modalSize, CRUDListController.WINDOW_LIST);

	}

	public boolean permissionToCreateOrEdit() {
		FmMenu menu = dao.getMenu(this.ctrlClass.getName());
		if (menu.getPermissaoOperacao() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public AbstractBusiness<T> getBusiness() {
		return business;
	}

	public void setBusiness(AbstractBusiness<T> business) {
		this.business = business;
	}

}
