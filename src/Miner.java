class Miner {

    private int difficulty;
    private String target;

    Miner(int difficulty) {
        this.difficulty = difficulty;

        // Create a string of '0' of difficulty size
        this.target = new String(new char[difficulty]).replace('\0', '0');
    }

    Block mineBlock(String transaction, String previousHash) {
        System.out.println("Mining block... ");

        long nonce = 0;
        String hash = Hasher.calculateHash(previousHash, transaction, nonce);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = Hasher.calculateHash(previousHash, transaction, nonce);
        }
        System.out.println("Yuhuu. I mined a block at " + nonce + " attempt! Hash: " + hash);
        System.out.println();

        return new Block(transaction, previousHash, hash, nonce);
    }
}
