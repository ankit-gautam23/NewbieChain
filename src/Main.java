import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 2;

    public static void main(String[] args) {

        blockchain.add(new Block("First Block", "0"));
        System.out.println("mining first block...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second Block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("mining second block");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third Block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("mining third block");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("Blockchain Validity: " +isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("The blockChain : ");
        System.out.println(blockchainJson);

        //First Block is known as genesis block
        /*
        Block genesisBlock = new Block("Very First Block", "0");
        System.out.println("First Block Hash Value : " + genesisBlock.hash);

        Block secondBlock = new Block("Second Block", genesisBlock.hash);
        System.out.println("Second Block Hash Value : " + secondBlock.hash);

        Block thirdBlock = new Block("Third Block", secondBlock.hash);
        System.out.println("Third Bloch Hash Value : "+thirdBlock.hash);

         */
    }

    public static Boolean isChainValid(){
        Block currentBlock;
        Block prevBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        for(int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes are not equal");
                return false;
            }
            if(!prevBlock.hash.equals(currentBlock.prevHash)){
                System.out.println("Previous Hashes are not Equal");
                return false;
            }
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
                System.out.println("Mining Failed");
                return false;
            }
        }
        return true;
    }
}