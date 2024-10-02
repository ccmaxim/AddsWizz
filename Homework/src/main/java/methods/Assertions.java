package methods;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assertions {
	private static final Logger logger = Logger.getLogger(Assertions.class.getName());

	// Assert Methods
	public static void assertMostPopularShow(String popularShow, int popularDownloads, String actualShow,
			int actualDownloads) {

		StringBuilder warningMessage = new StringBuilder();
		boolean isShowCorrect = actualShow.equals(popularShow);
		boolean isDownloadsCorrect = actualDownloads == popularDownloads;

		if (!isShowCorrect || !isDownloadsCorrect) {
			if (!isShowCorrect) {
				warningMessage
						.append("Expected most popular show to be " + popularShow + ", but got " + actualShow + ". ");
			}

			if (!isDownloadsCorrect) {
				warningMessage.append("Expected number of downloads to be " + popularDownloads + ", but got "
						+ actualDownloads + ".");
			}

			logger.log(Level.WARNING, warningMessage.toString());
			return;
		}

		// Print if valid
		System.out.println("-------------------------------------");
		System.out.println("Most popular show is: " + actualShow);
		System.out.println("Number of downloads is: " + actualDownloads);
		System.out.println("-------------------------------------");

	}

	public static void assertMostUsedDevice(String expectedDeviceType, int expectedDeviceDownloads,
			String actualDeviceType, int actualDownloads) {

		StringBuilder warningMessage = new StringBuilder();
		boolean isDeviceTypeCorrect = actualDeviceType.equals(expectedDeviceType);
		boolean isDownloadsCorrect = actualDownloads == expectedDeviceDownloads;

		if (!isDeviceTypeCorrect || !isDownloadsCorrect) {
			if (!isDeviceTypeCorrect) {
				warningMessage.append("Expected most popular device to be " + expectedDeviceType + ", but got "
						+ actualDeviceType + ". ");
			}

			if (!isDownloadsCorrect) {
				warningMessage.append("Expected number of downloads to be " + expectedDeviceDownloads + ", but got "
						+ actualDownloads + ".");
			}

			logger.log(Level.WARNING, warningMessage.toString());
			return;
		}

		// Print if valid
		System.out.println("Most popular device is: " + actualDeviceType);
		System.out.println("Number of downloads is: " + actualDownloads);
		System.out.println("-------------------------------------");
	}

	public static void assertPrerollCount(List<Map.Entry<String, Integer>> actualEntries,
			Map<String, Integer> expectedCounts) {

		StringBuilder warningMessage = new StringBuilder();
		boolean hasWarnings = false;

		for (Map.Entry<String, Integer> entry : actualEntries) {
			String showId = entry.getKey();
			Integer actualCount = entry.getValue();
			Integer expectedCount = expectedCounts.get(showId);

			if (expectedCount != null && !actualCount.equals(expectedCount)) {
				warningMessage.append("Expected preroll opportunity number for \"" + showId + "\" to be "
						+ expectedCount + ", but got " + actualCount + ". ");
				hasWarnings = true;
			}
		}

		// Print if valid
		if (hasWarnings == false) {
			for (Map.Entry<String, Integer> entry : actualEntries) {
				String showId = entry.getKey();
				Integer actualCount = entry.getValue();
				System.out.println("ShowId: " + showId + " preroll count: " + actualCount);
			}
		} else {
			logger.log(Level.WARNING, warningMessage.toString());
		}
	}

}
