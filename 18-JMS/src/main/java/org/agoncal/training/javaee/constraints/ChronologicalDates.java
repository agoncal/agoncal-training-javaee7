package org.agoncal.training.javaee.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ChronologicalDatesValidator.class)
public @interface ChronologicalDates {

    String message() default "{org.agoncal.training.javaee.constraints.ChronologicalDates.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
