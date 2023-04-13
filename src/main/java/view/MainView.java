package view;

import org.json.simple.JSONArray;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

public class MainView {
    public static void render(JSONArray results) throws IOException {
        // Configure Thymeleaf template engine
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Create Thymeleaf context with the Java object
        Context context = new Context();
        context.setVariable("results", results);

        // Render the Thymeleaf template with the context
        String htmlOutput = templateEngine.process("index", context);

        FileWriter fileWriter = new FileWriter("result.html");
        fileWriter.write(htmlOutput);
        fileWriter.close();
    }
}
