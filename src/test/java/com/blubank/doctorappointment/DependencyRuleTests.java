package com.blubank.doctorappointment;

import com.blubank.doctorappointment.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

	@Test
	void validateRegistrationContextArchitecture() {
		HexagonalArchitecture.basePackage("com.blubank.doctorappointment")

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
						.importPackages("com.blubank.doctorappointment.."));
	}

	@Test
	void domainModelDoesNotDependOnOutside() {
		noClasses()
				.that()
				.resideInAPackage("com.blubank.doctorappointment.application.domain.model..")
				.should()
				.dependOnClassesThat()
				.resideOutsideOfPackages(
						"com.blubank.doctorappointment.application.domain.model..",
						"lombok..",
						"java..",
						"com.blubank.doctorappointment.common.validation..",
						"jakarta.validation..",
						"com.fasterxml.jackson.annotation.."
				)
				.check(new ClassFileImporter()
						.importPackages("com.blubank.doctorappointment.."));
	}

}
