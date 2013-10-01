package dc.visao.framework.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Scope;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.json.client.JSONParser;

@org.springframework.stereotype.Component
@Scope("singleton")
public class TableFileHandler {
	
	@Value("${user.home}/${table.fileBasePath}") 
	private String filePath;

	public void save(JsonObject json,String user, String entityName  ) {
		// TODO Auto-generated method stub
		try {
			 
			File file = new File(getFullPath(user,entityName));
			file.getParentFile().mkdirs();
			FileWriter writer= new FileWriter(file);
		
			writer.write(json.toString());
			writer.flush();
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFullPath(String user, String entityName) {
		return filePath + "/" + user + "/"+entityName + ".json";
	}

	public JsonObject load(String user,String entityName) {
		// TODO Auto-generated method stub
		FileReader reader;
		try {
			reader = new FileReader(getFullPath(user, entityName));
			JsonParser p2 = new JsonParser();
			JsonElement el = p2.parse(reader);
			reader.close();
			return el.getAsJsonObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
