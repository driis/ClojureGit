(ns cgit-test
  (:require [clojure.test :refer :all]
            [cgit :refer :all]
            [cgit.parse :refer :all]))

(deftest cat-file-can-get-type
  (is (= "blob" (cat-file "t" "8c8975696e41a08644f3816d121c98a9249f2d59"))))

(deftest cat-file-can-get-size
  (is (= 39 (cat-file "s" "8c8975696e41a08644f3816d121c98a9249f2d59"))))

(deftest cat-file-can-validate
  (is (true? (cat-file "e" "8c8975696e41a08644f3816d121c98a9249f2d59"))))

(deftest cat-file-can-get-content
  (is (= 39 (count (cat-file "p" "8c8975696e41a08644f3816d121c98a9249f2d59")))))
