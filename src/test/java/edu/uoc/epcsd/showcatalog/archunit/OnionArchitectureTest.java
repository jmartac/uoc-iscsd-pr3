package edu.uoc.epcsd.showcatalog.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "edu.uoc.epcsd.showcatalog", importOptions = {ImportOption.DoNotIncludeTests.class})
public class OnionArchitectureTest {

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..domain.service..")
            .applicationServices("..edu.uoc.epcsd.showcatalog..")
            .adapter("persistence", "..infrastructure.repository.jpa..")
            .adapter("rest", "..application.rest..");

}
