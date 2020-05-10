/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper;

import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.Map.Entry;


/**
 * @author ActiveEon Team
 * @since 10/01/17
 */
public class SigningSupport {

    private TimestampGenerator timestampGenerator = new DefaultTimestampGenerator();

    private static final BitSet UNRESERVED;

    static {
        BitSet alpha = new BitSet(256);
        for (int i = 'a'; i <= 'z'; i++) {
            alpha.set(i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            alpha.set(i);
        }
        BitSet digit = new BitSet(256);
        for (int i = '0'; i <= '9'; i++) {
            digit.set(i);
        }
        BitSet unreserved = new BitSet(256);
        unreserved.or(alpha);
        unreserved.or(digit);
        unreserved.set('-');
        unreserved.set('.');
        unreserved.set('_');
        unreserved.set('~');
        UNRESERVED = unreserved;
    }

    public String buildAuthorizationHeaderValueTest(Map<String, String> oauthParameters, String consumerSecret, String tokenSecret) {
        StringBuilder header = new StringBuilder();
        header.append("OAuth ");
        for (Entry<String, String> entry : oauthParameters.entrySet()) {
            header.append(oauthEncode(entry.getKey()))
                  .append("=\"")
                  .append(oauthEncode(entry.getValue()))
                  .append("\", ");
        }
        StringBuilder signature = new StringBuilder().append(consumerSecret).append('&').append(tokenSecret);
        header.append(oauthEncode("oauth_signature"))
              .append("=\"")
              .append(oauthEncode(signature.toString()))
              .append("\"");
        return header.toString();
    }
    private static String oauthEncode(String param) {
        try {
            // See http://tools.ietf.org/html/rfc5849#section-3.6
            byte[] bytes = encode(param.getBytes("UTF-8"), UNRESERVED);
            return new String(bytes, "US-ASCII");
        } catch (Exception shouldntHappen) {
            throw new IllegalStateException(shouldntHappen);
        }
    }
    private static byte[] encode(byte[] source, BitSet notEncoded) {
        Assert.notNull(source, "'source' must not be null");
        ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length * 2);
        for (int i = 0; i < source.length; i++) {
            int b = source[i];
            if (b < 0) {
                b += 256;
            }
            if (notEncoded.get(b)) {
                bos.write(b);
            } else {
                bos.write('%');
                char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF, 16));
                char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF, 16));
                bos.write(hex1);
                bos.write(hex2);
            }
        }
        return bos.toByteArray();
    }


    static interface TimestampGenerator {

        long generateTimestamp();

        long generateNonce(long timestamp);

    }

    private static class DefaultTimestampGenerator implements TimestampGenerator {

        public long generateTimestamp() {
            return System.currentTimeMillis() / 1000;
        }

        public long generateNonce(long timestamp) {
            return timestamp + RANDOM.nextInt();
        }

        static final Random RANDOM = new Random();

    }



}
