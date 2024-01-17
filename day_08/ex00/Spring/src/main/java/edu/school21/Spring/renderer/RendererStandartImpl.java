package edu.school21.Spring.renderer;

import edu.school21.Spring.preprocessor.PreProcessor;

public class RendererStandartImpl implements Renderer {

    private PreProcessor preProcessor;

    public RendererStandartImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void showData(String data) {
        System.out.println(preProcessor.preProcess(data));

    }
}
