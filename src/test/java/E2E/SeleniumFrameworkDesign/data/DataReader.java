package E2E.SeleniumFrameworkDesign.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// converting .json file to String
		String jsonString = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "//src//test//java//E2E//SeleniumFrameworkDesign//data//Order.json"),
				StandardCharsets.UTF_8);

		// Converting String to HashMap using Jackson DataBind
		// for this we need to add Jackson Databind dependency from maven repository -
		// in pom.xml
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		// data variable stores- {{map1},{map2}}

	}
}
