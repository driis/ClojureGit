(ns cgit-test
  (:require [clojure.test :refer :all]
            [cgit :refer :all]
            [cgit.parse :refer :all]))

(deftest cat-file-can-get-type
  (is (= "blob" (cat-file "t" "64c63999a318632683e1d368bdf186a2e283d725"))))

(deftest cat-file-can-get-size
  (is (= 280 (cat-file "s" "64c63999a318632683e1d368bdf186a2e283d725"))))

(deftest cat-file-can-validate
  (is (true? (cat-file "e" "64c63999a318632683e1d368bdf186a2e283d725"))))

(deftest cat-file-can-get-content
  (is (= 280 (count (cat-file "p" "64c63999a318632683e1d368bdf186a2e283d725")))))
