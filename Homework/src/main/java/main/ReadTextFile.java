package main;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import methods.AssertionManager;
import methods.FileProcessor;

public class ReadTextFile {
	public static void main(String[] args) {
		ObjectMapper objectMapper = new ObjectMapper();
		FileProcessor fileProcessor = new FileProcessor(objectMapper);
		AssertionManager statsManager = new AssertionManager();

		Map<String, Integer> showCount = new HashMap<>();
		Map<String, Integer> prerollCountByShowId = new HashMap<>();
		Map<String, Integer> deviceTypeCount = new HashMap<>();

		String root = System.getProperty("user.dir");
		String projectPath = Paths.get(root, "src", "test", "resources", "text_documents", "downloads.txt").toString();

		fileProcessor.processFile(projectPath, showCount, prerollCountByShowId, deviceTypeCount);

		statsManager.assertMostPopularShow(showCount);
		statsManager.assertMostUsedDevice(deviceTypeCount);
		statsManager.assertPrerollCounts(prerollCountByShowId);
	}
}
