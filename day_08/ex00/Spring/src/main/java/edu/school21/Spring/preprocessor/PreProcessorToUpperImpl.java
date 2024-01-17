package edu.school21.Spring.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcess(String data) {
        return data.toUpperCase();
    }
}
