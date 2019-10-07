import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Main {

    private static ArrayList<Block> blockchain = new ArrayList<>();
    private final static int difficulty = 5;

    public static void main(String[] args) {
        Miner miner = new Miner(difficulty);

        // 1. Create transaction
        String firstTransaction = "Thomas pays Lucy 5 CC";
        // 2. Miner listens to this transaction and mines block
        Block firstBlock = miner.mineBlock(firstTransaction, "0");
        // 3. Block is added to the blockchain
        blockchain.add(firstBlock);

        // 1. Create transaction
        String secondTransaction = "John pays Paul 2 CC";
        // 2. Miner listens to this transaction and mines block
        Block secondBlock = miner.mineBlock(secondTransaction, firstBlock.getHash());
        // 3. Block is added to the blockchain
        blockchain.add(secondBlock);

        // 1. Create transaction
        String thirdTransaction = "Paul pays Thomas 4 CC";
        // 2. Miner listens to this transaction and mines block
        Block thirdBlock = miner.mineBlock(thirdTransaction, secondBlock.getHash());
        // 3. Block is added to the blockchain
        blockchain.add(thirdBlock);

        System.out.println("Is our blockchain valid?: " + isBlockchainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Blockchain:");
        System.out.println(blockchainJson);
    }

    private static Boolean isBlockchainValid() {

        if (blockchain.size() > 0) {
            for (int i = 0; i < blockchain.size(); i++) {

                Block block = blockchain.get(i);

                // has valid hash
                String expectedHash = Hasher.calculateHash(block.getPreviousHash(), block.getTransaction(), block.getNonce());
                if (!expectedHash.equals(block.getHash())) {
                    System.out.println("Block has invalid hash");
                    return false;
                }

                // block was mined/solved
                String hashTarget = new String(new char[difficulty]).replace('\0', '0');
                if (!block.getHash().substring(0, difficulty).equals(hashTarget)) {
                    System.out.println("Block wasn't mined");
                    return false;
                }

                // For every block except the first compare previousHash
                if (i > 0) {
                    Block previousBlock = blockchain.get(i - 1);
                    // Previous hash is equal to actual previous hash
                    if (!block.getPreviousHash().equals(previousBlock.getHash())) {
                        System.out.println("Block has invalid previous hash");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Empty blockchain");
            return true;
        }
        return true;
    }


}
