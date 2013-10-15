(ns cgit.zip-test
  (:require [clojure.test :refer :all]
            [cgit.zip :refer :all]))

(deftest i-can-unzip-an-object-and-get-correct-number-of-bytes
  (is (=
        270
        (count (unzip-blob "a07e4c832e7bb76268c49a01c9ee885c7d91353e")))))
