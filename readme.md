CGit - Git For Clojure
===

A Clojure implementation of git (read-only).

This is a pet project for me to learn Clojure and git internals at the same time. It will probably be wrong, have bugs, and might not ever be completed. It is not intended to be used for "production" usage.

Usage
---
You need [Leiningen](https://github.com/technomancy/leiningen) installed. To run, use the REPL:

    lein repl

Or to run the tests

    lein test

Currently implemented higher-level functions from git:

* cat-file

For example, to print the contents of a blob with a given hash, run the following in the REPL:

    (cat-file "p" "64c63999a318632683e1d368bdf186a2e283d725")

Which should return the equivalent of:

    git cat-file -p 64c63999a318632683e1d368bdf186a2e283d725
