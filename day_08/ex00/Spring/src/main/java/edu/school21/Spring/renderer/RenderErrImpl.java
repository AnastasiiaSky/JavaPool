package edu.school21.Spring.renderer;

import edu.school21.Spring.preprocessor.PreProcessor;

public class RenderErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RenderErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void showData(String data) {
        System.err.println(preProcessor.preProcess(data));
    }
}
