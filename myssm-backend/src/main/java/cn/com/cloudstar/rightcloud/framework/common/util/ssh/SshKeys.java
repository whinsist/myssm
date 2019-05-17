/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */
package cn.com.cloudstar.rightcloud.framework.common.util.ssh;

import com.google.common.annotations.Beta;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;

import static com.google.common.base.Joiner.on;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Splitter.fixedLength;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.size;
import static com.google.common.io.BaseEncoding.base16;
import static com.google.common.io.BaseEncoding.base64;

/**
 * Utilities for ssh key pairs
 *
 * @author Chaohong.Mao
 * @see <a href="http://stackoverflow.com/questions/3706177/how-to-generate-ssh-compatible-id-rsa-pub-from-java" />
 */
@Beta
public class SshKeys {

    public static final String PRIVATE_PKCS1_MARKER = "-----BEGIN RSA PRIVATE KEY-----";
    public static final String PRIVATE_PKCS8_MARKER = "-----BEGIN PRIVATE KEY-----";
    public static final String CERTIFICATE_X509_MARKER = "-----BEGIN CERTIFICATE-----";
    public static final String PUBLIC_X509_MARKER = "-----BEGIN PUBLIC KEY-----";
    public static final String PUBLIC_PKCS1_MARKER = "-----BEGIN RSA PUBLIC KEY-----";
    public static final String PROC_TYPE_ENCRYPTED = "Proc-Type: 4,ENCRYPTED";

    /**
     * return a "public" -> rsa public key, "private" -> its corresponding private key
     *
     * @return the map
     */
    public static Map<String, String> generate() {
        try {
            return generate(KeyPairGenerator.getInstance("RSA"));
        } catch (NoSuchAlgorithmException e) {
            throw propagate(e);
        }
    }

    /**
     * Generate map.
     *
     * @param generator the generator
     * @return the map
     */
    public static Map<String, String> generate(KeyPairGenerator generator) {
        KeyPair pair = generateRsaKeyPair(generator);
        Builder<String, String> builder = ImmutableMap.builder();
        builder.put("public", encodeAsOpenSSH(RSAPublicKey.class.cast(pair.getPublic())));
        builder.put("private", Pems.pem(RSAPrivateKey.class.cast(pair.getPrivate())));
        return builder.build();
    }

    /**
     * Generate rsa key pair key pair.
     *
     * @param generator to generate RSA key pairs
     * @return new 2048 bit keyPair
     */
    public static KeyPair generateRsaKeyPair(KeyPairGenerator generator) {
        generator.initialize(2048);
        return generator.genKeyPair();
    }

    /**
     * Encode as open ssh string.
     *
     * @param key the key
     * @return the string
     */
    public static String encodeAsOpenSSH(RSAPublicKey key) {
        byte[] keyBlob = keyBlob(key.getPublicExponent(), key.getModulus());
        return "ssh-rsa " + base64().encode(keyBlob);
    }

    /**
     * Key blob byte [ ].
     *
     * @param publicExponent the public exponent
     * @param modulus        the modulus
     * @return the byte [ ]
     */
    private static byte[] keyBlob(BigInteger publicExponent, BigInteger modulus) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            writeLengthFirst("ssh-rsa".getBytes(), out);
            writeLengthFirst(publicExponent.toByteArray(), out);
            writeLengthFirst(modulus.toByteArray(), out);
            return out.toByteArray();
        } catch (IOException e) {
            throw propagate(e);
        }
    }

    /**
     * Write length first.
     *
     * @param array the array
     * @param out   the out
     * @throws IOException the io exception
     */
