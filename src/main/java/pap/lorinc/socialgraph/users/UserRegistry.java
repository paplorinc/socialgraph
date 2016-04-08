package pap.lorinc.socialgraph.users;

import javaslang.Function1;

class UserRegistry {
    static final Function1<String, User> USER_REGISTRY = Function1.of(User::new).memoized();
} 