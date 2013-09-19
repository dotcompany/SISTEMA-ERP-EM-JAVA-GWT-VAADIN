package dc.framework;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.type.Type;

import com.sun.istack.logging.Logger;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.visao.spring.SecuritySessionProvider;

public class ModelInterceptor extends EmptyInterceptor{
	
	

	private static final long serialVersionUID = 5903477031218867131L;
	public static Logger logger = Logger.getLogger(ModelInterceptor.class);
	
	
	@Override
    public boolean onSave( Object entity, Serializable id, Object[] state,
        String[] propertyNames, Type[] types ) throws CallbackException
    {
       logger.info("A "+entity.getClass().getCanonicalName() + " was created.");
       setMultiEmpresa(entity);
       return false;
    }

	private void setMultiEmpresa(Object entity) {
		if(entity instanceof AbstractMultiEmpresaModel){
			   AbstractMultiEmpresaModel model = (AbstractMultiEmpresaModel) entity;
			   logger.info("multiEmpresa model will be saved...");
			   model.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
		   }else{
			   logger.info("isn´t a multiEmpresa model...");  
		   }
	}
	
	 @Override
	    public boolean onFlushDirty( Object entity, Serializable id,
	        Object[] currentState, Object[] previousState, String[] propertyNames,
	        Type[] types )
	    {
		 logger.info("A "+entity.getClass().getCanonicalName() + " was updated.");
		 setMultiEmpresa(entity);
	       return false;
	    }
	    
	
	   @Override
	   public void onCollectionUpdate(Object collection, Serializable key)
	    {
	       
	       if (collection instanceof PersistentSet)
	       {
	          PersistentSet newValues = (PersistentSet) collection;          
	          Object owner = newValues.getOwner();
	          
	          Set<?> oldValues = ((Map<?,?>) newValues.getStoredSnapshot()).keySet();
	          
	          System.out.println(owner + " had one of its collections changed.");
	          System.out.println("The collection property is "+ newValues.getRole().replace(owner.getClass().getCanonicalName() + ".", "") );
	          
	          // find all objects that were added
	          for (Object newValue : newValues)
	          {
	             if (!oldValues.contains(newValue))
	             {
	                System.out.println("A " + newValue + " was added.");
	             }
	          }
	          
	          // find all objects that were deleted
	          for (Object oldValue : oldValues)
	          {
	             if (!newValues.contains(oldValue))
	             {
	                System.out.println("A " + oldValue + " was removed.");
	             }
	          }
	          
	       }else{
	    	   System.out.println("not persistent set");
	    	   if(collection != null){
	    		   System.out.println(collection.getClass());   
	    	   }else{
	    		   System.out.println("collection is null");
	    	   }
	    		   
	    	   
	       }
	    }
	       

}
