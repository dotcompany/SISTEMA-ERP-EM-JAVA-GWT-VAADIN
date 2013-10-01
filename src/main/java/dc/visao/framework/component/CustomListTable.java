package dc.visao.framework.component;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sun.istack.logging.Logger;
import com.vaadin.ui.Table;

import dc.visao.spring.SecuritySessionProvider;

public class CustomListTable extends Table {
	
	
	private static final long serialVersionUID = 735711713125549382L;
	public static Logger logger = Logger.getLogger(CustomListTable.class);
	private TableFileHandler fileHandler = new TableFileHandler();
	private String entityName;
	public static final Object CUSTOM_SELECT_ID = "Selecionar";
	
	public void setEntityName(String name){
		this.entityName = name;
	}
	
	 public void setColumnCollapsed(Object propertyId, boolean collapsed) 
	           throws IllegalStateException {
		 if(!propertyId.equals(CUSTOM_SELECT_ID)){
		    super.setColumnCollapsed(propertyId, collapsed);
		    logger.info("collapsed" + collapsed);
		    logger.info(String.valueOf(propertyId));
		    saveToFile();	 
		 }
	 }
	 
	 public boolean loadFromFile(){
		 Integer userId = SecuritySessionProvider.getUsuario().getId();
		 JsonObject o = fileHandler.load(String.valueOf(userId),entityName);
		 if(o != null){
			 doMapColluns(o);
		 }
		 
		 
		 return o != null && o.entrySet().size() >0 ;
	 }
	 
	 private void doMapColluns(JsonObject o) {
		// TODO Auto-generated method stub
		 Set<Entry<String, JsonElement>> s = o.entrySet();
		 java.util.Iterator<Entry<String, JsonElement>> it = s.iterator();
		 ArrayList<String> colunas = new ArrayList<String>();
		 while (it.hasNext()){
			 Entry k  = it.next();
			 logger.info(String.valueOf(k.getKey()));
			 logger.info(String.valueOf(k.getValue()));
			 colunas.add(String.valueOf(k.getKey()));
			 JsonPrimitive p =   (JsonPrimitive) k.getValue();
			 setColumnWidth(k.getKey(), p.getAsInt());
			 //logger.info(String.valueOf(k));
			 //logger.info(String.valueOf(o.get(String.valueOf(k))));
		 }
		
		 this.setVisibleColumns(colunas.toArray());
	}

	public void saveToFile(){
		 Integer userId = SecuritySessionProvider.getUsuario().getId();
		 Object[] os =getVisibleColumns();
		 logger.info("saving to file, all visible collumns");
		 JsonObject json = new JsonObject();
		 for(Object o : os){ 
			 String propertyId = String.valueOf(o);
			 if(!isColumnCollapsed(propertyId)){
				 int w= getColumnWidth(propertyId);
				 logger.info("property: " + propertyId);
				 logger.info("width" + w); 
				 if(!propertyId.equals("DEBUG_PROPERTY_ID_QUERY_COUT") && !propertyId.equals("DEBUG_PROPERTY_ID_BATCH_INDEX") && !propertyId.equals("DEBUG_PROPERTY_ID_BATCH_QUERY_TIME")){
					 json.addProperty(propertyId, String.valueOf(w));	 
				 } 
			 }
		 }
		 
		 fileHandler.save(json,String.valueOf(userId), entityName);
		 
	 }

	public void setFileHandler(TableFileHandler handle) {
		this.fileHandler = handle;
		
	}

}
