package constants;

import java.util.HashMap;
import java.util.Map;

public class AssertData {
	//Expected data
    public static Map<String, Integer> getExpectedPrerollCounts() {
        Map<String, Integer> expectedPrerollCounts = new HashMap<>();
        expectedPrerollCounts.put("Stuff You Should Know", 40);
        expectedPrerollCounts.put("Who Trolled Amber", 40);
        expectedPrerollCounts.put("Crime Junkie", 30);
        expectedPrerollCounts.put("The Joe Rogan Experience", 10);
        return expectedPrerollCounts;
    }
    
    public static String getPopularShow() {
        return "Who Trolled Amber";
    }

    public static int getPopularShowDownloads() {
        return 24;
    }

    public static String getMostUsedDevice() {
        return "mobiles & tablets";
    }

    public static int getNumberOfDownloads() {
        return 60;
    }
}
