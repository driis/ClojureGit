(ns util.zip-test
  (:require [clojure.test :refer :all]
            [cgit.util.zip :refer :all]))

(deftest i-can-get-blob-path
  (is (=
        ".git/objects/47/a59d0ad0cc623199741d6f98cf3bc3f0e473b2"
        (path-from-hash "47a59d0ad0cc623199741d6f98cf3bc3f0e473b2"))))

(deftest i-can-unzip-an-object-and-get-correct-number-of-bytes
  (is (=
        289
        (count (unzip-blob "64c63999a318632683e1d368bdf186a2e283d725")))))
