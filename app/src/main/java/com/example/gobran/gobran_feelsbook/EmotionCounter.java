package com.example.gobran.gobran_feelsbook;

import java.util.ArrayList;

public class EmotionCounter {
    private ArrayList<Integer> counts  = new ArrayList<Integer>();

    private PrimitiveData primitiveData;


    public EmotionCounter(PrimitiveData pData) {
        primitiveData = pData;
        for(EmotionType type: EmotionType.values()) {
            counts.add(primitiveData.getData(type.getStringForm()));
        }
    }

    public void updateCount(EmotionType type, int quantity) {
        int newCount = retrieveCount(type) + quantity;
        counts.set(type.getNumId(),newCount);
        primitiveData.saveData(type.getStringForm(),newCount);
    }

    public int retrieveCount(EmotionType type) {
        return counts.get(type.getNumId());
    }

}
