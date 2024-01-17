package edu.school21.Spring.preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String preProcess(String data) {
        return data.toLowerCase();
    }
}
