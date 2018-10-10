package ru.matevosyan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig is configuration class that describe how to where to find beans and construct them.
 */
@Configuration
@ComponentScan(basePackages = "ru.matevosyan.models.javaSpecific")
public class AppConfig {

}