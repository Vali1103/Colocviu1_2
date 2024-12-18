package ro.pub.cs.systems.eim.Colocviu1_2;

public class Constants {
    final public static String ALL_TERMS = "allTerms";
    final public static String LEFT_COUNT = "leftCount";
    final public static String RIGHT_COUNT = "rightCount";

    final public static String NUMBER_OF_CLICKS = "numberOfClicks";
    final public static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    final public static String FIRST_NUMBER = "firstNumber";
    final public static String SECOND_NUMBER = "secondNumber";

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.arithmeticmean",
            "ro.pub.cs.systems.eim.practicaltest01.geometricmean"
    };

    final public static int NUMBER_OF_CLICKS_THRESHOLD = 5;
    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;

    final public static String PROCESSING_THREAD_TAG = "[Processing Thread]";

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";

    // Adaugă aceste constante pentru a rezolva eroarea
    final public static String SUM_RESULT = "SUM_RESULT";  // pentru suma calculată
    final public static String SUM_BROADCAST_ACTION = "ro.pub.cs.systems.eim.practicaltest01.SUM_BROADCAST_ACTION"; // acțiunea pentru broadcast
}
