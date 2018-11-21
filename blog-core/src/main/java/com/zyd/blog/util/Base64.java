package com.zyd.blog.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
import org.apache.shiro.codec.CodecSupport;

public class Base64 {
    private static final int CHUNK_SIZE = 76;
    private static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 64;
    private static final int EIGHTBIT = 8;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final int FOURBYTE = 4;
    private static final int SIGN = -128;
    private static final byte PAD = 61;
    private static final byte[] base64Alphabet = new byte[255];
    private static final byte[] lookUpBase64Alphabet = new byte[64];

    public Base64() {
    }

    private static boolean isBase64(byte octect) {
        if (octect == 61) {
            return true;
        } else {
            return octect >= 0 && base64Alphabet[octect] != -1;
        }
    }

    public static boolean isBase64(byte[] arrayOctect) {
        arrayOctect = discardWhitespace(arrayOctect);
        int length = arrayOctect.length;
        if (length == 0) {
            return true;
        } else {
            for(int i = 0; i < length; ++i) {
                if (!isBase64(arrayOctect[i])) {
                    return false;
                }
            }

            return true;
        }
    }

    static byte[] discardWhitespace(byte[] data) {
        byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;
        byte[] packedData = data;
        int len$ = data.length;
        int i$ = 0;

        while(i$ < len$) {
            byte aByte = packedData[i$];
            switch(aByte) {
                default:
                    groomedData[bytesCopied++] = aByte;
                case 9:
                case 10:
                case 13:
                case 32:
                    ++i$;
            }
        }

        packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }

    public static String encodeToString(byte[] bytes) {
        byte[] encoded = encode(bytes);
        return CodecSupport.toString(encoded);
    }

    public static byte[] encodeChunked(byte[] binaryData) {
        return encode(binaryData, true);
    }

    public static byte[] encode(byte[] pArray) {
        return encode(pArray, false);
    }

