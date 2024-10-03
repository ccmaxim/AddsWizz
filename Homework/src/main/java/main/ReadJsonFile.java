package main;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import methods.JsonProcessor;
import methods.StatisticsManager;

public class ReadJsonFile {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonProcessor jsonProcessor = new JsonProcessor(objectMapper);
        StatisticsManager statsManager = new StatisticsManager();

        Set<String> targetShows = new HashSet<>(Arrays.asList("Who Trolled Amber", "Crime Junkie",
                "Stuff You Should Know", "The Joe Rogan Experience"));

        Map<String, Integer> showCount = new HashMap<>();
        Map<String, Integer> prerollCountByShowId = new HashMap<>();
        Map<String, Integer> deviceTypeCount = new HashMap<>();
        
        //Count initialize
        targetShows.forEach(show -> {
            showCount.put(show, 0);
            prerollCountByShowId.put(show, 0);
        });

        String root = System.getProperty("user.dir");
        String projectPath = Paths.get(root, "src", "test", "resources", "text_documents", "downloads.txt").toString();
        
        jsonProcessor.processFile(projectPath, targetShows, showCount, prerollCountByShowId, deviceTypeCount);

        statsManager.displayMostPopularShow(showCount);
        statsManager.displayMostUsedDeviceType(deviceTypeCount);
        statsManager.displayPrerollCounts(prerollCountByShowId);
    }
}
