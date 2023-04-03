package ma.enset.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD}) //be able to put on element type "constructor" & element type "field" & element type "method"
@Retention(RetentionPolicy.RUNTIME) //conservation, maintien, rétention استبقاء

public @interface InjectMe {


}
