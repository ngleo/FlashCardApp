package model;

import java.io.*;

public class ObjectFileStore {
    public static void storeObject(CardPack cardPack, String name){

        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("data/" + name);
            objOps = new ObjectOutputStream(ops);
            objOps.writeObject(cardPack);
            objOps.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ex){

            }
        }

    }

    public static CardPack readObjects(){

        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream("data/DataFile.txt");
            objIs = new ObjectInputStream(fileIs);
            CardPack cardPack = (CardPack) objIs.readObject();
            return cardPack;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ex){

            }
        }
        return null;
    }
}

