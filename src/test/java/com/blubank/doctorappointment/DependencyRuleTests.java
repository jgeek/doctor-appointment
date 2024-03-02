package com.blubank.doctorappointment;

import com.blubank.doctorappointment.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

    private static final String BASE_PACKAGE = "com.blubank.doctorappointment";

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.basePackage(BASE_PACKAGE)

                .withDomainLayer("application.domain")

                .withAdaptersLayer("adapter")
                .incoming("in.web")
                .outgoing("out.persistence")
                .and()

                .withApplicationLayer("application")
                .incomingPorts("port.in")
                .outgoingPorts("port.out")
                .and()

                .withConfiguration("configuration")
                .check(new ClassFileImporter()
                        .importPackages(BASE_PACKAGE + ".."));
    }

    @Test
    void domainModelDoesNotDependOnOutside() {
        noClasses()
                .that()
                .resideInAPackage(BASE_PACKAGE + ".application.domain.model..")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(
                        BASE_PACKAGE + ".application.domain.model..",
                        "lombok..",
                        "java..",
                        BASE_PACKAGE + ".common.validation..",
                        "jakarta.validation..",
                        "com.fasterxml.jackson.annotation.."
                )
                .check(new ClassFileImporter()
                        .importPackages(BASE_PACKAGE + ".."));
    }

}