// http://www.ietf.org/rfc/rfc4253.txt
    private static void writeLengthFirst(byte[] array, ByteArrayOutputStream out) throws IOException {
        out.write((array.length >>> 24) & 0xFF);
        out.write((array.length >>> 16) & 0xFF);
        out.write((array.length >>> 8) & 0xFF);
        out.write((array.length >>> 0) & 0xFF);
        if (array.length == 1 && array[0] == (byte) 0x00) {
            out.write(new byte[0]);
        } else {
            out.write(array);
        }
    }

    /**
     * Private key matches public key boolean.
     *
     * @param privateKey the private key
     * @param publicKey  the public key
     * @return true if the keypairs match
     */
    public static boolean privateKeyMatchesPublicKey(RSAPrivateCrtKeySpec privateKey, RSAPublicKeySpec publicKey) {
        return privateKey.getPublicExponent().equals(publicKey.getPublicExponent()) &&
                privateKey.getModulus().equals(publicKey.getModulus());
    }

    /**
     * Private key has fingerprint boolean.
     *
     * @param privateKey  the private key
     * @param fingerprint the fingerprint
     * @return true if the keypair has the same fingerprint as supplied
     */
    public static boolean privateKeyHasFingerprint(RSAPrivateCrtKeySpec privateKey, String fingerprint) {
        return fingerprint(privateKey.getPublicExponent(), privateKey.getModulus()).equals(fingerprint);
    }

    /**
     * Create a fingerprint per the following <a
     * href="http://tools.ietf.org/html/draft-friedl-secsh-fingerprint-00" >spec</a>
     *
     * @param publicExponent the public exponent
     * @param modulus        the modulus
     * @return hex fingerprint ex. {@code 2b:a9:62:95:5b:8b:1d:61:e0:92:f7:03:10:e9:db:d9}
     */
    public static String fingerprint(BigInteger publicExponent, BigInteger modulus) {
        byte[] keyBlob = keyBlob(publicExponent, modulus);
        return hexColonDelimited(Hashing.md5().hashBytes(keyBlob));
    }

    /**
     * Hex colon delimited string.
     *
     * @param hc the hc
     * @return the string
     */
    private static String hexColonDelimited(HashCode hc) {
        return on(':').join(fixedLength(2).split(base16().lowerCase().encode(hc.asBytes())));
    }

    /**
     * Fingerprint public key string.
     *
     * @param publicKeyOpenSSH RSA public key in OpenSSH format
     * @return fingerprint ex. {@code 2b:a9:62:95:5b:8b:1d:61:e0:92:f7:03:10:e9:db:d9}
     */
    public static String fingerprintPublicKey(String publicKeyOpenSSH) {
        RSAPublicKeySpec publicKeySpec = publicKeySpecFromOpenSSH(publicKeyOpenSSH);
        return fingerprint(publicKeySpec.getPublicExponent(), publicKeySpec.getModulus());
    }

    /**
     * Executes on the string which was OpenSSH
     * Base64 Encoded {@code id_rsa.pub}
     *
     * @param idRsaPub formatted {@code ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAB...}
     * @return the rsa public key spec
     */
    public static RSAPublicKeySpec publicKeySpecFromOpenSSH(String idRsaPub) {
        try {
            return publicKeySpecFromOpenSSH(ByteSource.wrap(idRsaPub.getBytes(Charsets.UTF_8)));
        } catch (IOException e) {
            throw propagate(e);
        }
    }

    /**
     * Returns {@link RSAPublicKeySpec} which was OpenSSH Base64 Encoded {@code id_rsa.pub}
     *
     * @param supplier the input stream factory, formatted {@code ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAB...}
     * @return the {@link RSAPublicKeySpec} which was OpenSSH Base64 Encoded {@code id_rsa.pub}
     * @throws IOException if an I/O error occurs
     */
    public static RSAPublicKeySpec publicKeySpecFromOpenSSH(ByteSource supplier) throws IOException {
        InputStream stream = supplier.openStream();
        Iterable<String> parts = Splitter.on(' ').split(toStringAndClose(stream).trim());
        checkArgument(size(parts) >= 2 && "ssh-rsa".equals(get(parts, 0)), "bad format, should be: ssh-rsa AAAAB3...");
        stream = new ByteArrayInputStream(base64().decode(get(parts, 1)));
        String marker = new String(readLengthFirst(stream));
        checkArgument("ssh-rsa".equals(marker), "looking for marker ssh-rsa but got %s", marker);
        BigInteger publicExponent = new BigInteger(readLengthFirst(stream));
        BigInteger modulus = new BigInteger(readLengthFirst(stream));
        return new RSAPublicKeySpec(modulus, publicExponent);
    }

    public static String toStringAndClose(InputStream input) throws IOException {
        checkNotNull(input, "input");
        try {
            return CharStreams.toString(new InputStreamReader(input, Charsets.UTF_8));
        } finally {
            closeQuietly(input);
        }
    }

    /**
     * Read length first byte [ ].
     *
     * @param in the in
     * @return the byte [ ]
     * @throws IOException the io exception
     */
