import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger("Calculator.class");

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        logger.info("logger_info_level");
        logger.debug("logger_debug_level");
        logger.warn("logger_warn_level");
        logger.error("logger_error_level");
        logger.trace("logger_trace_level");
    }

}