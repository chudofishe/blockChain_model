package blockchain;


import java.io.*;

public class Block implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Long timestamp;
    private String hash;
    private String hashPrev;
    private int magicNumber;
    private Long generationTime;
    private Long minedBy;


    public Block(int id, Long timestamp, String hash, String hashPrev, Long generationTime, int magicNumber, Long minedBy) {
        this.id = id;
        this.timestamp = timestamp;
        this.hash = hash;
        this.hashPrev = hashPrev;
        this.generationTime = generationTime;
        this.magicNumber = magicNumber;
        this.minedBy = minedBy;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return this.hash;
    }

    public String getHashPrev() {return this.hashPrev;}

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setHashPrev(String hashPrev) {this.hashPrev = hashPrev;}

    public Long getGenerationTime() {
        return this.generationTime;
    }

    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }



    @Override
    public String toString() {
        return "Block:" + "\n" +
                "Created by miner # " + minedBy + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block:\n" + hashPrev + "\n" +
                "Hash of the block:\n" + hash + "\n" +
                "Block was generating for " + generationTime + " seconds";
    }
}
