(ns cgit.parse-test
  (:require [clojure.test :refer :all]
            [cgit.parse :refer :all]))

;; Parse raw blob
(deftest i-can-parse-a-commit-type
  (let [blob (get-blob "3c4326225f47c63a170305174a46ea27d42ac48e")]
    (is (= "commit" (:type blob)))))

(deftest i-can-parse-a-tree-type
  (let [blob (get-blob "1679680677a2475af2effc2f70ead4bf79e69be7")]
    (is (= "tree" (:type blob)))))

(deftest i-can-parse-a-blob-type
  (let [blob (get-blob "0c3cbafc359595f46f1cecf7f2365642999dfe0a")]
    (is (= "blob" (:type blob)))))

(deftest i-can-parse-the-length
  (let [blob (get-blob "3c4326225f47c63a170305174a46ea27d42ac48e")]
    (is (= 280 (:length blob)))))

(deftest i-can-get-content
  (let [blob (get-blob "3c4326225f47c63a170305174a46ea27d42ac48e")]
    (is (= 280
          (count (:content blob))))))

(deftest i-can-get-content-string
  (let [content-string (get-content-string (get-blob "3c4326225f47c63a170305174a46ea27d42ac48e"))]
    (is (= "CGit - Git For Clojure
===

A Clojure implementation of git (read-only).

This is a pet project for me to learn Clojure and git internals at the same time. It will probably be wrong, have bugs, and might not ever be completed. It is not intended to be used for \"production\" usage."
          content-string))))

;; Parse object types
(deftest i-can-parse-a-commit
  (let [commit (parse-commit (get-blob "3c4326225f47c63a170305174a46ea27d42ac48e"))]
    (is (= "Create project scaffolding" (:comment commit)))
    (is (= "036bbbde02143c508732dae8ff54d011897f5b2f" (:tree commit)))
    (is (= "dc3f8865780c6fc1e60264b5ae0a99bfab35bcb3" (:parent commit)))))

(deftest i-can-get-a-commit-from-its-hash
  (let [commit (get-commit "3c4326225f47c63a170305174a46ea27d42ac48e")]
    (is (= "Create project scaffolding" (:comment commit)))))

(deftest i-can-parse-commit-author
  (let [commit (get-commit "3c4326225f47c63a170305174a46ea27d42ac48e")]
    (let [author (:author commit)]
      (is (= "Dennis Riis" (:name author)))
      (is (= "driis@ebay.com") (:email author)))))
