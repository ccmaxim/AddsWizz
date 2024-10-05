package methods;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import constants.AssertData;

public class AssertionManager {

	@Test
	public void assertMostPopularShow(Map<String, Integer> showCount) {

		String expectedPopularShow = AssertData.getPopularShow();
		int expectedPopularDownloads = AssertData.getPopularShowDownloads();

		Map.Entry<String, Integer> actualShow = Collections.max(showCount.entrySet(), Map.Entry.comparingByValue());

		assertEquals(expectedPopularShow, actualShow.getKey());
		assertEquals(expectedPopularDownloads, actualShow.getValue());

		// Print if assertion passes
		System.out.println("-------------------------------------");
		System.out.println("Most popular show is: " + actualShow.getKey());
		System.out.println("Number of downloads is: " + actualShow.getValue());
		System.out.println("-------------------------------------");

	}

	@Test
	public void assertMostUsedDevice(Map<String, Integer> deviceTypeCount) {

		String expectedDeviceType = AssertData.getMostUsedDevice();
		int expectedDeviceDownloads = AssertData.getNumberOfDownloads();

		Map.Entry<String, Integer> actualDeviceType = Collections.max(deviceTypeCount.entrySet(),
				Map.Entry.comparingByValue());

		assertEquals(expectedDeviceType, actualDeviceType.getKey());
		assertEquals(expectedDeviceDownloads, actualDeviceType.getValue());

		// Print if assertion passes
		System.out.println("Most popular device is: " + actualDeviceType.getKey());
		System.out.println("Number of downloads is: " + actualDeviceType.getValue());
		System.out.println("-------------------------------------");
	}

	@Test
	public void assertPrerollCounts(Map<String, Integer> prerollCountByShowId) {
		Map<String, Integer> expectedPrerollCounts = AssertData.getExpectedPrerollCounts();

		assertEquals(expectedPrerollCounts, prerollCountByShowId);

		List<Map.Entry<String, Integer>> actualPrerollCounts = new ArrayList<>(prerollCountByShowId.entrySet());
		actualPrerollCounts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

		// Print if assertion passes
		for (Map.Entry<String, Integer> entry : actualPrerollCounts) {
			String showId = entry.getKey();
			Integer actualCount = entry.getValue();
			System.out.println("ShowId: " + showId + " preroll count: " + actualCount);
		}

	}
}
