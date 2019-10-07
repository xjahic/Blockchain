class Block {
    private String hash;
    private String previousHash;
    private String transaction;
    // Nonce is the number that blockchain miners are solving for
    private long nonce;

    Block(String transaction, String previousHash, String hash, long nonce) {
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.hash = hash;
        this.nonce = nonce;
    }

    String getHash() {
        return hash;
    }

    String getPreviousHash() {
        return previousHash;
    }

    String getTransaction() {
        return transaction;
    }

    long getNonce() {
        return nonce;
    }
}
