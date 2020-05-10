package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper;

import org.springframework.http.HttpStatus;

/**
 * @author Hong.Wu
 * @date: 16:10 2020/05/02
 */
public class RestErrorUtil {
    public static boolean hasError(HttpStatus statusCode) {
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }
}
