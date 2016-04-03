package pap.lorinc.socialgraph.utils;

import java.util.Optional;
import java.util.stream.Stream;

public class Streams {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType") /* Added in JDK 9 only */
    public static <T> java.util.stream.Stream<T> stream(Optional<T> opt) {
        return opt.map(Stream::of)
                  .orElseGet(Stream::empty);
    }
}
