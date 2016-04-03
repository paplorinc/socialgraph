package pap.lorinc.socialgraph.utils;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.stream.Stream;

@UtilityClass
public class Streams {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType") /* Added in JDK 9 only */
    public static <T> Stream<T> stream(Optional<T> opt) {
        return opt.map(Stream::of)
                  .orElseGet(Stream::empty);
    }
}
