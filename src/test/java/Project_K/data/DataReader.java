package Project_K.data;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;     

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException
	{
		String jsoncontent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\Project_K\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(
	jsoncontent,  new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		}
	}


