package dc.servicos.dao.framework.geral;

import java.io.Serializable;

import org.springframework.stereotype.Repository;


@Repository
public class GenericListDAO extends AbstractCrudDAO<Serializable> {

	private Class pojoClass;
	
	public void setPojoClass(Class c){
		this.pojoClass = c; 
	}
	
	@Override
	protected Class<Serializable> getEntityClass() {
		// TODO Auto-generated method stub
		return pojoClass;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
