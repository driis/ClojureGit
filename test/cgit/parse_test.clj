(ns cgit.parse-test
  (:require [clojure.test :refer :all]
            [cgit.parse :refer :all]))

;; Parse raw blob
(deftest i-can-parse-a-commit-type
  (let [blob (get-blob "47a59d0ad0cc623199741d6f98cf3bc3f0e473b2")]
    (is (= "commit" (:type blob)))))

(deftest i-can-parse-a-tree-type
  (let [blob (get-blob "036bbbde02143c508732dae8ff54d011897f5b2f")]
    (is (= "tree" (:type blob)))))

(deftest i-can-parse-a-blob-type
  (let [blob (get-blob "64c63999a318632683e1d368bdf186a2e283d725")]
    (is (= "blob" (:type blob)))))

(deftest i-can-parse-the-length
  (let [blob (get-blob "64c63999a318632683e1d368bdf186a2e283d725")]
    (is (= 280 (:length blob)))))

(deftest i-can-get-content
  (let [blob (get-blob "64c63999a318632683e1d368bdf186a2e283d725")]
    (is (= 280
          (count (:content blob))))))

(deftest i-can-get-content-string
  (let [content-string (get-content-string (get-blob "64c63999a318632683e1d368bdf186a2e283d725"))]
    (is (= "CGit - Git For Clojure
===

A Clojure implementation of git (read-only).

This is a pet project for me to learn Clojure and git internals at the same time. It will probably be wrong, have bugs, and might not ever be completed. It is not intended to be used for \"production\" usage."
          content-string))))

;; Parse object types
(deftest i-can-parse-a-commit
  (let [commit (parse-commit "c5f0568851444572b1e17421dd9fb88e72d87af0")]
    (is (= "Create project scaffolding" [:comment commit]))
    (is (= "036bbbde02143c508732dae8ff54d011897f5b2f" [:tree commit]))
    (is (= "dc3f8865780c6fc1e60264b5ae0a99bfab35bcb3" [:parent commit]))))
