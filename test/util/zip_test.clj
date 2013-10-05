(ns util.zip-test
  (:require [clojure.test :refer :all]
            [cgit.util.zip :refer :all]))

(deftest i-can-get-blob-path
  (is (=
        ".git/objects/47/a59d0ad0cc623199741d6f98cf3bc3f0e473b2"
        (path-from-hash "47a59d0ad0cc623199741d6f98cf3bc3f0e473b2"))))

(deftest i-can-unzip-an-object-and-get-correct-number-of-bytes
  (is (=
        270
        (count (unzip-blob "a07e4c832e7bb76268c49a01c9ee885c7d91353e")))))
