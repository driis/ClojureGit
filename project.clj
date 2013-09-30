(defproject cgit "0.1.0-SNAPSHOT"
  :description "Git in Clojure"
  :license {:name "MIT License"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :repl-options {
    ;; Specify the ns to start the REPL in (overrides :main in
    ;; this case only)
    :init-ns cgit})