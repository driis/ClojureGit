(ns core-test
  (:require [clojure.test :refer :all]
            [cgit.util.zip :refer :all]))

(deftest i-can-unzip-a-commit
  (is (not nil) (unzip-blob "47a59d0ad0cc623199741d6f98cf3bc3f0e473b2")))
