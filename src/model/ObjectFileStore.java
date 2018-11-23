package model;

import java.io.*;

public class ObjectFileStore {
    public static void storeObject(CardPack cardPack, String name, String dir){

        OutputStream ops = null;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream(dir + name + ".txt");
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

    public static CardPack readObjects(File name){

        InputStream fileIs = null;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream(name);
            objIs = new ObjectInputStream(fileIs);
            return (CardPack) objIs.readObject();
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

