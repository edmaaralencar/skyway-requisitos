package com.cesar.trabalho.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Caminho para os arquivos .feature
        glue = "com.cesar.trabalho.steps", // Pacote onde os Step Definitions estão
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Opções de output
)
public class RunCucumberTest {
}