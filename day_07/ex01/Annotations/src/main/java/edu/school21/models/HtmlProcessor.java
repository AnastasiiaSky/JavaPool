package edu.school21.models;

import edu.school21.annotations.HtmlForm;
import edu.school21.annotations.HtmlInput;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("edu.school21.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Element> annotationsList = new ArrayList<>();
        annotationsList.addAll(roundEnv.getElementsAnnotatedWith(HtmlForm.class));
        annotationsList.addAll(roundEnv.getElementsAnnotatedWith(HtmlInput.class));
        if (!annotationsList.isEmpty()) {
            try {
                workingWithData(annotationsList);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return true;
    }

    public void workingWithData(List<Element> annotationsList) throws IOException {
        HtmlForm htmlForm = annotationsList.get(0).getAnnotation(HtmlForm.class);
        FileObject htmlFile = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", htmlForm.fileName());
        PrintWriter printWriter = new PrintWriter(htmlFile.openWriter());
        printWriter.printf("<form action = \"%s\" method = \"%s\">%n", htmlForm.action(), htmlForm.method());
        annotationsList.remove(0);
        for (Element element : annotationsList) {
            printWriter.printf("\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">%n",
                    element.getAnnotation(HtmlInput.class).type(),
                    element.getAnnotation(HtmlInput.class).name(),
                    element.getAnnotation(HtmlInput.class).placeholder());
        }
        printWriter.print("\t<input type = \"submit\" value = \"Send\">\n</form>\n");
        printWriter.close();
    }
}

