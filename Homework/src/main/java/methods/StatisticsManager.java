package methods;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import constants.AssertData;

// StatisticsManager.java
public class StatisticsManager {
	public void displayMostPopularShow(Map<String, Integer> showCount) {

		String expectedPopularShow = AssertData.getPopularShow();
		int expectedPopularDownloads = AssertData.getPopularShowDownloads();

		Map.Entry<String, Integer> actualPopularShow = Collections.max(showCount.entrySet(),
				Map.Entry.comparingByValue());

		Assertions.assertMostPopularShow(expectedPopularShow, expectedPopularDownloads, actualPopularShow.getKey(),
				actualPopularShow.getValue());

		
	}

	public void displayMostUsedDeviceType(Map<String, Integer> deviceTypeCount) {

		String expectedDeviceType = AssertData.getMostUsedDevice();
		int expectedDeviceDownloads = AssertData.getNumberOfDownloads();

		Map.Entry<String, Integer> actualDeviceType = Collections.max(deviceTypeCount.entrySet(),
				Map.Entry.comparingByValue());

		Assertions.assertMostUsedDevice(expectedDeviceType, expectedDeviceDownloads, actualDeviceType.getKey(),
				actualDeviceType.getValue());

		
	}

	public void displayPrerollCounts(Map<String, Integer> prerollCountByShowId) {
	    Map<String, Integer> expectedPrerollCounts = AssertData.getExpectedPrerollCounts();

	    List<Map.Entry<String, Integer>> actualPrerollCounts = new ArrayList<>(prerollCountByShowId.entrySet());
	    actualPrerollCounts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    // Check for preroll counts with sorted entries
	    Assertions.assertPrerollCount(actualPrerollCounts, expectedPrerollCounts);
	}

}
