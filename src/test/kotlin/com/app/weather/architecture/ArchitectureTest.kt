package com.app.weather.architecture

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController

class ArchitectureTest {

   private val weatherAppClasses: JavaClasses = ClassFileImporter()
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
        .importPackages("/com/app/weather")

    @Nested
    inner class General {

        @Test
        fun `layers access`() {
            layeredArchitecture()
                .layer("Rest").definedBy("..rest..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")
                .layer("Component").definedBy("..component..")
                .whereLayer("Rest").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Rest")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .whereLayer("Component").mayOnlyBeAccessedByLayers("Service")
                .check(weatherAppClasses)
        }
    }

    @Nested
    inner class RestPackage {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..rest..")
                .and()
                .areAnnotatedWith(RestController::class.java)
                .should(haveSufix("Rest"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..rest..")
                .should(haveSufix("Rest"))
                .check(weatherAppClasses)
        }

        @Test
        fun `Rest should only be accessed by the Service`(){
            classes()
                .that().resideInAPackage("..rest..")
                .should().onlyBeAccessed().byAnyPackage("..rest..","..service..")
                .check(weatherAppClasses)

        }
    }

    @Nested
    inner class ServicePackage {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..service..")
                .and()
                .areAnnotatedWith(Service::class.java)
                .should(haveSufix("Service"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..service..")
                .should(haveSufix("Service"))
                .check(weatherAppClasses)
        }

        @Test
        fun `Service should only be accessed be Rest or Services`(){
            classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..service..","..rest..")
                .check(weatherAppClasses)

        }
    }

    @Nested
    inner class PackageRepository {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..repository..")
                .and()
                .areAnnotatedWith(Repository::class.java)
                .should(haveSufix("Repository"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..repository..")
                .should(haveSufix("Repository"))
                .check(weatherAppClasses)
        }

        @Test
        fun `Repository can only be accessed be Service`(){
            classes()
                .that().resideInAPackage("..repository..")
                .should().onlyBeAccessed().byAnyPackage("..repository..","..service..")
                .check(weatherAppClasses)

        }
    }

    @Nested
    inner class PackageDto {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..dto..")
                .should(haveSufix("Dto"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..dto..")
                .should(haveSufix("Dto"))
                .check(weatherAppClasses)
        }
    }

    @Nested
    inner class PackageVo {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..vo..")
                .should(haveSufix("Vo"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..vo..")
                .should(haveSufix("Vo"))
                .check(weatherAppClasses)
        }
    }

    @Nested
    inner class PackageExceptions {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..exceptions..")
                .should(haveSufix("Exception"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..exceptions..")
                .should(haveSufix("Exception"))
                .check(weatherAppClasses)
        }
    }

    @Nested
    inner class PackageDeserializers {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..deserializers..")
                .should(haveSufix("Deserializer"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..deserializers..")
                .should(haveSufix("Deserializer"))
                .check(weatherAppClasses)
        }
    }

    @Nested
    inner class PackageExtensions {

        @Test
        fun `naming convention`() {
            classes()
                .that()
                .resideInAPackage("..extensions..")
                .should(haveSufix("Extensions"))
                .check(weatherAppClasses)

            noClasses()
                .that()
                .resideOutsideOfPackage("..extensions..")
                .should(haveSufix("Extensions"))
                .check(weatherAppClasses)
        }
    }

    private fun haveSufix(sufix: String): ArchCondition<JavaClass> {
        return object : ArchCondition<JavaClass>("have sufix $sufix") {

            override fun check(klass: JavaClass, events: ConditionEvents) {
                if (klass.simpleName.endsWith(sufix) || klass.simpleName.endsWith("${sufix}Kt") || klass.fullName.contains(
                        "$sufix$"
                    )
                ) {
                    events.add(SimpleConditionEvent.satisfied(klass, "Class ${klass.fullName} has sufix $sufix"))
                } else {
                    events.add(SimpleConditionEvent.violated(klass, "Class ${klass.fullName} shoud have sufix $sufix"))
                }
            }
        }
    }
}