    public static byte[] encode(byte[] binaryData, boolean isChunked) {
        long binaryDataLength = (long)binaryData.length;
        long lengthDataBits = binaryDataLength * 8L;
        long fewerThan24bits = lengthDataBits % 24L;
        long tripletCount = lengthDataBits / 24L;
        int chunckCount = 0;
        long encodedDataLengthLong;
        if (fewerThan24bits != 0L) {
            encodedDataLengthLong = (tripletCount + 1L) * 4L;
        } else {
            encodedDataLengthLong = tripletCount * 4L;
        }

        if (isChunked) {
            chunckCount = CHUNK_SEPARATOR.length == 0 ? 0 : (int)Math.ceil((double)((float)encodedDataLengthLong / 76.0F));
            encodedDataLengthLong += (long)(chunckCount * CHUNK_SEPARATOR.length);
        }

        if (encodedDataLengthLong > 2147483647L) {
            throw new IllegalArgumentException("Input array too big, output array would be bigger than Integer.MAX_VALUE=2147483647");
        } else {
            int encodedDataLength = (int)encodedDataLengthLong;
            byte[] encodedData = new byte[encodedDataLength];
            int encodedIndex = 0;
            int nextSeparatorIndex = 76;
            int chunksSoFar = 0;

            byte k;
            byte l;
            byte b1;
            byte b2;
            int dataIndex;
            int i;
            byte val1;
            byte val2;
            for(i = 0; (long)i < tripletCount; ++i) {
                dataIndex = i * 3;
                b1 = binaryData[dataIndex];
                b2 = binaryData[dataIndex + 1];
                byte b3 = binaryData[dataIndex + 2];
                l = (byte)(b2 & 15);
                k = (byte)(b1 & 3);
                val1 = (b1 & -128) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 192);
                val2 = (b2 & -128) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 240);
                byte val3 = (b3 & -128) == 0 ? (byte)(b3 >> 6) : (byte)(b3 >> 6 ^ 252);
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | k << 4];
                encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2 | val3];
                encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 63];
                encodedIndex += 4;
                if (isChunked && encodedIndex == nextSeparatorIndex) {
                    System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
                    ++chunksSoFar;
                    nextSeparatorIndex = 76 * (chunksSoFar + 1) + chunksSoFar * CHUNK_SEPARATOR.length;
                    encodedIndex += CHUNK_SEPARATOR.length;
                }
            }

            dataIndex = i * 3;
            if (fewerThan24bits == 8L) {
                b1 = binaryData[dataIndex];
                k = (byte)(b1 & 3);
                val1 = (b1 & -128) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 192);
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
                encodedData[encodedIndex + 2] = 61;
                encodedData[encodedIndex + 3] = 61;
            } else if (fewerThan24bits == 16L) {
                b1 = binaryData[dataIndex];
                b2 = binaryData[dataIndex + 1];
                l = (byte)(b2 & 15);
                k = (byte)(b1 & 3);
                val1 = (b1 & -128) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 192);
                val2 = (b2 & -128) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 240);
                encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
                encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | k << 4];
                encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
                encodedData[encodedIndex + 3] = 61;
            }

            if (isChunked && chunksSoFar < chunckCount) {
                System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
            }

            return encodedData;
        }
    }

    public static String decodeToString(String base64Encoded) {
        byte[] encodedBytes = CodecSupport.toBytes(base64Encoded);
        return decodeToString(encodedBytes);
    }

    public static String decodeToString(byte[] base64Encoded) {
        byte[] decoded = decode(base64Encoded);
        return CodecSupport.toString(decoded);
    }

    public static byte[] decode(String base64Encoded) {
        byte[] bytes = CodecSupport.toBytes(base64Encoded);
        return decode(bytes);
    }

    public static byte[] decode(byte[] base64Data) {
        base64Data = discardNonBase64(base64Data);
        if (base64Data.length == 0) {
            return new byte[0];
        } else {
            int numberQuadruple = base64Data.length / 4;
            int encodedIndex = 0;
            int i = base64Data.length;

            while(base64Data[i - 1] == 61) {
                --i;
                if (i == 0) {
                    return new byte[0];
                }
            }

            byte[] decodedData = new byte[i - numberQuadruple];

            for(i = 0; i < numberQuadruple; ++i) {
                int dataIndex = i * 4;
                byte marker0 = base64Data[dataIndex + 2];
                byte marker1 = base64Data[dataIndex + 3];
                byte b1 = base64Alphabet[base64Data[dataIndex]];
                byte b2 = base64Alphabet[base64Data[dataIndex + 1]];
                byte b3;
                if (marker0 != 61 && marker1 != 61) {
                    b3 = base64Alphabet[marker0];
                    byte b4 = base64Alphabet[marker1];
                    decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                    decodedData[encodedIndex + 1] = (byte)((b2 & 15) << 4 | b3 >> 2 & 15);
                    decodedData[encodedIndex + 2] = (byte)(b3 << 6 | b4);
                } else if (marker0 == 61) {
                    decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                } else {
                    b3 = base64Alphabet[marker0];
                    decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                    decodedData[encodedIndex + 1] = (byte)((b2 & 15) << 4 | b3 >> 2 & 15);
                }

                encodedIndex += 3;
            }

            return decodedData;
        }
    }

    static byte[] discardNonBase64(byte[] data) {
        byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;
        byte[] packedData = data;
        int len$ = data.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte aByte = packedData[i$];
            if (isBase64(aByte)) {
                groomedData[bytesCopied++] = aByte;
            }
        }

        packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }

    static {
        int i;
        for(i = 0; i < 255; ++i) {
            base64Alphabet[i] = -1;
        }

        for(i = 90; i >= 65; --i) {
            base64Alphabet[i] = (byte)(i - 65);
        }

        for(i = 122; i >= 97; --i) {
            base64Alphabet[i] = (byte)(i - 97 + 26);
        }

        for(i = 57; i >= 48; --i) {
            base64Alphabet[i] = (byte)(i - 48 + 52);
        }

        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;

        for(i = 0; i <= 25; ++i) {
            lookUpBase64Alphabet[i] = (byte)(65 + i);
        }

        i = 26;

        int j;
        for(j = 0; i <= 51; ++j) {
            lookUpBase64Alphabet[i] = (byte)(97 + j);
            ++i;
        }

        i = 52;

        for(j = 0; i <= 61; ++j) {
            lookUpBase64Alphabet[i] = (byte)(48 + j);
            ++i;
        }

        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }
}
