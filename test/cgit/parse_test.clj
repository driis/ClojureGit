(ns cgit.parse-test
  (:require [clojure.test :refer :all]
            [cgit.parse :refer :all]))

;; Parse raw blob
(deftest i-can-parse-a-commit-type
  (let [blob (get-blob "a07e4c832e7bb76268c49a01c9ee885c7d91353e")]
    (is (= "commit" (:type blob)))))

(deftest i-can-parse-a-tree-type
  (let [blob (get-blob "8d392821d5999738d73af981a1becf36c6212a06")]
    (is (= "tree" (:type blob)))))

(deftest i-can-parse-a-blob-type
  (let [blob (get-blob "8c8975696e41a08644f3816d121c98a9249f2d59")]
    (is (= "blob" (:type blob)))))

(deftest i-can-parse-the-length
  (let [blob (get-blob "a07e4c832e7bb76268c49a01c9ee885c7d91353e")]
    (is (= 259 (:length blob)))))

(deftest i-can-get-content
  (let [blob (get-blob "a07e4c832e7bb76268c49a01c9ee885c7d91353e")]
    (is (= 259
          (count (:content blob))))))

(deftest i-can-get-content-string
  (let [content-string (get-content-string (get-blob "8c8975696e41a08644f3816d121c98a9249f2d59"))]
    (is (= "this is a test file for the unit tests\n"
          content-string))))

;; Parse object types
(deftest i-can-parse-a-commit
  (let [commit (parse-commit (get-blob "a07e4c832e7bb76268c49a01c9ee885c7d91353e"))]
    (is (= "Intermedia commit, because git pack screwed the tests. Oops." (:comment commit)))
    (is (= "8d392821d5999738d73af981a1becf36c6212a06" (:tree commit)))
    (is (= "6ec652e0b981829e2f832c75f50ad95aeef2d31b" (:parent commit)))))

(deftest i-can-get-a-commit-from-its-hash
  (let [commit (get-commit "a07e4c832e7bb76268c49a01c9ee885c7d91353e")]
    (is (= "Intermedia commit, because git pack screwed the tests. Oops." (:comment commit)))))

;(deftest i-can-parse-commit-author
;  (let [commit (get-commit "a07e4c832e7bb76268c49a01c9ee885c7d91353e")]
;    (let [author (:author commit)]
;      (is (= "Dennis Riis" (:name author)))
;      (is (= "driis@ebay.com") (:email author)))))
