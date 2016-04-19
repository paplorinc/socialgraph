[![Build Status](https://travis-ci.org/paplorinc/socialgraph.svg?branch=master)](https://travis-ci.org/paplorinc/socialgraph?branch=master) [![Coverage Status](https://coveralls.io/repos/github/paplorinc/socialgraph/badge.svg?branch=master)](https://coveralls.io/github/paplorinc/socialgraph?branch=master)

Simple, twitter-like, console-based social networking experiment
================================================================

Building the project
--------------------

You can create a new executable via the `gradlew clean build jacocoTestReport` command (or via the provided Idea run config).

This will compile the code, run all the tests, and deploy the executables in the `build/distributions/SocialGraph-1.0.0.zip` file, containing all the dependencies — with Windows/Linux launchers in the `bin` folder.

You can view the test results in `build/reports/tests/index.html` with the coverage statistics in `build/reports/jacoco/test/html/index.html` (or in the above Coveralls link).
 
Using the application
---------------------

The following features are available:

* `Posting` — Users can publish messages to their timelines: `<user name> -> <message>`

        > Alice -> I love the weather today
        > Bob -> Damn! We lost!
        > Bob -> Good game though.
  
* `Reading` — Users' timelines can be viewed: `<user name>`
 
        > Alice
        Alice: I love the weather today (5 minutes ago)
        > Bob
        Bob: Good game though. (1 minute ago)
        Bob: Damn! We lost! (2 minutes ago)
  
* `Following` — Users can subscribe to others' posts: `<user name> follows <another user>`
  
        > Charlie follows Alice
  
* `Aggregate Reading` — Users can display others' posts, merged with their followees': `<user name> wall`

        > Charlie -> I'm in New York today! Anyone wants to have a coffee? 
        > Charlie follows Bob
        > Charlie wall
        Charlie: I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
        Bob: Good game though. (1 minute ago)
        Bob: Damn! We lost! (2 minutes ago)
        Alice: I love the weather today (5 minutes ago)