// http://www.ietf.org/rfc/rfc4253.txt
    private static byte[] readLengthFirst(InputStream in) throws IOException {
        int byte1 = in.read();
        int byte2 = in.read();
        int byte3 = in.read();
        int byte4 = in.read();
        int length = (byte1 << 24) + (byte2 << 16) + (byte3 << 8) + (byte4 << 0);
        byte[] val = new byte[length];
        ByteStreams.readFully(in, val);
        return val;
    }

    /**
     * Close quietly.
     *
     * @param closeable the closeable
     */
    private static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Private key has sha 1 boolean.
     *
     * @param privateKey  the private key
     * @param fingerprint the fingerprint
     * @return true if the keypair has the same SHA1 fingerprint as supplied
     */
    public static boolean privateKeyHasSha1(RSAPrivateCrtKeySpec privateKey, String fingerprint) {
        return sha1(privateKey).equals(fingerprint);
    }

    /**
     * Create a SHA-1 digest of the DER encoded private key.
     *
     * @param privateKey the private key
     * @return hex sha1HexColonDelimited ex. {@code 2b:a9:62:95:5b:8b:1d:61:e0:92:f7:03:10:e9:db:d9}
     */
    public static String sha1(RSAPrivateCrtKeySpec privateKey) {
        try {
            byte[] encodedKey = KeyFactory.getInstance("RSA").generatePrivate(privateKey).getEncoded();
            return hexColonDelimited(Hashing.sha1().hashBytes(encodedKey));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw propagate(e);
        }
    }

    /**
     * Public key has fingerprint boolean.
     *
     * @param publicKeyOpenSSH RSA public key in OpenSSH format
     * @param fingerprint      ex. {@code 2b:a9:62:95:5b:8b:1d:61:e0:92:f7:03:10:e9:db:d9}
     * @return true if the keypair has the same fingerprint as supplied
     */
    public static boolean publicKeyHasFingerprint(String publicKeyOpenSSH, String fingerprint) {
        return publicKeyHasFingerprint(publicKeySpecFromOpenSSH(publicKeyOpenSSH), fingerprint);
    }

    /**
     * Public key has fingerprint boolean.
     *
     * @param publicKey   the public key
     * @param fingerprint the fingerprint
     * @return true if the keypair has the same fingerprint as supplied
     */
    public static boolean publicKeyHasFingerprint(RSAPublicKeySpec publicKey, String fingerprint) {
        return fingerprint(publicKey.getPublicExponent(), publicKey.getModulus()).equals(fingerprint);
    }


//   public static void main(String args[]) {
//      KeyPairGenerator keyGen = null;
//      SecureRandom random = null;
//      try {
//         keyGen = KeyPairGenerator.getInstance("RSA");
//         random = SecureRandom.getInstance("SHA1PRNG", "SUN");
//      } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//         e.printStackTrace();
//      }
//      Map<String, String> generate = SshKeys.generate(keyGen, random);
//      for (String s : generate.keySet()) {
//         System.out.println(s + " : \n" + generate.get(s));
//      }
//      System.out.println(fingerprintPublicKey(generate.get("public")));
//
//   }
}
