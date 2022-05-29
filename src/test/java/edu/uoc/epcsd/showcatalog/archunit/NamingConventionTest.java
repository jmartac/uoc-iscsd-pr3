package edu.uoc.epcsd.showcatalog.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog", importOptions = {ImportOption.DoNotIncludeTests.class})
public class NamingConventionTest {

    @ArchTest
    static ArchRule services_should_be_suffixed =
            classes()
                    .that().resideInAPackage("..domain.service..")
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("ServiceImpl");
}
