package methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessor {
	private final ObjectMapper objectMapper;

	public JsonProcessor(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void processFile(String filePath, Set<String> targetShows, Map<String, Integer> showCount,
			Map<String, Integer> prerollCountByShowId, Map<String, Integer> deviceTypeCount) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			reader.lines().forEach(line -> {
				try {
					JsonNode jsonNode = objectMapper.readTree(line);
					processObject(jsonNode, targetShows, showCount, prerollCountByShowId, deviceTypeCount);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processObject(JsonNode jsonNode, Set<String> targetShows, Map<String, Integer> showCount,
			Map<String, Integer> prerollCountByShowId, Map<String, Integer> deviceTypeCount) {

		String showId = jsonNode.path("downloadIdentifier").path("showId").asText();
		String city = jsonNode.path("city").asText();
		String deviceType = jsonNode.path("deviceType").asText();

		deviceTypeCount.merge(deviceType, 1, Integer::sum);

		if (targetShows.contains(showId) && city.equalsIgnoreCase("San Francisco")) {
			showCount.merge(showId, 1, Integer::sum);
		}

		countPreroll(jsonNode, showId, prerollCountByShowId);
	}

	private void countPreroll(JsonNode jsonNode, String showId, Map<String, Integer> prerollCountByShowId) {
		JsonNode opportunitiesNode = jsonNode.path("opportunities");
		if (opportunitiesNode.isArray()) {
			for (JsonNode opportunity : opportunitiesNode) {
				JsonNode adBreakIndexNode = opportunity.path("positionUrlSegments").path("aw_0_ais.adBreakIndex");
				if (adBreakIndexNode.isArray() && containsPreroll(adBreakIndexNode)) {
					prerollCountByShowId.put(showId, prerollCountByShowId.getOrDefault(showId, 0) + 1);
				}
			}
		}
	}

	private boolean containsPreroll(JsonNode adBreakIndexNode) {
		for (JsonNode adBreak : adBreakIndexNode) {
			if (adBreak.asText().equalsIgnoreCase("preroll")) {
				return true;
			}
		}
		return false;
	}

}
