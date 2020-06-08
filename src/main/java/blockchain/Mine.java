package blockchain;

import java.security.MessageDigest;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Mine {

    private final static int POOL_SIZE = 20;
    private static volatile LinkedList<Block> blockChain = new LinkedList<>();
    private static volatile StringBuilder zeroes = new StringBuilder();

    public static void mineBlocks () throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

        for (int i = 0; i < 40; i++) {
            if (blockChain.size() == 0) {
                blockChain.add(generateBlock(1,"0", zeroes.toString(), Thread.currentThread().getId()));
                Block block = blockChain.peekLast();
                System.out.println(block.toString());
                zeroes.append("0");
                System.out.println("N was increased to " + zeroes.length());
            }
            executor.submit(() -> {
                Block newBlock = generateBlock(0, blockChain.peekLast().getHash(), zeroes.toString(), Thread.currentThread().getId());
                submitBlock(newBlock);
            });
        }

        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.MILLISECONDS);
    }

    private static Block generateBlock(int id, String prevHash, String zeroes, Long threadNum) {
        Long timeStamp = new Date().getTime();
        int magicNumber;
        String hash;
        String input = id + prevHash + timeStamp;
        do {
            magicNumber = new Random().nextInt();
            hash = applySha256(input, magicNumber);
        }
        while (!hash.startsWith(zeroes));
        long generationTime = new Date().getTime();
        generationTime = generationTime - timeStamp;
        return new Block(id, timeStamp, hash, prevHash, generationTime, magicNumber, threadNum);
    }

    private static String applySha256(String input, int magicNumber){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            String randomHash = input + magicNumber;
            byte[] hash = digest.digest(randomHash.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validateBlock(Block prevBlock, Block currBlock, String zeroes) {

        if (!currBlock.getHash().startsWith(zeroes)) {
            return false;
        } else return currBlock.getHashPrev().equals(prevBlock.getHash());
    }

    private static synchronized void submitBlock (Block block) {

        if (validateBlock(blockChain.peekLast(), block, zeroes.toString())) {
            block.setId(blockChain.size() + 1);
            blockChain.add(block);

            System.out.println("\n" + block.toString());
            if (block.getGenerationTime() < 60) {
                zeroes.append("0");
                System.out.println("N was increased to " + zeroes.length());
            } else {
                System.out.println("N stays the same");
            }
        }
    }

}
