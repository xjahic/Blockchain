import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

class Hasher {
    static String calculateHash(String previousHash, String transaction, long nonce) {
        final String toHash = previousHash + transaction + nonce;
        return Hashing.sha256()
            .hashString(toHash, StandardCharsets.UTF_8)
            .toString();
    }
}
