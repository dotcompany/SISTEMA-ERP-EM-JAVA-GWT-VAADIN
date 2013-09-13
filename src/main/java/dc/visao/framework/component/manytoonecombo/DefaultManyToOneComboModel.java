package dc.visao.framework.component.manytoonecombo;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import dc.controller.pessoal.TipoColaboradorListController;
import dc.entidade.framework.PapelMenu;
import dc.entidade.geral.Usuario;
import dc.entidade.pessoal.TipoColaborador;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
import dc.servicos.dao.sistema.PapelDAO;
import dc.visao.framework.geral.CRUDListController;
import dc.visao.framework.geral.ControllerAcesso;
import dc.visao.framework.geral.MainController;
import dc.visao.spring.SecuritySessionProvider;

public class DefaultManyToOneComboModel<T> implements ManyToOneComboModel<T> {
		
		private AbstractCrudDAO<T> dao;
		private Class ctrlClass;
		private MainController mainController;		
		private PapelDAO daoPapel;
		
		public static final int FULL_SIZE_MODAL = 1;
		public static final int MEDIUM_SIZE_MODAL = 2;
		public static final int SMALL_SIZE_MODAL = 3;
		
		private int modalSize = 2;
		
		public DefaultManyToOneComboModel(Class controllerClass, AbstractCrudDAO<T> dao,MainController mainController,PapelDAO daoPapel){
			this.dao = dao;
			this.daoPapel = daoPapel; 
			this.ctrlClass = controllerClass;
			this.mainController = mainController;
		}
		
		public void setModalSize(int modalSizeType){
			this.modalSize = modalSizeType;
		}

		@Override
		public void onCriarNovo(String filter) {
			Notification.show("Selecionado Criar Novo: " + filter);
			Window w = new Window();
			CRUDListController<TipoColaborador> ctrl = (CRUDListController) mainController.getEntityController(ctrlClass);
			 Usuario u = SecuritySessionProvider.getUsuario();
			 
			if(ctrl instanceof ControllerAcesso){
				ControllerAcesso ctrlAcesso = (ControllerAcesso) ctrl;
				if(u.getAdministrador()){
					ctrlAcesso.setAcessoLiberado();
				}else{
					PapelMenu pf = daoPapel.getPapelMenuByPapelAndMenuControllerClass(u.getPapel().getId(),TipoColaboradorListController.class.toString());
					ctrlAcesso.setPapelMenu(pf);	
				}
				
			}
			ctrl.getPublicFormController().criarNovo();
			w.setContent((Component) ctrl.getPublicFormController().getView());
			
			w.center();
			if(modalSize != 1 && modalSize != 2){
				w.setWidth("70%");
				w.setHeight("80%");	
			}else if (modalSize == 1){
				w.setWidth("100%");
				w.setHeight("100%");	
			}else{
				w.setWidth("35%");
				w.setHeight("40%");
			}
			
			w.setModal(true);
			UI.getCurrent().addWindow(w);
		}
		
		@Override
		public List<T> getResultado(String q) {
			return dao.comboTextSearch(q, dao.getSearchFields());
		}
		
		@Override
		public Class<T> getEntityClass() {
			Class<T> clazz = (Class<T>) this.getTypeArguments(DefaultManyToOneComboModel.class, this.getClass()).get(0);
			return clazz;
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
			Notification.show("Selecionado Editar: " + value);
			
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
             
	
	
}
